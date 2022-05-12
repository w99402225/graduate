package edu.zust.se.graduate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author 潘谦睿
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="File对象", description="文件")
public class Files extends Model<Files> {

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "文件名")
    private String name;

    @ApiModelProperty(value = "文件类型")
    private String type;

    @ApiModelProperty(value = "文件大小")
    private Long size;

    @ApiModelProperty(value = "文件地址")
    private String url;

    @ApiModelProperty(value = "删除标记（-1删除，0正常）")
    private Integer deleteType;

    @ApiModelProperty(value = "文件md5")
    private String md5;

}
