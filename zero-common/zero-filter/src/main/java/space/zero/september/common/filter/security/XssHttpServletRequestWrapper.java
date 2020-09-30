package space.zero.september.common.filter.security;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.text.StringEscapeUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * <project> september
 *
 * <p> XSS过滤器
 *
 * @author penggs
 * @since 2019-09-06
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getHeader(String name) {
        return StringEscapeUtils.escapeHtml4(super.getHeader(name));
    }

    @Override
    public String getQueryString() {
        return StringEscapeUtils.escapeHtml4(super.getQueryString());
    }

    @Override
    public String getParameter(String name) {
        return StringEscapeUtils.escapeHtml4(super.getParameter(name));
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if(values != null) {
            int length = values.length;
            String[] escapseValues = new String[length];
            for(int i = 0; i < length; i++){
                escapseValues[i] = StringEscapeUtils.escapeHtml4(values[i]);
            }
            return escapseValues;
        }
        return values;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        String str=getRequestBody(super.getInputStream());
        JSONObject jsonObject = JSONObject.parseObject(str);
        // 递归替换
        recursionReplace(jsonObject);
        str = JSONObject.toJSONString(jsonObject);
        final ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return bais.read();
            }
            @Override
            public boolean isFinished() {
                return false;
            }
            @Override
            public boolean isReady() {
                return false;
            }
            @Override
            public void setReadListener(ReadListener listener) {
            }
        };
    }

    private String getRequestBody(InputStream stream) {
        String line = "";
        StringBuilder body = new StringBuilder();
        int counter = 0;

        // 读取POST提交的数据内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, Charset.forName("UTF-8")));
        try {
            while ((line = reader.readLine()) != null) {
                body.append(line);
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body.toString();
    }

    /**
     * 递归替换
     * @param object
     */
    private static void recursionReplace(Object object) {

        if(object instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) object;
            for (Map.Entry<String, Object> entry: jsonObject.entrySet()) {
                Object o = entry.getValue();
                if(o instanceof String) {
                    entry.setValue(StringEscapeUtils.escapeHtml4((String)entry.getValue()));
                } else {
                    recursionReplace(o);
                }
            }
        }
        if(object instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) object;
            for (Object o : jsonArray) {
                recursionReplace(o);
            }
        }
    }
}