package com.shyoc.controller;

import com.shyoc.pojo.Archives;
import com.shyoc.pojo.Result;
import com.shyoc.service.BlogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@RequestMapping("/archives")
public class ArchivesController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/findArchivesBlogList")
    @ApiOperation("获取归档页面博客时间分组映射")
    public Result findArchivesBlogList() {
        List<Archives> archivesList = blogService.findArchivesBlogList();
        Integer count = 0;
        for(Archives archive: archivesList) {
            count += archive.getBlogList().size();
        }
        Map<String, Object> realMap = new HashMap<>(2);
        realMap.put("list", archivesList);
        realMap.put("total", count);
        return Result.builder()
                .statusCode(HttpStatus.OK.value())
                .flag(true)
                .data(realMap)
                .build();
    }
}
