package com.demons.rfc6455;

/**
 * @author dongwei
 * @since 2019/12/12
 * Time: 17:59
 * 请求头字段信息
 */
public class Headers {

    /**
     * MUST
     */
    public static final String HOST = "Host";
    /**
     * MUST if browser
     */
    public static final String ORIGIN = "Origin";
    public static final String PRAGMA = "Pragma";
    /**
     * MAY
     */
    public static final String SEC_WEBSOCKET_EXTENSIONS = "Sec-WebSocket-Extensions";
    /**
     * MUST 握手请求头
     */
    public static final String SEC_WEBSOCKET_KEY = "Sec-WebSocket-Key";
    /**
     * MUST
     */
    public static final String SEC_WEBSOCKET_VERSION = "Sec-WebSocket-Version";
    /**
     * MUST
     */
    public static final String UPGRADE = "Upgrade";
    public static final String USER_AGENT = "User-Agent";
    /**
     * MUST
     */
    public static final String CONNECTION = "Connection";

    /**
     * MUST 握手响应头
     */
    public static final String SEC_WEBSOCKET_ACCEPT = "Sec-WebSocket-Accept";
}
