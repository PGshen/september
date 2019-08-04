package space.zero.september.admin.service;

import com.baomidou.mybatisplus.service.IService;
import space.zero.september.admin.entity.MenuApi;

import java.util.List;

/**
 * @author : penggs
 * @program : september
 * @description : 菜单-API关联service
 * @create : 2019-07-19 18:19
 */
public interface MenuApiService extends IService<MenuApi> {
    /**
     * 菜单绑定API接口
     *
     * @param menuId 菜单ID
	 * @param apiIds API ID列表
     * @author penggs
     * @date 2019-07-25
     */
    void bindApi(Long menuId, List<Long> apiIds);

    /**
     * 删除关联
     *
     * @param menuId 菜单ID
     * @author penggs
     * @date 2019-07-25
     */
    void disassociateMenuApi(Long menuId);

    /**
     * 新增关联
     *
     * @param menuId 菜单ID
	 * @param apiIds API ID列表
     * @author penggs
     * @date 2019-07-25
     */
    void associateMenuApi(Long menuId, List<Long> apiIds);
}