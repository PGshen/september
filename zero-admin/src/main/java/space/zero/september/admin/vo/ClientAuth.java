package space.zero.september.admin.vo;

import lombok.Data;

import java.util.List;

/**
 * @author : penggs
 * @program : september
 * @description : 客户端授权
 * @create : 2019-08-04 12:20
 */
@Data
public class ClientAuth {
    /**
     * 客户端ID
     */
    private Long clientAppId;
    /**
     * ip
     */
    private String ip;
    /**
     * API ID列表
     */
    private List<Long> apiIds;
}