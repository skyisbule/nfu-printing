package com.github.skyisbule.print.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.skyisbule.print.domain.DbFile;
import com.github.skyisbule.print.domain.User;

import java.util.Date;

public class FileWithUserVo {

    private Integer uid;

    private String nickName;

    private String openId;

    private String sex;

    private String headPic;

    private String wechat;

    private String telNum;

    private Integer openShop;

    private Integer fid;

    private String fileName;

    private String fileUrl;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date uploadTime;

    private Integer isPublic;


    public void buildUser(User user){
        this.nickName = user.getNickName();
        this.openId   = user.getOpenId();
        this.sex      = user.getSex();
        this.headPic  = user.getHeadPic();
        this.wechat   = user.getWechat();
        this.telNum   = user.getTelNum();
        this.openShop = user.getOpenShop();
        this.uid = user.getUid();
    }

    public void buildFile(DbFile file){
        this.fid = file.getFid();
        this.fileName = file.getFileName();
        this.fileUrl  = file.getFileUrl();
        this.uploadTime = file.getUploadTime();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public Integer getOpenShop() {
        return openShop;
    }

    public void setOpenShop(Integer openShop) {
        this.openShop = openShop;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
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

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }
}
