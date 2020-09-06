package space.zero.september.admin.vo;

import lombok.Data;
import space.zero.september.admin.entity.User;

import java.io.Serializable;
import java.util.List;

/**
 * @author : penggs
 * @program : september
 * @description : 用户信息（含角色权限等）
 * @create : 2020-09-01
 */
@Data
public class UserInfoVO implements Serializable {

    private static final long serialVersionUID = -8089431293334852693L;
    /**
     * 用户
     */
    private User user;
    /**
     * 权限标识集合
     */
    private List<String> permissions;

    /**
     * 角色集合
     */
    private List<Long> roles;
}