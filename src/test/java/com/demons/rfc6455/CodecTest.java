package com.demons.rfc6455;

import com.demons.rfc6455.util.CodecUtil;
import com.sun.tools.javac.jvm.Code;
import org.junit.Test;

/**
 * @author dongwei
 * @since 2019/12/19
 * Time: 11:24
 */
public class CodecTest {

    @Test
    public void sha1Test(){
        String string = "dGhlIHNhbXBsZSBub25jZQ==258EAFA5-E914-47DA-95CA-C5AB0DC85B11";

        byte[] sha1 = CodecUtil.getSha1Bytes(string);
        String base64Encoding = CodecUtil.base64Encoding(sha1);

        System.out.println(base64Encoding);
    }
}
