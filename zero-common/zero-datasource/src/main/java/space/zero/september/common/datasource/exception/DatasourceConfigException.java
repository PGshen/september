package space.zero.september.common.datasource.exception;

/**
 * @author : penggs
 * @program : september
 * @description : 数据源配置异常
 * @create : 2019-07-16 20:00
 */
public class DatasourceConfigException extends Exception {
    public DatasourceConfigException() {
        super("数据库配置错误，至少包含一个数据源，请检查配置文件");
    }

    public DatasourceConfigException(String message) {
        super(message);
    }

    public DatasourceConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatasourceConfigException(Throwable cause) {
        super(cause);
    }

    public DatasourceConfigException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}