package edu.zust.se.graduate.enums;



/**
 * @author 潘谦睿
 */

public enum EventStageEnum {
    //事件阶段(1:发起中,2:待审批,3:募捐中,4:已截止,5:公示中,6:已结束,7:复核中,-1:异常)
    START(1,"发起中"),
    PENDING(2,"待审批"),
    PROGRESS(3,"募捐中"),
    CLOSED(4,"已截止"),
    PUBLIC(5,"公示中"),
    COMPLETED(6,"已结束"),
    REVIEW(7,"复核中"),
    ABNORMAL(-1,"异常");

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
