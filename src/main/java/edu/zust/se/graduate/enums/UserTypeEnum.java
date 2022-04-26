package edu.zust.se.graduate.enums;



/**
 * @author 潘谦睿
 */

public enum UserTypeEnum {
    //用户身份(1:普通用户,2:操作员,3:审查员,4:管理员)
    NORMAL(1,"普通用户"),
    OPERATION(2,"操作员"),
    REVIEW(3,"审查员"),
    ADMIN(4,"管理员");

    private Integer status;

    private String stateinfo;

    private UserTypeEnum(Integer status, String stateinfo) {
        this.status = status;
        this.stateinfo = stateinfo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStateinfo() {
        return stateinfo;
    }

    public void setStateinfo(String stateinfo) {
        this.stateinfo = stateinfo;
    }

    public static UserTypeEnum stateOf(int index) {
        for (UserTypeEnum statusEnum : values()) {
            if (statusEnum.getStatus() == index) {
                return statusEnum;
            }
        }
        return null;
    }
}
