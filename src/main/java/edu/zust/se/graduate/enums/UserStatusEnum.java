package edu.zust.se.graduate.enums;



/**
 * @author 潘谦睿
 */

public enum UserStatusEnum {
    //状态：1.正常；2.冻结；3.删除
    NORMAL(1,"正常"),
    FREEZE(2,"冻结"),
    DELETE(3,"删除");

    private Integer status;

    private String stateinfo;

    private UserStatusEnum(Integer status, String stateinfo) {
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

    public static UserStatusEnum stateOf(int index) {
        for (UserStatusEnum statusEnum : values()) {
            if (statusEnum.getStatus() == index) {
                return statusEnum;
            }
        }
        return null;
    }
}
