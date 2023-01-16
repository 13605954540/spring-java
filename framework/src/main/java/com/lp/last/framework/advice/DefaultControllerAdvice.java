package com.lp.last.framework.advice;

import com.lp.last.framework.base.BaseResp;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log
@ControllerAdvice
public class DefaultControllerAdvice extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public BaseResp exceptionHandler(Exception ex, WebRequest request) {
        log.info(ex.getMessage());
        return BaseResp.of(ex.getMessage());
    }
}
