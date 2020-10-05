package space.zero.september.common.utils;

import cn.hutool.core.util.StrUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <project> september
 *
 * <p> MD5工具类
 *
 * @author penggs
 * @since 2020-09-06
 */
public class Md5Util {
    public static String encode(String system, String timestamp, String secret) throws NoSuchAlgorithmException {
        byte[] systemBytes = system.getBytes();
        byte[] timestampBytes = StrUtil.isBlank(timestamp) ? "123".getBytes() : timestamp.getBytes();
        byte[] secretBytes = secret.getBytes();

        MessageDigest messagedigest = MessageDigest.getInstance("MD5");
        messagedigest.update(systemBytes, 0, systemBytes.length);
        messagedigest.update(secretBytes, 0, secretBytes.length);
        messagedigest.update(timestampBytes, 0, timestampBytes.length);

        byte[] digest = messagedigest.digest();
        return toHexString(digest);
    }

    public static String encode(String source) {
        byte[] sourceBytes = source.getBytes();
        MessageDigest messagedigest = null;
        try {
            messagedigest = MessageDigest.getInstance("MD5");
            messagedigest.update(sourceBytes, 0, sourceBytes.length);
            byte[] digest = messagedigest.digest();
            return toHexString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String toHexString(byte[] byteArray) {
        final StringBuilder hexString = new StringBuilder("");
        if (byteArray == null || byteArray.length <= 0) {
            return null;
        }
        for (byte b : byteArray) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                hexString.append(0);
            }
            hexString.append(hv);
        }
        return hexString.toString().toLowerCase();
    }
}