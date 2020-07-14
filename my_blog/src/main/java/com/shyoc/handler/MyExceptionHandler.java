package com.shyoc.handler;

import com.shyoc.exception.NotFoundException;
import com.shyoc.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestControllerAdvice
public class MyExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        logger.error("Request URL: {}, Response Status_Code: {}, Exception: {}", request.getRequestURL(), response.getStatus(), e);
        if(e instanceof NoHandlerFoundException || e instanceof NotFoundException){
            return Result.builder()
                    .flag(false)
                    .errorMsg(e.getMessage())
                    .statusCode(404)
                    .build();
        }else{
            return Result.builder()
                    .flag(false)
                    .errorMsg(e.getMessage())
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();
        }
    }
}
