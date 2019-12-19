package com.demons.rfc6455.util;

import com.demons.rfc6455.HttpRequest;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.sun.org.apache.regexp.internal.RE;

import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author dongwei
 * @since 2019/12/13
 * Time: 14:18
 */
public class HttpRequestParser {

    /**
     * 匹配请求头 正则
     */
    private static final Pattern HEADER_PATTERN = Pattern.compile("(.*?):(.*)");
    /**
     * 请求行
     */
    public static final Pattern REQUEST_LINE_PATTERN = Pattern.compile("(GET|POST|HEAD|PUT|DELETE|OPTIONS|TRACE|PATCH)\\s(.*?)\\s(http\\/[0-9].[0-9])",Pattern.CASE_INSENSITIVE);

    /**
     * 解析http请求头
     * @param httpMsg http请求报文
     * @return HttpRequest
     */
    public static HttpRequest parse(String httpMsg){
        HttpRequest request = new HttpRequest();
        Map<String,String> header = Maps.newHashMap();
        int i = 0;
        Iterable<String> split = Splitter.on("\r\n").split(httpMsg);
        for (String line : split) {
            if(i==0){
                request.setRequestLine(line);
                Matcher matcher = REQUEST_LINE_PATTERN.matcher(line);
                if(matcher.find()){
                    request.setMethod(matcher.group(1));
                    request.setUrl(matcher.group(2));
                    request.setProtocol(matcher.group(3));
                }
            }else {
                Matcher matcher = HEADER_PATTERN.matcher(line);
                if(matcher.find()){
                    header.put(matcher.group(1),matcher.group(2).replace(" ",""));
                }
                request.setHeader(header);
            }
            i++;
        }
        return request;
    }
}
