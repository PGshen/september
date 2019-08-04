package space.zero.september.admin.vo;

import lombok.Data;
import space.zero.september.admin.entity.Api;

import java.util.List;

/**
 * @author : penggs
 * @program : september
 * @description : 菜单API关联，裁剪树
 * @create : 2019-08-03 21:52
 */
@Data
public class MenuApiVo {
    /**
     * 未选中的API
     */
    private List<Api> fromData;
    /**
     * 已选中的API
     */
    private List<Api> toData;
}