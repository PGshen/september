package space.zero.september.seata.storage.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import space.zero.september.seata.storage.entity.Storage;
import space.zero.september.seata.storage.mapper.StorageMapper;

/**
 * <project> september
 *
 * <p>
 *
 * @author penggs
 * @since 2020-12-03
 */
@Service
public class StorageService extends ServiceImpl<StorageMapper, Storage> {
    @DS("storage")//每一层都需要使用多数据源注解切换所选择的数据库
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void deduct(String commodityCode, int count) {
        Storage storage = baseMapper.selectOne(new QueryWrapper<Storage>().eq("commodity_code", commodityCode));
        int stock = storage.getCount() - count;
        if (stock < 0) {
            throw new RuntimeException("库存不足！");
        }
        storage.setCount(stock);

        baseMapper.updateById(storage);
    }
}