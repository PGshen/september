package space.zero.september.admin.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : penggs
 * @program : september
 * @description : 用户角色关联
 * @create : 2019-07-16 19:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = -8410549622839459582L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;
}