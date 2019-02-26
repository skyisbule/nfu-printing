package com.github.skyisbule.print.vo;

import com.github.skyisbule.print.domain.Order;
import com.github.skyisbule.print.domain.Shop;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class OrderWithShopVO {

    @ApiModelProperty("订单id")
    private Integer oid;
    @ApiModelProperty("商铺id")
    private Integer sid;
    @ApiModelProperty("用户id")
    private Integer uid;
    @ApiModelProperty("文件名")
    private String fileName;
    @ApiModelProperty("文件url")
    private String fileUrl;
    @ApiModelProperty("用户填写的需求")
    private String requirement;
    @ApiModelProperty("创建这条订单的时间")
    private Date uploadTime;
    @ApiModelProperty("前端自定义的标签")
    private String tag;
    @ApiModelProperty("订单状态")
    private Integer status;

    @ApiModelProperty("店铺名")
    private String name;
    @ApiModelProperty("西区还是东区")
    private String region;
    @ApiModelProperty("哪栋楼")
    private String roomNumber;
    @ApiModelProperty("详细介绍")
    private String content;
    @ApiModelProperty("目前是否开店")
    private Integer openUp;

    public static OrderWithShopVO build(Order order, Shop shop){
        OrderWithShopVO vo = new OrderWithShopVO();
        vo.oid = order.getOid();
        vo.sid = order.getSid();
        vo.uid = order.getUid();
        vo.fileName = order.getFileName();
        vo.fileUrl  = order.getFileUrl();
        vo.requirement  = order.getRequirement();
        vo.uploadTime = order.getUploadTime();
        vo.tag = order.getTag();
        vo.status = order.getStatus();
        vo.name = shop.getName();
        vo.region = shop.getRegion();
        vo.roomNumber = shop.getRoomNumber();
        vo.content = shop.getContent();
        vo.openUp = shop.getOpenUp();
        return vo;
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

    private static final long serialVersionUID = 1L;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        Order other = (Order) that;
        return (this.getOid() == null ? other.getOid() == null : this.getOid().equals(other.getOid()))
                && (this.getSid() == null ? other.getSid() == null : this.getSid().equals(other.getSid()))
                && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
                && (this.getFileName() == null ? other.getFileName() == null : this.getFileName().equals(other.getFileName()))
                && (this.getFileUrl() == null ? other.getFileUrl() == null : this.getFileUrl().equals(other.getFileUrl()))
                && (this.getRequirement() == null ? other.getRequirement() == null : this.getRequirement().equals(other.getRequirement()))
                && (this.getUploadTime() == null ? other.getUploadTime() == null : this.getUploadTime().equals(other.getUploadTime()))
                && (this.getTag() == null ? other.getTag() == null : this.getTag().equals(other.getTag()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOid() == null) ? 0 : getOid().hashCode());
        result = prime * result + ((getSid() == null) ? 0 : getSid().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getFileName() == null) ? 0 : getFileName().hashCode());
        result = prime * result + ((getFileUrl() == null) ? 0 : getFileUrl().hashCode());
        result = prime * result + ((getRequirement() == null) ? 0 : getRequirement().hashCode());
        result = prime * result + ((getUploadTime() == null) ? 0 : getUploadTime().hashCode());
        result = prime * result + ((getTag() == null) ? 0 : getTag().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", oid=").append(oid);
        sb.append(", sid=").append(sid);
        sb.append(", uid=").append(uid);
        sb.append(", fileName=").append(fileName);
        sb.append(", fileUrl=").append(fileUrl);
        sb.append(", requirement=").append(requirement);
        sb.append(", uploadTime=").append(uploadTime);
        sb.append(", tag=").append(tag);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

}
