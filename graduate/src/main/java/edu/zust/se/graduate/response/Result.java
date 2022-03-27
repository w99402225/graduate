package edu.zust.se.graduate.response;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;


/**
 *
 * 通用返回结果封装
 */
@ApiModel(value = "响应结果")
public class Result {
    // 返回状态
    @ApiModelProperty(value = "状态码")
    private int status;

    // 编码
    @ApiModelProperty(value = "编码定义，详见CodeConstant")
    private String code;

    // 状态描述
    @ApiModelProperty(value = "消息")
    private String desc;

    // 返回数据集
    @ApiModelProperty(value = "数据集")
    private Object data;

    public Result(HttpStatus status) {
        this.status = status.value();
        this.desc = status.getReasonPhrase();
    }

    public Result(HttpStatus status, JSON jsonObject) {
        this.status = status.value();
        this.desc = status.getReasonPhrase();
        this.data = jsonObject;
    }

    public Result(boolean isSuccess) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (isSuccess) {
            httpStatus = HttpStatus.OK;
        }
        this.status = httpStatus.value();
        this.desc = httpStatus.getReasonPhrase();
    }

    public Result(HttpStatus status, String code, String desc) {
        this.status = status.value();
        this.code = code;
        this.desc = desc;
    }

    public Result(HttpStatus status, String code, String desc, Object data) {
        this.status = status.value();
        this.code = code;
        this.desc = desc;
        this.data = data;
    }

//    public Result(HttpStatus status, String desc, Object object, String name){
//        this.status = status.value();
//        this.desc = desc;
//        this.object = object;
//        this.name = name;
//    }

//    public Result(HttpStatus status, Object object){
//        this.status = status.value();
//        this.object = object;
//    }


    public int getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

//    public JSON getData() {
//        return data;
//    }

//    public void setData(JSON data){
//        this.data = data;
//    }

    public boolean isSuccess() {
        return this.status == HttpStatus.OK.value();
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
