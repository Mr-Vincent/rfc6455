package com.demons.rfc6455.exception;

/**
 * @author dongwei
 * @since 2019/12/18
 * Time: 09:57
 */
public class WebsocketException extends RuntimeException {

    public WebsocketException(){
        super();
    }

    public WebsocketException(String message){
        super(message);
    }

    public WebsocketException(String message, Throwable cause) {
        super(message, cause);
    }

}
