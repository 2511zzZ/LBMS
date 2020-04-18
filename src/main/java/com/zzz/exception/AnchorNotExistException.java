package com.zzz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "主播不存在")
public class AnchorNotExistException extends Exception {

    public AnchorNotExistException(){
        super();
    }

    public AnchorNotExistException(String msg){
        super(msg);
    }
}
