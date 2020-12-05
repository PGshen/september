package space.zero.september.seata.account.entity;

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
@TableName("account_tbl")
public class Account implements Serializable {
    private static final long serialVersionUID = -4803411856206851105L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String userId;
    private BigDecimal money;

    public Account() {
    }

    public Account(String userId) {
        this.userId = userId;
    }
}