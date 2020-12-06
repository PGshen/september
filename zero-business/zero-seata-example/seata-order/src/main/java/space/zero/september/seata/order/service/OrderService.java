package space.zero.september.seata.order.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import space.zero.september.seata.order.feign.AccountService;
import space.zero.september.seata.order.mapper.OrderMapper;
import space.zero.september.seata.order.entity.Order;

import java.math.BigDecimal;

/**
 * <project> september
 *
 * <p>
 *
 * @author penggs
 * @since 2020-12-03
 */
@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {
    @Autowired
    private AccountService accountService;

    @DS("order")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void create(String userId, String commodityCode, Integer count) {

        // 计算金额，从商品表获取商品价格
        BigDecimal orderMoney = new BigDecimal(count).multiply(new BigDecimal(5));

        Order order = new Order();
        order.setUserId(userId);
        order.setCommodityCode(commodityCode);
        order.setCount(count);
        order.setMoney(orderMoney);
        baseMapper.insert(order);

        accountService.debit(userId, orderMoney);

    }
}