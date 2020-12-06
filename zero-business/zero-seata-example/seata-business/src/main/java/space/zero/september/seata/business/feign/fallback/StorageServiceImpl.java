package space.zero.september.seata.business.feign.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
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
public class StorageServiceImpl implements StorageService {
    private static final Logger log = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Override
    public boolean deduct(String commodityCode, Integer count) {
        log.error("调用{}异常:{}, {}", "deduct", commodityCode, count);
        return false;
    }
}