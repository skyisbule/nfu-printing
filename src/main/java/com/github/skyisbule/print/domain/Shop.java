package com.github.skyisbule.print.domain;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class Shop implements Serializable {
    @ApiModelProperty("店铺id，这个id和用户id是一样的。")
    private Integer sid;
    @ApiModelProperty("店铺名")
    private String name;
    @ApiModelProperty("西区还是东区")
    private String region;
    @ApiModelProperty("哪栋楼")
    private String roomNumber;
    @ApiModelProperty("标签")
    private String tag;
    @ApiModelProperty("详细介绍")
    private String content;
    @ApiModelProperty("目前是否开店")
    private Integer openUp;

    private static final long serialVersionUID = 1L;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getOpenUp() {
        return openUp;
    }

    public void setOpenUp(Integer openUp) {
        this.openUp = openUp;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Shop other = (Shop) that;
        return (this.getSid() == null ? other.getSid() == null : this.getSid().equals(other.getSid()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getRegion() == null ? other.getRegion() == null : this.getRegion().equals(other.getRegion()))
            && (this.getRoomNumber() == null ? other.getRoomNumber() == null : this.getRoomNumber().equals(other.getRoomNumber()))
            && (this.getTag() == null ? other.getTag() == null : this.getTag().equals(other.getTag()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getOpenUp() == null ? other.getOpenUp() == null : this.getOpenUp().equals(other.getOpenUp()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSid() == null) ? 0 : getSid().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getRegion() == null) ? 0 : getRegion().hashCode());
        result = prime * result + ((getRoomNumber() == null) ? 0 : getRoomNumber().hashCode());
        result = prime * result + ((getTag() == null) ? 0 : getTag().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getOpenUp() == null) ? 0 : getOpenUp().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sid=").append(sid);
        sb.append(", name=").append(name);
        sb.append(", region=").append(region);
        sb.append(", roomNumber=").append(roomNumber);
        sb.append(", tag=").append(tag);
        sb.append(", content=").append(content);
        sb.append(", openUp=").append(openUp);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}