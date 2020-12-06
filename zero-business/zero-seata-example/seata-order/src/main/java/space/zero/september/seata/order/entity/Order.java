package space.zero.september.seata.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <project> september
 *
 * <p>
 *
 * @author penggs
 * @since 2020-12-03
 */
@Data
@TableName("order_tbl")
public class Order implements Serializable {
    private static final long serialVersionUID = -173116833179908827L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String userId;
    private String commodityCode;
    private BigDecimal money;
    private Integer count;
}