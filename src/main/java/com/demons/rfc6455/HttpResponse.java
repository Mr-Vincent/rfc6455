package com.demons.rfc6455;

import java.util.Map;

/**
 * @author dongwei
 * @since 2019/12/18
 * Time: 18:00
 */
public class HttpResponse {
    /**
     * 响应行
     */
    private String responseLine;

    /**
     * 响应码
     */
    private Integer statusCode;

    /**
     * 响应描述
     */
    private String statusDescription;

    public String getResponseLine() {
        return responseLine;
    }

    public void setResponseLine(String responseLine) {
        this.responseLine = responseLine;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    /**
     * 响应头
     */
    private Map<String,String> header;


    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

}
