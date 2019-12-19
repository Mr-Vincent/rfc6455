package com.demons.rfc6455;

import com.google.common.base.Objects;

import java.util.Map;

/**
 * @author dongwei
 * @since 2019/12/13
 * Time: 14:17
 */
public class HttpRequest {

    private static final String HTTP_GET= "get";

    private String requestLine;

    private Map<String,String> header;

    private String method;

    private String url;

    private String protocol;

    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRequestLine() {
        return requestLine;
    }

    public void setRequestLine(String requestLine) {
        this.requestLine = requestLine;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
        String[] split = protocol.split("/");
        if(split.length == 2){
            setVersion(split[1]);
        }
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "requestLine='" + requestLine + '\'' +
                ", header=" + header +
                ", method='" + method + '\'' +
                ", url='" + url + '\'' +
                ", protocol='" + protocol + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
