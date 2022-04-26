package edu.zust.se.graduate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author 潘谦睿
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Event对象", description="筹款事件")
public class Event extends Model<Event> {

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "事件名")
    private String name;

    @ApiModelProperty(value = "事件详情")
    private String details;

    @ApiModelProperty(value = "封面图片")
    private String img;

    @ApiModelProperty(value = "详情图片")
    private String images;

    @ApiModelProperty(value = "事件目标筹款金额")
    private Double totalMoney;

    @ApiModelProperty(value = "事件现筹款金额")
    private Double nowMoney;

    @ApiModelProperty(value = "事件发起人id")
    private Long submitUserId;

    @ApiModelProperty(value = "事件操作员id")
    private Long operationUserId;

    @ApiModelProperty(value = "事件审查员id")
    private Long reviewUserId;

    @ApiModelProperty(value = "事件发起时间")
    private LocalDateTime startDate;

    @ApiModelProperty(value = "事件更新时间")
    private LocalDateTime updateDate;

    @ApiModelProperty(value = "筹款天数")
    private Integer raiseDay;

    @ApiModelProperty(value = "事件截止时间")
    private LocalDateTime endDate;

    @ApiModelProperty(value = "事件优先级")
    private Integer priority;

    @ApiModelProperty(value = "事件类型(1:自然灾害,2:意外事故,3:爱心捐赠,4:个人募捐)")
    private Integer type;

    @ApiModelProperty(value = "事件阶段(1:进行中,2:已截止,3:公示中,4:已结束,5:复核中,6:异常)")
    private Integer stage;

    @ApiModelProperty(value = "删除标记(-1:删除，0:未删除)")
    private Integer deleteType;

}
