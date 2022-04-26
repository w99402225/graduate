package edu.zust.se.graduate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @author 潘谦睿
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="用户")
public class User extends Model<User> {

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "密码")
    private String password;

//    @JsonIgnore
//    @ApiModelProperty(value = "确认密码")
//    private String confirmPassword;

    @ApiModelProperty(value = "手机号码")
    private String telephone;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @ApiModelProperty(value = "状态：1.冻结；2.正常；3.删除")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateId;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "用户身份(1:普通用户,2:操作员,3:审查员,4:管理员)")
    private Integer userType;

    @ApiModelProperty(value = "余额")
    private Double balance;

    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime lastLoginTime;

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
