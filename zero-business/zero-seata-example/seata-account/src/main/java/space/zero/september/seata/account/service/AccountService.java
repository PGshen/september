package space.zero.september.seata.account.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import space.zero.september.seata.account.entity.Account;
import space.zero.september.seata.account.mapper.AccountMapper;

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
public class AccountService extends ServiceImpl<AccountMapper, Account> {

    @DS("account")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void debit(String userId, BigDecimal num) {
        Account account = baseMapper.selectOne(new QueryWrapper<Account>().eq("user_id", userId));
        BigDecimal balance = account.getMoney().subtract(num);
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("余额不足！");
        }
        account.setMoney(balance);
        baseMapper.updateById(account);
    }
}