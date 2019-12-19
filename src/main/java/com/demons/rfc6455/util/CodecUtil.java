package com.demons.rfc6455.util;

import com.google.common.base.Strings;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author dongwei
 * @since 2019/12/19
 * Time: 10:19
 */
public class CodecUtil {

    private static final Base64.Encoder ENCODER = Base64.getEncoder();

    /**
     * 获取sha1 散列值  已字符串形式返回
     * @param str
     * @return
     */
    public static String getSha1(String str) {
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes(StandardCharsets.UTF_8));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] getSha1Bytes(String str) {
        MessageDigest mdTemp = null;
        try {
            mdTemp = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        }
        mdTemp.update(str.getBytes(StandardCharsets.UTF_8));
        return mdTemp.digest();
    }

    /**
     * base64 编码
     * @param original 字符
     * @return
     */
    public static String base64Encoding(String original){
        if(Strings.isNullOrEmpty(original)){
            return "";
        }
        byte[] textByte = original.getBytes(StandardCharsets.UTF_8);
        return ENCODER.encodeToString(textByte);
    }

    /**
     *
     * @param original 字节数组
     * @return
     */
    public static String base64Encoding(byte[] original){
        return ENCODER.encodeToString(original);
    }
}
