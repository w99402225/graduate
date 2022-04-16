package edu.zust.se.graduate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author 潘谦睿
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Afford对象", description="捐款信息")
public class Afford extends Model<Afford> {

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "事件id")
    private Long eventId;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "捐款金额")
    private Double money;

    @ApiModelProperty(value = "捐款状态")
    private Integer state;

    @ApiModelProperty(value = "捐款目标类型(1:自然灾害,2:意外事故,3:爱心捐赠,4:个人募捐)")
    private Integer eventType;

    @ApiModelProperty(value = "捐款发起时间")
    private Date startTime;

    @ApiModelProperty(value = "捐款支付时间")
    private Date affordTime;

    @ApiModelProperty(value = "支付编号")
    private String affordNo;

    @ApiModelProperty(value = "支付平台")
    private String affordWay;
}
