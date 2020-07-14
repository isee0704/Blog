package com.shyoc.controller;

import com.shyoc.pojo.Link;
import com.shyoc.pojo.Result;
import com.shyoc.service.LinkService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @GetMapping("/findAllowedLinkList")
    @ApiOperation("查询所有已允许的友情链接")
    public Result findAllowedLinkList() {
        List<Link> allowedLinkList = linkService.findAllowedLinkList();
        return Result.builder()
                .flag(true)
                .statusCode(HttpStatus.OK.value())
                .data(allowedLinkList)
                .build();
    }

    @PutMapping("/saveLink")
    @ApiOperation("上传友情链接")
    public Result saveLink(@RequestBody Link link) {
        Integer saveLink = linkService.saveLink(link);
        return Result.builder()
                .flag(true)
                .statusCode(HttpStatus.OK.value())
                .data(saveLink)
                .build();
    }
}
