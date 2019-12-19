package com.demons.rfc6455.util;

import com.demons.rfc6455.Headers;
import com.demons.rfc6455.HttpRequest;
import com.demons.rfc6455.exception.WebsocketException;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;
import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.demons.rfc6455.Headers.*;

/**
 * @author dongwei
 * @since 2019/12/17
 * Time: 17:00
 */
public class Checker {

    /**
     * http版本至少为1.1
     */
    private static final double LEAST_HTTP_VERSION = 1.1D;

    /**
     * 固定长度  随机选取16字节进行base64 转化后的长度为24字节 16*(1/3)=24
     */
    private static final int SEC_WEBSOCKET_KEY_LENGTH = 24;

    /**
     * 版本  必须为13
     */
    private static final int WEBSOCKET_VERSION = 13;

    /**
     * upgrade 头中要包含关键字websocket
     */
    private static final String UPGRADE_KEY_WORD = "websocket";

    /**
     * connection 头包含关键字Upgrade
     */
    private static final String CONNECTION_TOKEN = "Upgrade";

    private static final Pattern HOST_PORT = Pattern.compile("[a-zA-Z\\.0-9]+",Pattern.CASE_INSENSITIVE);

    /**
     * 检验websocket握手信息
     * @param request
     */
    public static void header(HttpRequest request){
        HttpRequest req = Optional.ofNullable(request).orElseThrow(RuntimeException::new);
        String version = req.getVersion();
        if(Double.parseDouble(version) < LEAST_HTTP_VERSION){
            throw new WebsocketException("http version MUST be at least 1.1");
        }
        if(Strings.isNullOrEmpty(req.getUrl())){
            throw new WebsocketException("http request url MUST NOT be null");
        }
        if(Objects.isNull(req.getHeader()) || req.getHeader().size() <= 0){
            throw new WebsocketException("http header cannot be empty");
        }

        Map<String, String> header = req.getHeader();
        if(Strings.isNullOrEmpty(header.get(HOST))){
            throw new WebsocketException("http header |host| field cannot be empty");
        }
        Matcher matcher = HOST_PORT.matcher(header.get(HOST));
        if(!matcher.matches()){
            throw new WebsocketException("http header |host| field is invalid");
        }

        if(Strings.isNullOrEmpty(header.get(UPGRADE))){
            throw new WebsocketException("http header |Upgrade| field cannot be empty");
        }
        if(!header.get(UPGRADE).toLowerCase().contains(UPGRADE_KEY_WORD)){
            throw new WebsocketException("http header |Upgrade| value MUST include key word 'websocket'");
        }
        if(Strings.isNullOrEmpty(header.get(CONNECTION))){
            throw new WebsocketException("http header |Connection| field cannot be empty");
        }
        if(!header.get(CONNECTION).toLowerCase().contains(CONNECTION_TOKEN.toLowerCase())){
            throw new WebsocketException("http header |Connection| value MUST include key word 'Upgrade'");
        }
        if(Strings.isNullOrEmpty(header.get(SEC_WEBSOCKET_KEY))){
            throw new WebsocketException("http header |Sec_WebSocket_Key| field cannot be empty");
        }
        if(header.get(SEC_WEBSOCKET_KEY).length() != SEC_WEBSOCKET_KEY_LENGTH){
            throw new WebsocketException("http header |Sec_WebSocket_Key| value is invalid");
        }
        if(Strings.isNullOrEmpty(header.get(SEC_WEBSOCKET_VERSION))){
            throw new WebsocketException("http header |Sec_WebSocket_Version| field cannot be empty");
        }
        if(Integer.parseInt(header.get(SEC_WEBSOCKET_VERSION)) != WEBSOCKET_VERSION){
            throw new WebsocketException("http header |Sec_WebSocket_Version| value must be 13");
        }

    }
}
