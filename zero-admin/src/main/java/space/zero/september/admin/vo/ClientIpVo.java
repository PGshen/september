package space.zero.september.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : penggs
 * @program : september
 * @description : 客户端绑定的IP
 * @create : 2019-08-04 14:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientIpVo {
    /**
     * IP列表
     */
    private String ip;
}