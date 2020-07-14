package com.shyoc.controller;

import com.shyoc.pojo.Result;
import com.shyoc.pojo.UVStatistic;
import com.shyoc.service.UVStatisticService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/statistic")
public class UVStatisticController {
    @Autowired
    private UVStatisticService uvStatisticService;

    @GetMapping("/findStatistic")
    @ApiOperation("获取最近recent天的独立访客统计数量")
    public Result findStatistic(@RequestParam(value = "recent", defaultValue = "7") Integer recent) {
        UVStatistic statistic = uvStatisticService.getStatistic(recent);
        return Result.builder()
                .flag(true)
                .statusCode(HttpStatus.OK.value())
                .data(statistic)
                .build();
    }
}
