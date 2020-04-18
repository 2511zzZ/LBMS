package com.zzz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "权限不足")
public class ForBiddenException extends Exception {

    public ForBiddenException(){
        super();
    }

    public ForBiddenException(String msg){
        super(msg);
    }
}
