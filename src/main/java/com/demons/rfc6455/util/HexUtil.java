package com.demons.rfc6455.util;

/**
 * @author dongwei
 * @since 2019/12/19
 * Time: 11:42
 */
public class HexUtil {

    private static final char[] HEX_CHAR_ARRAY = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

    private static final String HEX_STR = "0123456789abcdef";

    /**
     * 字节数组转字符
     * @param btArr
     * @return
     */
    public static String byteArrToHex(byte[] btArr) {
        char[] strArr = new char[btArr.length * 2];
        int i = 0;
        for (byte bt : btArr) {
            strArr[i++] = HEX_CHAR_ARRAY[bt>>>4 & 0xf];
            strArr[i++] = HEX_CHAR_ARRAY[bt & 0xf];
        }
        return new String(strArr);
    }

    /**
     * 字符转字节数组
     * @param hexStr
     * @return
     */
    public static byte[] hexToByteArr(String hexStr) {
        char[] charArr = hexStr.toCharArray();
        byte[] btArr = new byte[charArr.length / 2];
        int index = 0;
        for (int i = 0; i < charArr.length; i++) {
            int highBit = HEX_STR.indexOf(charArr[i]);
            int lowBit = HEX_STR.indexOf(charArr[++i]);
            btArr[index] = (byte) (highBit << 4 | lowBit);
            index++;
        }
        return btArr;
    }
}
