package space.zero.september.admin.util;

import java.io.*;
import java.util.List;

/**
 * @author : penggs
 * @program : september
 * @description : 集合工具类
 * @create : 2019-08-03 22:41
 */
public class CollectionUtil {
    /**
     * List 深克隆
     *
     * @param src 源List
     * @return java.util.List<T>
     * @author penggs
     * @date 2019-08-03
     */
    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }
}