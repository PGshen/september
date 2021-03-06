package space.zero.september.seata.storage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <project> september
 *
 * <p>
 *
 * @author penggs
 * @since 2020-12-03
 */
@Data
@TableName("storage_tbl")
public class Storage implements Serializable {
    private static final long serialVersionUID = -2229201668524315011L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String commodityCode;
    private Integer count;
}