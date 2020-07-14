package com.shyoc.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: u
 * @create: 2020-07-13 9:13 上午
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("邮件发送日志类")
@JsonIgnoreProperties(value = {"handler"})
public class MailSendLog {

    @ApiModelProperty("消息Id")
    private String msgId;
    @ApiModelProperty("评论或留言的id")
    private Long needId;
    @ApiModelProperty("消息发送状态")
    private Integer status;
    @ApiModelProperty("Rabbitmq-routeKey")
    private String routeKey;
    @ApiModelProperty("Rabbitmq-exchange")
    private String exchange;
    @ApiModelProperty("允许重试次数")
    private Integer count;
    @ApiModelProperty("重试开始时间")
    private Date tryTime;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("更新时间")
    private Date updateTime;
    @ApiModelProperty("业务需求，true表示评论，false表示留言")
    private Boolean isComment;
}
