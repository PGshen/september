package space.zero.september.seata.storage.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import space.zero.september.seata.storage.service.StorageService;

/**
 * <project> september
 *
 * <p>
 *
 * @author penggs
 * @since 2020-12-03
 */
@RestController
public class StorageWeb {
    private static final Logger logger = LoggerFactory.getLogger(StorageWeb.class);

    @Autowired
    private StorageService storageService;

    /**
     * 减库存
     * @param commodityCode 商品编码
     * @param count 数量
     * @return 是否成功
     */
    @GetMapping(path = "/deduct")
    public Boolean deduct(String commodityCode, Integer count) {
        logger.info("deduct...");
        storageService.deduct(commodityCode, count);
        return true;
    }
}