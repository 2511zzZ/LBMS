package com.zzz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "无法识别的操作")
public class BadOperationException extends Exception {

    public BadOperationException(){
        super();
    }

    public BadOperationException(String msg){
        super(msg);
    }
}
