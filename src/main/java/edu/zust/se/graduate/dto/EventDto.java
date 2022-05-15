package edu.zust.se.graduate.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import edu.zust.se.graduate.entity.Event;
import edu.zust.se.graduate.entity.Files;
import edu.zust.se.graduate.entity.Images;
import edu.zust.se.graduate.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 潘谦睿
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "EventDto", description = "事件Dto")
public class EventDto extends Event {


    @ApiModelProperty(value = "图片id数组")
    private Long[] images;

    @ApiModelProperty(value = "详情图片")
    private List<Files> filesList;

    @ApiModelProperty(value = "募捐用户信息")
    private User submitUser;
}
