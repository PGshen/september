package space.zero.september.seata.business.feign.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import space.zero.september.seata.business.feign.OrderService;

/**
 * <project> september
 *
 * <p>
 *
 * @author penggs
 * @since 2020-12-04
 */
@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public boolean create(String userId, String commodityCode, Integer count) {
        log.error("调用{}异常:{}, {}, {}", "debit", userId, commodityCode, count);
        return false;
    }
}