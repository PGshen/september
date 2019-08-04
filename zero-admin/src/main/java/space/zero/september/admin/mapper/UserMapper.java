package space.zero.september.admin.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import space.zero.september.admin.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author : penggs
 * @program : september
 * @description : 用户mapper
 * @create : 2019-07-18 13:28
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 分页查询
     *
     * @param page 分页
	 * @param condition 条件
     * @return java.util.List<space.zero.september.admin.entity.User>
     * @author penggs
     * @date 2019-07-24
     */
    List<User> selectUser(Pagination page, Map condition);

    /**
     * 禁用/解禁用户
     *
     * @param userId 用户ID
     * @return int
     * @author penggs
     * @date 2019-08-02
     */
    int enableUser(Long userId);
}