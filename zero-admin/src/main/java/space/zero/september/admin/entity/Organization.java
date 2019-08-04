package space.zero.september.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author : penggs
 * @program : september
 * @description : 组织机构
 * @create : 2019-07-15 20:56
 */
@Data
@TableName("sys_org")
public class Organization implements Serializable {

    private static final long serialVersionUID = -787998684234909569L;
    /**
     * 机构ID
     */
    @TableId(value = "org_id", type = IdType.AUTO)
    private Long orgId;
    /**
     * 机构父ID
     */
    private Long parentOrgId;
    /**
     * 机构名称
     */
    private String orgName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 排序
     */
    private Integer orderNum;
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
    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * 子节点
     */
    @TableField(exist = false)
    private List<Organization> children;
}