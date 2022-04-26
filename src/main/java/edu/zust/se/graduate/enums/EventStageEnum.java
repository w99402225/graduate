package edu.zust.se.graduate.enums;



/**
 * @author 潘谦睿
 */

public enum EventStageEnum {
    //事件阶段(1:发起中,2:进行中,3:已截止,4:公示中,5:已结束,6:复核中,-1:异常)
    START(1,"发起中"),
    PROGRESS(2,"进行中"),
    CLOSED(3,"已截止"),
    PUBLIC(4,"公示中"),
    COMPLETED(5,"已结束"),
    REVIEW(6,"复核中"),
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
