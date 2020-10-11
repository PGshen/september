package space.zero.september.auth.feign.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : penggs
 * @program : september
 * @description : 用户
 * @create : 2019-09-10
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 8103760695344263609L;

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 密码
     */
    private String password;
    /**
     * 名字
     */
    private String name;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 电话
     */
    private String phone;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否禁用
     */
    private Integer isEnable;
    /**
     * 删除标识
     */
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
     * 用户角色（初始化防止返回null,导致前端JS出错）
     */
    private List<Long> roles = new ArrayList<>();

}