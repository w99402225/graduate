package edu.zust.se.graduate.enums;



/**
 * @author 潘谦睿
 */

public enum EventTypeEnum {
    //事件类型(1:自然灾害,2:意外事故,3:爱心捐赠,4:个人募捐,9:所有)
    NATURAL(1,"自然灾害"),
    ACCIDENT(2,"意外事故"),
    CHARITY(3,"爱心捐赠"),
    PERSONAL(4,"个人募捐"),
    ALL(9,"所有");

    private Integer status;

    private String stateinfo;

    private EventTypeEnum(Integer status, String stateinfo) {
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

    public static EventTypeEnum stateOf(int index) {
        for (EventTypeEnum statusEnum : values()) {
            if (statusEnum.getStatus() == index) {
                return statusEnum;
            }
        }
        return null;
    }
}
