package space.zero.september.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.zero.september.admin.entity.Menu;
import space.zero.september.admin.mapper.MenuMapper;
import space.zero.september.admin.service.MenuService;
import space.zero.september.admin.util.ResultGen;
import space.zero.september.common.core.Result;
import space.zero.september.common.core.param.ReqCond;
import space.zero.september.common.core.returncode.BusinessCode;
import space.zero.september.common.core.returncode.ErrorCode;
import space.zero.september.common.core.utils.CommonUtil;

import java.util.List;
import java.util.Map;

/**
 * @author : penggs
 * @program : september
 * @description : 菜单实现类
 * @create : 2019-07-19 18:26
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    private Logger log = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    private ResultGen<Menu> resultGen;

    /**
     * 根据ID获取
     *
     * @param id 菜单ID
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Menu>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<Menu> getMenuById(Long id) {
        log.info("Get menu: id = " + id);
        Menu menu = baseMapper.selectById(id);
        if (menu == null){
            return resultGen.fail(BusinessCode.MENU, ErrorCode.P301);
        }
        return resultGen.success(BusinessCode.MENU, menu);
    }

    /**
     * 分页查询
     *
     * @param reqCond 条件
     * @return space.zero.september.common.core.Result<com.baomidou.mybatisplus.plugins.Page<space.zero.september.admin.entity.Menu>>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<Page<Menu>> getMenu(ReqCond reqCond) {
        log.info("Get menu: condition : " + JSONObject.toJSONString(reqCond));
        ResultGen<Page<Menu>> resultGen = new ResultGen<>();
        Page<Menu> page = new Page<>(reqCond.getPage(), reqCond.getSize(), reqCond.getSort());
        Map<String, Object> condition = CommonUtil.getReqCond(reqCond);
        page.setRecords(baseMapper.selectMenu(page, condition));
        return resultGen.success(BusinessCode.MENU, page);
    }

    /**
     * 保存
     *
     * @param menu 菜单
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Menu>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<Menu> saveMenu(Menu menu) {
        log.info("Save menu: menu = " + JSONObject.toJSONString(menu));
        // 数据校验，不符就抛异常
        baseMapper.insert(menu);
        return resultGen.success(BusinessCode.MENU, menu);
    }

    /**
     * 更新菜单
     *
     * @param id 菜单ID
	 * @param menu 菜单
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Menu>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<Menu> updateMenu(Long id, Menu menu) {
        log.info("Update menu: id = " + id + ", menu = " + JSONObject.toJSONString(menu));
        menu.setMenuId(id);
        baseMapper.updateById(menu);
        return resultGen.success(BusinessCode.MENU, menu);
    }

    /**
     * 删除菜单
     *
     * @param id 菜单ID
     * @return space.zero.september.common.core.Result<java.lang.Boolean>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<Boolean> removeMenu(Long id) {
        log.info("Remove menu: id = " + id);
        ResultGen<Boolean> resultGen = new ResultGen<>();
        baseMapper.deleteById(id);
        return resultGen.success(BusinessCode.MENU, Boolean.TRUE);
    }

    /**
     * 获取角色关联的菜单列表
     *
     * @param roleId 角色ID
     * @return java.util.List<space.zero.september.admin.entity.Menu>
     * @author penggs
     * @date 2019-07-25
     */
    @Override
    public List<Menu> getMenuByRoleId(Long roleId) {
        log.info("Get menu by roleId: roleId = " + roleId);
        return baseMapper.selectMenuByRoleId(roleId);
    }

    /**
     * 获取角色关联的菜单ID列表
     *
     * @param roleId 角色ID
     * @return java.util.List<java.lang.Long>
     * @author penggs
     * @date 2019-07-25
     */
    @Override
    public List<Long> getMenuIdByRoleId(Long roleId) {
        log.info("Get menuIds by roleId: roleId = " + roleId);
        return baseMapper.selectMenuIdByRoleId(roleId);
    }

    /**
     * 根据父节点ID查询子节点
     *
     * @param menuId 父节点ID
     * @return java.util.List<space.zero.september.admin.entity.Menu>
     * @author penggs
     * @date 2019-07-26
     */
    @Override
    public Menu getMenuTree(Long menuId) {
        log.info("Get menu tree: menuId = " + menuId);
        return baseMapper.selectMenuTree(menuId);
    }

    /**
     * 获取指定用户的菜单树
     *
     * @param userId 用户ID
     * @return java.util.List<space.zero.september.admin.entity.Menu>
     * @author penggs
     * @date 2019-07-28
     */
    @Override
    public List<Menu> getUserMenuId(Long userId) {
        // 1. 完整菜单树
        List<Menu> allMenu = this.getMenuTree(0L).getChildren();
        // 2. 用户对应角色关联的菜单
        List<Long> userMenuIds = baseMapper.selectUserMenuId(userId);
        // 3. 对完整树做裁剪
        cutOutMenuTree(allMenu, userMenuIds);
        return allMenu;
    }

    /**
     * 裁剪菜单树，使其仅保留当前角色列表所关联的菜单
     *
     * @param menus 菜单树
	 * @param menuIds 角色所拥有的菜单
     * @author penggs
     * @date 2019-07-28
     */
    private void cutOutMenuTree(List<Menu> menus, List<Long> menuIds){
        menus.removeIf(menu -> !menuIds.contains(menu.getMenuId()));
        // 移除出按钮类型等
        menus.removeIf(menu -> menu.getType() == 2);
        menus.forEach(menu -> cutOutMenuTree(menu.getChildren(), menuIds));
    }

    /**
     * 获取指定用户的菜单权限标识
     *
     * @param userId 用户ID
     * @return java.util.List<java.lang.String>
     * @author penggs
     * @date 2019-07-28
     */
    @Override
    public List<String> getUserMenuPerm(Long userId){
        return baseMapper.selectUserMenuPerm(userId);
    }
}