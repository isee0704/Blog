package com.shyoc.controller.profile;

import com.shyoc.annotation.AuthToken;
import com.shyoc.pojo.Link;
import com.shyoc.pojo.Result;
import com.shyoc.service.LinkService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AuthToken
@RequestMapping("/profile")
public class ProfileLinkController {
    @Autowired
    private LinkService linkService;

    @GetMapping("/findLinkList")
    @ApiOperation("查询所有的友情链接")
    public Result findLinkList() {
        List<Link> linkList = linkService.findLinkList();
        return Result.builder()
                .flag(true)
                .statusCode(HttpStatus.OK.value())
                .data(linkList)
                .build();
    }

    @PutMapping("/updateLink")
    @ApiOperation("更新友情链接（设置允许状态）")
    public Result updateLink(@RequestBody Link link) {
        if (link == null || link.getId() == null) {
            throw new RuntimeException("传入数据有误！");
        }
        Integer updateLink = linkService.updateLink(link);
        return Result.builder()
                .flag(true)
                .statusCode(HttpStatus.OK.value())
                .data(updateLink)
                .build();
    }

    @DeleteMapping("/deleteLink")
    @ApiOperation("删除友情链接")
    public Result deleteLink(@RequestParam("id") Long id) {
        try {
            linkService.deleteLink(id);
            return Result.builder()
                    .flag(true)
                    .statusCode(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            return Result.builder()
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .errorMsg(e.getMessage())
                    .build();
        }
    }
}
