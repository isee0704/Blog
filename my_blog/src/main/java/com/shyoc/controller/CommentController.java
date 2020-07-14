package com.shyoc.controller;

import com.shyoc.pojo.Comment;
import com.shyoc.pojo.Result;
import com.shyoc.service.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/findCommentListByBlogId")
    @ApiOperation("通过博客Id查询博客下的评论列表")
    public Result findCommentListByBlogId(@RequestParam("blogId") Long blogId){
        List<Comment> commentList = commentService.findCommentListByBlogId(blogId);
        return Result.builder()
                .flag(true)
                .statusCode(HttpStatus.OK.value())
                .data(commentList)
                .build();
    }

    @PutMapping("/saveComment")
    @ApiOperation("保存评论内容")
    public Result saveComment(@RequestBody Comment comment, HttpServletRequest request) {
        int i = commentService.saveComment(comment, request);
        if(i != 0){
            return Result.builder()
                    .flag(true)
                    .statusCode(HttpStatus.OK.value())
                    .data(i)
                    .build();
        }
        return Result.builder()
                .errorMsg("保存评论失败，请联系管理员")
                .build();
    }
}
