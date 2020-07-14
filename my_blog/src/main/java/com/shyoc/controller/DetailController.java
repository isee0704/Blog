package com.shyoc.controller;

import com.shyoc.pojo.Blog;
import com.shyoc.pojo.Result;
import com.shyoc.service.BlogService;
import com.shyoc.utils.IpAddressUtils;
import com.shyoc.utils.RedisUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/detail")
public class DetailController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/findBlogDetail")
    @ApiOperation("根据博客Id查询博客详情信息")
    public Result findBlogDetail(@RequestParam("id") Long id, HttpServletRequest request){
        Blog blogById = blogService.findAndConvertBlogById(id);
        Long views = pfAddAndCount(blogById, request);
        blogById.setViews(views);
        return Result.builder()
                .statusCode(HttpStatus.OK.value())
                .flag(true)
                .data(blogById)
                .build();
    }

    /**
     * 添加元素并返回访问数量
     * @param blog
     * @return
     */
    public Long pfAddAndCount(Blog blog, HttpServletRequest request) {
        String key = "views::blogId-" + blog.getId();
        String value = IpAddressUtils.getIpAddress(request) + System.currentTimeMillis();
        redisUtil.pfadd(key, value);
        return redisUtil.pfcount(key);
    }
}
