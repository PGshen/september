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
 * @description : 接口
 * @create : 2019-07-15 20:57
 */
@Data
@TableName("t_sys_api")
public class Api implements Serializable, Cloneable {
    private static final long serialVersionUID = -2753701557066373333L;
    /**
     * ID
     */
    @TableId(value = "api_id", type = IdType.AUTO)
    private Long apiId;
    /**
     * 父ID（用户维护树形结构，方便查看）
     */
    private Long parentApiId;
    /**
     * API名称
     */
    private String name;
    /**
     * API描述
     */
    private String description;
    /**
     * 类型（0：目录 1：API）
     */
    private Integer type;
    /**
     * 级联路径
     */
    private String cascadePath;
    /**
     * API请求方式（POST，GET，PUT，DELETE等）
     */
    private String method;
    /**
     * URI（不包含ip和端口的uri)
     */
    private String uri;
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
     * 子节点列表
     */
    @TableField(exist = false)
    private List<Api> children;

    /**
     * 返回 METHOD:URI 作为认证的权限标识
     * @return
     */
    public String getPerm() {
        return this.method + ":" + this.getUri();
    }

}