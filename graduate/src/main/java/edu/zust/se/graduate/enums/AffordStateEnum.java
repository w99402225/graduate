package edu.zust.se.graduate.enums;



/**
 * @author 潘谦睿
 */

public enum AffordStateEnum {
    //捐款状态(1:待使用,2：已捐赠)
    PENDING(1,"待使用"),
    DONATED(2,"已捐赠");

    private Integer status;

    private String stateinfo;

    private AffordStateEnum(Integer status, String stateinfo) {
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

    public static AffordStateEnum stateOf(int index) {
        for (AffordStateEnum statusEnum : values()) {
            if (statusEnum.getStatus() == index) {
                return statusEnum;
            }
        }
        return null;
    }
}
