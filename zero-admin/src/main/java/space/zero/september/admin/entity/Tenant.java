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
 * @description : 租户
 * @create : 2019-07-15 20:55
 */
@Data
@TableName("sys_tenant")
public class Tenant implements Serializable {

    private static final long serialVersionUID = 5442967563479369883L;
    /**
     * ID
     */
    @TableId(value = "tenant_id", type = IdType.AUTO)
    private Long tenantId;
    /**
     * 租户名称
     */
    private String tenantName;
    /**
     * 租户编号
     */
    private String tenantCode;
    /**
     * 描述
     */
    private String description;
    /**
     * 是否禁用（0 正常， 1 禁用）
     */
    private Integer isEnable;
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