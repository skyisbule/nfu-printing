package com.github.skyisbule.print.domain;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * db_user
 * @author 
 */
public class User implements Serializable {
    @ApiModelProperty("用户id")
    private Integer uid;
    @ApiModelProperty("账号名，不可重复。")
    private String nickName;
    @ApiModelProperty("密码")
    private String passwd;
    @ApiModelProperty("微信的openid")
    private String openId;
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("头像的url")
    private String headPic;
    @ApiModelProperty("微信号")
    private String wechat;
    @ApiModelProperty("手机号")
    private String telNum;
    @ApiModelProperty("有没有开启自己的店铺")
    private Integer openShop;

    private static final long serialVersionUID = 1L;

    public boolean isAdmin(){
        return this.uid == 1;
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

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
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
        User other = (User) that;
        return (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getNickName() == null ? other.getNickName() == null : this.getNickName().equals(other.getNickName()))
            && (this.getPasswd() == null ? other.getPasswd() == null : this.getPasswd().equals(other.getPasswd()))
            && (this.getOpenId() == null ? other.getOpenId() == null : this.getOpenId().equals(other.getOpenId()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getHeadPic() == null ? other.getHeadPic() == null : this.getHeadPic().equals(other.getHeadPic()))
            && (this.getWechat() == null ? other.getWechat() == null : this.getWechat().equals(other.getWechat()))
            && (this.getTelNum() == null ? other.getTelNum() == null : this.getTelNum().equals(other.getTelNum()))
            && (this.getOpenShop() == null ? other.getOpenShop() == null : this.getOpenShop().equals(other.getOpenShop()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getNickName() == null) ? 0 : getNickName().hashCode());
        result = prime * result + ((getPasswd() == null) ? 0 : getPasswd().hashCode());
        result = prime * result + ((getOpenId() == null) ? 0 : getOpenId().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getHeadPic() == null) ? 0 : getHeadPic().hashCode());
        result = prime * result + ((getWechat() == null) ? 0 : getWechat().hashCode());
        result = prime * result + ((getTelNum() == null) ? 0 : getTelNum().hashCode());
        result = prime * result + ((getOpenShop() == null) ? 0 : getOpenShop().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uid=").append(uid);
        sb.append(", nickName=").append(nickName);
        sb.append(", passwd=").append(passwd);
        sb.append(", openId=").append(openId);
        sb.append(", sex=").append(sex);
        sb.append(", headPic=").append(headPic);
        sb.append(", wechat=").append(wechat);
        sb.append(", telNum=").append(telNum);
        sb.append(", openShop=").append(openShop);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}