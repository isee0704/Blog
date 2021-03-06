package com.shyoc.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@ApiModel("留言实体类")
@JsonIgnoreProperties(value = {"handler"})
public class Message implements Serializable {
    @ApiModelProperty("留言id")
    private Long id;
    @ApiModelProperty("留言创建时间")
    private Long createTime;
    @ApiModelProperty("留言内容")
    private String content;
    @ApiModelProperty("留言标识是否为管理员的留言内容")
    private Boolean adminMessage;
    @ApiModelProperty("父留言的Id")
    private Long parentMsgId;

    @ApiModelProperty("留言用户")
    private User user;
    @ApiModelProperty("该留言的父留言")
    private Message parentMessage;
    @ApiModelProperty("该留言的子留言列表")
    private List<Message> childMessages;

    @ApiModelProperty("该留言下子留言的数目")
    private Integer childMessagesCount;
}
