package space.zero.september.admin.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : penggs
 * @program : september
 * @description : 客户端-API关联
 * @create : 2019-07-16 19:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_client_app_api")
public class ClientAppApi implements Serializable {

    private static final long serialVersionUID = 2820621223724634471L;
    /**
     * 客户端app的ID
     */
    private Long clientAppId;
    /**
     * 允许的IP
     */
    private String ip;
    /**
     * API接口ID
     */
    private Long apiId;
}