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
@JsonIgnoreProperties(value = {"handler"})
@ApiModel("归档博客列表结果存储实体类")
public class Archives implements Serializable, Comparable<Archives>  {
    @ApiModelProperty("年份")
    private String year;

    @ApiModelProperty("该年份下的博客列表")
    private List<Blog> blogList;


    @Override
    public int compareTo(Archives archives) {
        return archives.getYear().compareTo(this.getYear());
    }
}
