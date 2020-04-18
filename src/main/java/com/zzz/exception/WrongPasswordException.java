package com.zzz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "密码错误")
public class WrongPasswordException extends Exception {

    public WrongPasswordException(){
        super();
    }

    public WrongPasswordException(String msg){
        super(msg);
    }
}
