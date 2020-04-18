package com.zzz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "日期格式错误")
public class BadDateFormatException extends Exception {

    public BadDateFormatException(){
        super();
    }

    public BadDateFormatException(String msg){
        super(msg);
    }
}
