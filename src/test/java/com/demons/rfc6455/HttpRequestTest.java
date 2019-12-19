package com.demons.rfc6455;

import com.demons.rfc6455.util.HttpRequestParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;

/**
 * @author dongwei
 * @since 2019/12/13
 * Time: 17:38
 */
public class HttpRequestTest {
    String string = "GET /echo HTTP/1.1\r\n" +
            "Host: localhost\r\n" +
            "Connection: Upgrade\r\n" +
            "Pragma: no-cache\r\n" +
            "Cache-Control: no-cache\r\n" +
            "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36\r\n" +
            "Upgrade: websocket\r\n" +
            "Origin: file://\r\n" +
            "Sec-WebSocket-Version: 13\r\n" +
            "Accept-Encoding: gzip, deflate, br\r\n" +
            "Accept-Language: zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7\r\n" +
            "Sec-WebSocket-Key: EzCjVtmFUZlipRWh+HsFNA==\r\n" +
            "Sec-WebSocket-Extensions: permessage-deflate; client_max_window_bits";
    @Test
    public void parseHeaderTest(){
        HttpRequest request = HttpRequestParser.parse(string);
        Assert.assertEquals("GET /echo HTTP/1.1",request.getRequestLine());
        Assert.assertEquals(12L,request.getHeader().size());
        Assert.assertEquals("localhost",request.getHeader().get(Headers.HOST));
    }
    @Test
    public void parseRequestLineTest(){
        String string = "GET /echo HTTP/1.1";
        Matcher matcher = HttpRequestParser.REQUEST_LINE_PATTERN.matcher(string);
        System.out.println(matcher.find());
    }

    @Test
    public void parseResponseTest(){
        HttpRequest request = HttpRequestParser.parse(string);
        String lines = HttpResponseParser.parseToLines(request);
        System.out.println(lines);

    }
}
