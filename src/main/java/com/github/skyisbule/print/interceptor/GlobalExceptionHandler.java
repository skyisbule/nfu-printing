package com.github.skyisbule.print.interceptor;

import com.github.skyisbule.print.domain.ExceptionEnty;
import com.github.skyisbule.print.exception.GlobalException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ExceptionEnty jsonErrorHandle(GlobalException exception){
        String errCode = exception.getMessage();
        return new ExceptionEnty(1,"222");
    }

}
