package space.zero.september.admin.vo;

import lombok.Data;
import space.zero.september.admin.entity.Api;

import java.util.List;

/**
 * @author : penggs
 * @program : september
 * @description : 客户端关联API VO
 * @create : 2019-08-04 12:12
 */
@Data
public class ClientApiVo {
    /**
     * 未选中的API
     */
    private List<Api> fromData;
    /**
     * 已选中的API
     */
    private List<Api> toData;
}