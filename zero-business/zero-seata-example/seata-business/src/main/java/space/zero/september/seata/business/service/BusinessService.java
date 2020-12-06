package space.zero.september.seata.business.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import space.zero.september.seata.business.feign.OrderService;
import space.zero.september.seata.business.feign.StorageService;

/**
 * <project> september
 *
 * <p>
 *
 * @author penggs
 * @since 2020-12-04
 */
@Service
public class BusinessService {
    @Autowired
    private OrderService orderService;
    @Autowired
    private StorageService storageService;

    /**
     * 减库存，下订单
     *
     * @param userId 用户ID
     * @param commodityCode 商品ID
     * @param orderCount 订购数量
     */
    @DS("business")
    @Transactional(rollbackFor = Exception.class)
    @GlobalTransactional(rollbackFor = Exception.class)
    public void purchase(String userId, String commodityCode, int orderCount) {
        // 减库存
        storageService.deduct(commodityCode, orderCount);
        // 下订单
        orderService.create(userId, commodityCode, orderCount);
    }


}