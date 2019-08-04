package space.zero.september.common.datasource.handler;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author : penggs
 * @program : september
 * @description : 自定义数据表字段填充
 * @create : 2019-07-16 20:01
 */
@Component
public class CustomMetaObjectHandler extends MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {

        Object createTime = getFieldValByName("createdTime", metaObject);
        Object modifyTime = getFieldValByName("updateTime", metaObject);

        /**
         * 设置实体属性setter进去的值，优先级要高于自动填充值
         * 如果实体没有设置该属性，防止entity的setter值被覆盖
         */
        Date date=new Date();
        if (null == createTime) {
            this.setFieldValByName("createdTime",date , metaObject);
        }

        if (null == modifyTime) {
            this.setFieldValByName("updateTime", date, metaObject);
        }

    }


    @Override
    public void updateFill(MetaObject metaObject) {

        Date updateTime = (Date) getFieldValByName("updateTime", metaObject);
        Date date = new Date();
        if (updateTime == null) {
            this.setFieldValByName("updateTime", date, metaObject);
        } else if (date.after(updateTime)) {
            this.setFieldValByName("updateTime", date, metaObject);
        }

    }

}