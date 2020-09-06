package space.zero.september.common.security.config;

import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;

/**
 * @program: september
 * @description: MD5加密
 * @author: penggs
 * @create: 2019-02-22 14:22
 **/
public class Md5PasswordEncoder extends MessageDigestPasswordEncoder {
    /**
     * The digest algorithm to use Supports the named
     * <a href="http://java.sun.com/j2se/1.4.2/docs/guide/security/CryptoSpec.html#AppA">
     * Message Digest Algorithms</a> in the Java environment.
     *
     * @param algorithm
     */
    public Md5PasswordEncoder(String algorithm) {
        super(algorithm);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return super.matches(rawPassword, encodedPassword.toLowerCase());
    }
}
