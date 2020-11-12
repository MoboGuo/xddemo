package com.mobo.xddemo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 请求加密工具类
 *
 * @author Mobo
 * @create 2020-11-12-15:46
 */
public class RequestEncoder {
    private static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final String MD5 = "MD5";
    public static final String SHA1 = "SHA1";

    public RequestEncoder() {
    }

    public static String encode(String algorithm, String str) throws NoSuchAlgorithmException {
        if (str == null) {
            return null;
        } else {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        }
    }

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);

        for(int j = 0; j < len; ++j) {
            buf.append(HEX_DIGITS[bytes[j] >> 4 & 15]);
            buf.append(HEX_DIGITS[bytes[j] & 15]);
        }

        return buf.toString();
    }

    public static String formatRequest(Map<String, Object> data) {
        Set<String> keySet = data.keySet();
        Iterator<String> it = keySet.iterator();
        StringBuffer sb = new StringBuffer();

        while(it.hasNext()) {
            String key = (String)it.next();
            Object value = data.get(key);
            if (value instanceof String) {
                sb.append(key + "=" + value + "&");
            }
        }

        if (sb.length() != 0) {
            return sb.substring(0, sb.length() - 1);
        } else {
            return null;
        }
    }
}