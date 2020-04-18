package com.zzz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "未授权，请登录")
public class UnAuthorizedException extends Exception {

    public UnAuthorizedException(){
        super();
    }

    public UnAuthorizedException(String msg){
        super(msg);
    }
}
