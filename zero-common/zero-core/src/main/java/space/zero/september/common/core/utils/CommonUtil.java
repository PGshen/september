package space.zero.september.common.core.utils;

import org.springframework.util.StringUtils;
import space.zero.september.common.core.param.ReqCond;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : penggs
 * @program : september
 * @description : 通用工具类
 * @create : 2019-07-21 22:47
 */
public class CommonUtil {
    /**
     * 处理查询条件
     *
     * @param reqCond
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @author linyao
     * @date 2019/4/2
     */
    public static Map<String, Object> getReqCond(ReqCond reqCond) {
        Map<String, Object> condition = reqCond.getFilter();
        // 移除空值条件
        if (null != condition) {
            condition.entrySet().removeIf(cond -> StringUtils.isEmpty(cond.getValue()));
        } else {
            condition = new HashMap<>();
        }
        return condition;
    }
}