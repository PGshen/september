package space.zero.september.admin.vo;

import lombok.Data;

import java.util.List;

/**
 * @author : penggs
 * @program : september
 * @description : 菜单关联API
 * @create : 2019-08-04 01:43
 */
@Data
public class MenuAuth {
    /**
     * 菜单ID
     */
    private Long menuId;
    /**
     * API ID列表
     */
    private List<Long> apiIds;
}