package com.shyoc.controller;

import com.shyoc.pojo.Message;
import com.shyoc.pojo.Result;
import com.shyoc.service.MessageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/findMessageList")
    @ApiOperation("查询留言列表")
    public Result findMessageList(){
        List<Message> messageList = messageService.findMessageList();
        return Result.builder()
                .flag(true)
                .statusCode(HttpStatus.OK.value())
                .data(messageList)
                .build();
    }

    @PutMapping("/saveMessage")
    @ApiOperation("保存留言内容")
    public Result saveMessage(@RequestBody Message message, HttpServletRequest request) {
        int i = messageService.saveMessage(message, request);
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
