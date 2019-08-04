package space.zero.september.admin.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : penggs
 * @program : september
 * @description : 菜单API关联
 * @create : 2019-07-16 19:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_menu_api")
public class MenuApi implements Serializable {

    private static final long serialVersionUID = -8904678812082753039L;
    /**
     * 菜单ID
     */
    private Long menuId;
    /**
     * API的ID
     */
    private Long apiId;
}