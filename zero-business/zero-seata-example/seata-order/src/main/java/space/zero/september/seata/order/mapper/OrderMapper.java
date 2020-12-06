package space.zero.september.seata.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import space.zero.september.seata.order.entity.Order;

/**
 * <project> september
 *
 * <p>
 *
 * @author penggs
 * @since 2020-12-03
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
