package com.github.skyisbule.print.common;

public class BaseHttpResponse<T> {

    public BaseHttpResponse(int code,String desc,T data){
        this.code = code;
        this.desc = desc;
        this.data = data;
    }

    public BaseHttpResponse(T data) {
        this.data = data;
    }

    private int code = 200;
    private String desc = "success";
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
