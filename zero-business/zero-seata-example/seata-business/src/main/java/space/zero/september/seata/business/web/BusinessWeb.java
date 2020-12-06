package space.zero.september.seata.business.web;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.zero.september.seata.business.service.BusinessService;

import java.io.Serializable;

/**
 * <project> september
 *
 * <p>
 *
 * @author penggs
 * @since 2020-12-04
 */
@RestController
public class BusinessWeb {
    @Autowired
    private BusinessService businessService;

    /**
     * 购买下单，模拟全局事务提交
     *
     * @return
     */
    @RequestMapping("/purchase/commit")
    public Boolean purchaseCommit(@RequestBody Purchase purchase) {
        try {
            businessService.purchase(purchase.getUserId(), purchase.getCommodityCode(), purchase.getOrderCount());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 购买下单，模拟全局事务回滚
     *
     * @return
     */
    @RequestMapping("/purchase/rollback")
    public Boolean purchaseRollback(@RequestBody Purchase purchase) {
        try {
            businessService.purchase(purchase.getUserId(), purchase.getCommodityCode(), purchase.getOrderCount());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Data
    static class Purchase implements Serializable {
        private static final long serialVersionUID = -5992648313596490906L;
        String userId;
        String commodityCode;
        int orderCount;
    }
}