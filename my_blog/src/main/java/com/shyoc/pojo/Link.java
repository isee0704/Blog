package com.shyoc.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@JsonIgnoreProperties(value = {"handler"})
@ApiModel("友情链接实体类")
public class Link implements Serializable {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("链接地址")
    private String url;
    @ApiModelProperty("图片地址")
    private String picture;
    @ApiModelProperty("站点名称")
    private String name;
    @ApiModelProperty("是否允许添加")
    private Boolean allow;
    @ApiModelProperty("创建时间")
    private Long createTime;
}
