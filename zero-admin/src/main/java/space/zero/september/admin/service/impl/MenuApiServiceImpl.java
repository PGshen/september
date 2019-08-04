package space.zero.september.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import space.zero.september.admin.entity.MenuApi;
import space.zero.september.admin.mapper.MenuApiMapper;
import space.zero.september.admin.service.MenuApiService;

import java.util.List;

/**
 * @author : penggs
 * @program : september
 * @description : 菜单-API关联实现类
 * @create : 2019-07-19 18:27
 */
@Service
public class MenuApiServiceImpl extends ServiceImpl<MenuApiMapper, MenuApi> implements MenuApiService {
    /**
     * 菜单绑定API
     *
     * @param menuId 菜单ID
	 * @param apiIds API ID列表
     * @author penggs
     * @date 2019-07-25
     */
    @Override
    public void bindApi(Long menuId, List<Long> apiIds) {
        disassociateMenuApi(menuId);
        associateMenuApi(menuId, apiIds);
    }

    /**
     * 取消关联
     *
     * @param menuId 菜单ID
     * @author penggs
     * @date 2019-07-25
     */
    @Override
    public void disassociateMenuApi(Long menuId) {
        baseMapper.delete(new EntityWrapper<MenuApi>().eq("menu_id", menuId));
    }

    /**
     * 新增关联
     *
     * @param menuId 菜单ID
	 * @param apiIds API ID列表
     * @author penggs
     * @date 2019-07-25
     */
    @Override
    public void associateMenuApi(Long menuId, List<Long> apiIds) {
        apiIds.forEach(apiId -> {
            baseMapper.insert(new MenuApi(menuId, apiId));
        });
    }
}