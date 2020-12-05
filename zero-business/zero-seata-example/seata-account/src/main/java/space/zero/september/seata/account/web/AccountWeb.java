package space.zero.september.seata.account.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.zero.september.seata.account.service.AccountService;

import java.math.BigDecimal;

/**
 * <project> september
 *
 * <p>
 *
 * @author penggs
 * @since 2020-12-03
 */
@RestController
public class AccountWeb {
    @Autowired
    private AccountService accountService;

    /**
     * 减余额
     * @param userId 用户ID
     * @param money 数额
     * @return 是否成功
     */
    @RequestMapping("/debit")
    public Boolean debit(String userId, BigDecimal money) {
        accountService.debit(userId, money);
        return true;
    }
}