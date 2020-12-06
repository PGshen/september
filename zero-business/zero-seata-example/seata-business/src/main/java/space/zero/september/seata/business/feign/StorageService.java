package space.zero.september.seata.business.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import space.zero.september.seata.business.feign.fallback.StorageServiceImpl;

/**
 * <project> september
 *
 * <p>
 *
 * @author penggs
 * @since 2020-12-04
 */
@FeignClient(name = "seata-storage", fallback = StorageServiceImpl.class)
@Service
public interface StorageService {
    @GetMapping("/storage/deduct")
    boolean deduct(@RequestParam("commodityCode") String commodityCode,
                @RequestParam("count") Integer count);
}
