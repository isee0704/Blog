package com.shyoc.controller;

import com.shyoc.pojo.Result;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@ApiIgnore
public class NotFoundErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";
    @RequestMapping(ERROR_PATH)
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    public Result handleError() {
        return Result.builder()
                .flag(false)
                .statusCode(HttpStatus.NOT_FOUND.value())
                .errorMsg("访问路径不存在")
                .build();
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
