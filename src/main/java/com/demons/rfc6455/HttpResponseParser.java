package com.demons.rfc6455;

import com.demons.rfc6455.constant.Constants;
import com.demons.rfc6455.constant.StatusCode;
import com.demons.rfc6455.util.CodecUtil;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author dongwei
 * @since 2019/12/19
 * Time: 09:47
 */
public class HttpResponseParser {

    public static HttpResponse processHandshakeResponse(HttpRequest httpRequest) {
        HttpResponse response = new HttpResponse();
        response.setResponseLine("HTTP/1.1 101 Switching Protocols");
        response.setStatusCode(StatusCode.SWITCHING_PROTOCOLS);
        response.setStatusDescription("Switching Protocols");

        Map<String, String> header = Maps.newHashMap();
        header.put(Headers.UPGRADE, "websocket");
        header.put(Headers.CONNECTION, "Upgrade");
        String secWebsocketKey = httpRequest.getHeader().get(Headers.SEC_WEBSOCKET_KEY);
        String concat = secWebsocketKey + Constants.MAGIC_SEQUENCE;
        byte[] sha1 = CodecUtil.getSha1Bytes(concat);
        String base64Encoding = CodecUtil.base64Encoding(sha1);
        header.put(Headers.SEC_WEBSOCKET_ACCEPT, base64Encoding);

        response.setHeader(header);
        return response;
    }

    public static String parseToLines(HttpRequest httpRequest) {
        StringBuilder sb = new StringBuilder();
        HttpResponse response = processHandshakeResponse(httpRequest);
        StringBuilder append = sb.append(response.getResponseLine()).append("\r\n");
        for (Map.Entry<String, String> entry : response.getHeader().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            append.append(key).append(":").append(value).append("\r\n");
        }
        append.append("\r\n");
        return append.toString();
    }

    public static byte[] parseToBytes(HttpRequest httpRequest) {
        String lines = parseToLines(httpRequest);
        return lines.getBytes(StandardCharsets.UTF_8);
    }

}
