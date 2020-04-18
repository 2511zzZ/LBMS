package com.zzz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "用户名不存在")
public class NullUsernameException extends Exception {

    public NullUsernameException(){
        super();
    }

    public NullUsernameException(String msg){
        super(msg);
    }
}
