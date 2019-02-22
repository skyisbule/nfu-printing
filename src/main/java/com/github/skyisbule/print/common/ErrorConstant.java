package com.github.skyisbule.print.common;

public class ErrorConstant {

    /**权限不足**/
    public static final String NO_PERMISSION           = "00001";
    /**用户传参错误**/
    public static final String PARAM_ERROR             = "00002";

    /**用户名已存在**/
    public static final String ACCOUNT_ALREADY_EXISTS  = "10001";
    /**用户名不存在**/
    public static final String USER_NOT_EXISTS         = "10002";
    /**用户名不存在或密码错误**/
    public static final String ACCOUNT_OR_PASSWD_ERROR = "10003";
    /**登陆状态失效**/
    public static final String FAILURE_OF_LOGIN_STATUS = "10004";
    /**用户不存在**/
    public static final String UID_NOT_EXISTS          = "10005";

    /**您删除的不是自己的文件**/
    public static final String DELETE_OTHERS_FILE      = "20001";

    /**该标签名已经有啦**/
    public static final String TAG_HAD_SAVED           = "30001";

    /**店铺id为空**/
    public static final String SHOP_ID_IS_NULL         = "40001";
}
