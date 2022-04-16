package edu.zust.se.graduate.enums;



/**
 * @author 潘谦睿
 */

public enum EventStageEnum {
    //事件阶段(1:进行中,2:已截止,3:公示中,4:已结束,5:复核中,6:异常)
    NATURAL(1,"自然灾害"),
    ACCIDENT(2,"意外事故"),
    CHARITY(3,"爱心捐赠"),
    PERSONAL(4,"个人募捐");

    private Integer status;

    private String stateinfo;

    private EventStageEnum(Integer status, String stateinfo) {
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

    public static EventStageEnum stateOf(int index) {
        for (EventStageEnum statusEnum : values()) {
            if (statusEnum.getStatus() == index) {
                return statusEnum;
            }
        }
        return null;
    }
}
