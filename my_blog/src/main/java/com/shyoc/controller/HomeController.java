package com.shyoc.controller;

import com.github.pagehelper.PageInfo;
import com.shyoc.pojo.Blog;
import com.shyoc.pojo.Result;
import com.shyoc.pojo.Tag;
import com.shyoc.pojo.Type;
import com.shyoc.service.BlogService;
import com.shyoc.service.TagService;
import com.shyoc.service.TypeService;
import com.shyoc.utils.RedisUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/findBlogList")
    @ApiOperation("首页查询博客的分页列表")
    public Result findBlogList(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        PageInfo<Blog> homeBlogList = blogService.findHomeBlogList(currentPage, pageSize);
        homeBlogList.getList().forEach(blog -> {
            String key = "views::blogId-" + blog.getId();
            blog.setViews(redisUtil.pfcount(key));
        });
        return Result.builder()
                .statusCode(HttpStatus.OK.value())
                .flag(true)
                .data(homeBlogList)
                .build();
    }

    @GetMapping("/findTypeList")
    @ApiOperation("查询首页分类列表博客最多前六条")
    public Result findTypeList(){
        List<Type> topTypeList = typeService.findTopTypeList(6);
        return Result.builder()
                .statusCode(HttpStatus.OK.value())
                .flag(true)
                .data(topTypeList)
                .build();
    }

    @GetMapping("/findTagList")
    @ApiOperation("查询首页标签列表博客最多前十条")
    public Result findTagList() {
        List<Tag> topTagList = tagService.findTopTagList(10);
        return Result.builder()
                .statusCode(HttpStatus.OK.value())
                .flag(true)
                .data(topTagList)
                .build();
    }

    @GetMapping("/findRecommendList")
    @ApiOperation("查询首页最新推荐博客列表前六条")
    public Result findRecommendList() {
        List<Blog> topRecommendBlogList = blogService.findTopRecommendBlogList(6);
        return Result.builder()
                .statusCode(HttpStatus.OK.value())
                .flag(true)
                .data(topRecommendBlogList)
                .build();
    }

    @GetMapping("/findSearchBlogList")
    @ApiOperation("查询符合搜索条件的博客列表")
    public Result findSearchBlogList(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                     @RequestParam(value = "search", defaultValue = "") String search) {
        PageInfo<Blog> searchBlog = blogService.findSearchBlog(currentPage, pageSize, search);
        searchBlog.getList().forEach(blog -> {
            String key = "blogId::" + blog.getId();
            blog.setViews(redisUtil.pfcount(key));
        });
        return Result.builder()
                .statusCode(HttpStatus.OK.value())
                .flag(true)
                .data(searchBlog)
                .build();
    }
}
