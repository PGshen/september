package space.zero.september.common.security.constant;

/**
 * @author : penggs
 * @program : september
 * @description : 安全静态变量
 * @create : 2019-04-11
 */
public class SecurityConstant {
    public static final String RESOURCE_SERVER_CONFIGURER = "resourceServerConfigurerAdapter";

    public static final String AUTH_PREFIX = "SEPTEMBER:";
    public static final String STATUS_NORMAL = "1";
    public static final String ROLE = "ROLE_";


    /**
     * 客户端模式
     */
    public static final String CLIENT_CREDENTIALS = "client_credentials";
    /**
     * sys_oauth_client_details表字段
     */
    public static final String CLIENT_FIELDS = "CLIENT_ID, CLIENT_SECRET, RESOURCE_IDS, SCOPE, "
            + "AUTHORIZED_GRANT_TYPES, WEB_SERVER_REDIRECT_URI, AUTHORITIES, ACCESS_TOKEN_VALIDITY, "
            + "REFRESH_TOKEN_VALIDITY, ADDITIONAL_INFORMATION, AUTOAPPROVE";

    /**
     * jdbcClientDetailsService查询
     */
    public static final String BASE_FIND_STATEMENT = "SELECT " + CLIENT_FIELDS
            + " FROM t_sys_oauth_client_details";

    /**
     * 默认的查询语句
     */
    public static final String DEFAULT_FIND_STATEMENT = BASE_FIND_STATEMENT + " ORDER BY CLIENT_ID";

    /**
     * 按条件client_id 查询
     */
    public static final String DEFAULT_SELECT_STATEMENT = BASE_FIND_STATEMENT + " WHERE CLIENT_ID = ?";
    /**
     * 用户ID字段
     */
    public static final String DETAILS_USER_ID = "USER_ID";

    /**
     * 用户名字段
     */
    public static final String DETAILS_USERNAME = "USERNAME";

    /**
     * 用户部门字段
     */
    public static final String DETAILS_ORG_ID = "ORG_ID";
}