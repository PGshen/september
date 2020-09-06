package space.zero.september.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : penggs
 * @program : september
 * @description : 外部客户端
 * @create : 2019-07-15 20:57
 */
@Data
@TableName("t_sys_client_app")
public class ClientApp implements Serializable {
    private static final long serialVersionUID = 3617524699533079425L;
    /**
     * id(主键）
     */
    @TableId(value = "client_app_id", type = IdType.AUTO)
    private Long clientAppId;
    /**
     * 外部客户端ID
     */
    private String clientId;
    /**
     * 外部客户端密钥
     */
    private String clientSecret;
    /**
     * 描述
     */
    private String description;
    /**
     * 资源ID
     */
    private String resourceIds;
    /**
     * 访问范围
     */
    private String scope;
    /**
     * 授权类型，逗号分隔
     */
    private String authorizedGrantTypes;
    /**
     * 重定向地址
     */
    private String webServerRedirectUri;
    /**
     * 权限
     */
    private String authorities;
    /**
     * 访问token有效期
     */
    private Long accessTokenValidity;
    /**
     * 刷新token有效期
     */
    private Long refreshTokenValidity;
    /**
     * 附加信息
     */
    private String additionalInformation;
    private String autoapprove;
    /**
     * 删除标识
     */
    @TableLogic
    @JsonIgnore
    private Integer isDel;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}