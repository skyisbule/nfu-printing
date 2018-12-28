package com.github.skyisbule.print.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.hutool.json.JSONUtil;
import com.github.skyisbule.print.domain.RequireCodePicCache;
import com.github.skyisbule.print.domain.User;
import com.github.skyisbule.print.domain.WarehouseCodePicCache;
import com.github.skyisbule.print.service.QiniuService;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

/**
 * 微信小程序用户接口
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RestController
@RequestMapping("/wechat/user")
public class WxMaUserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WxMaService wxService;

    @Autowired
    private UserService userService;

    @Autowired
    private QiniuService qiniuService;

    @Autowired
    RequireCodePicCacheMapper requireCodePicCacheMapper;

    @Autowired
    WarehouseCodePicCacheMapper warehouseCodePicCacheMapper;

   // @Autowired
   // private WxMaQrcodeService qrcodeService;

    //@RequestMapping("/getAccessToken")
    public String getToken() throws WxErrorException {
        return wxService.getAccessToken();
    }



    @RequestMapping("/createCodeRequire")
    public String createCodeRequire(int rid) throws WxErrorException {
        RequireCodePicCache cache = requireCodePicCacheMapper.selectByPrimaryKey(rid);
        if (cache!=null)
            return cache.getUrl();
        File file = wxService.getQrcodeService().createWxaCodeUnlimit(
                String.valueOf(rid),
                "pages/needDetail/needDetail"
                  );
        String url =  qiniuService.doUpload(file.getPath());
        cache = new RequireCodePicCache();
        cache.setRid(rid);
        cache.setUrl(url);
        requireCodePicCacheMapper.insert(cache);
        return url;
    }

    @RequestMapping("/createCodeWarehouse")
    public String createCodeWarehouse(int wid) throws WxErrorException {
        WarehouseCodePicCache cache = warehouseCodePicCacheMapper.selectByPrimaryKey(wid);
        if (cache!=null)
            return cache.getUrl();
        File file = wxService.getQrcodeService().createWxaCodeUnlimit(
                String.valueOf(wid),
                "pages/warehouseDetail/warehouseDetail"
        );
        String url = qiniuService.doUpload(file.getPath());
        cache = new WarehouseCodePicCache();
        cache.setWid(wid);
        cache.setUrl(url);
        warehouseCodePicCacheMapper.insert(cache);
        return url;
    }

    /**
     * 登陆接口
     */
    @GetMapping("/login")
    public String login(String code) {
        if (StringUtils.isBlank(code)) {
            return "empty jscode";
        }

        try {
            WxMaJscode2SessionResult session = this.wxService.getUserService().getSessionInfo(code);
            this.logger.info(session.getSessionKey());
            this.logger.info(session.getOpenid());
            //插入用户
            User user = new User();
            user.setOpenId(session.getOpenid());
            if (userService.countUserByOpenId(session.getOpenid()) == 0)
                userService.insertUser(user);
            return "{\"openId\":\""+session.getOpenid()+"\"}";
            //return JsonUtils.toJson(session);
        } catch (WxErrorException e) {
            this.logger.error(e.getMessage(), e);
            return e.toString();
        }
    }

    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @GetMapping("/info")
    public String info(String sessionKey, String signature, String rawData, String encryptedData, String iv) {
        // 用户信息校验
        if (!this.wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        // 解密用户信息
        WxMaUserInfo userInfo = this.wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);

        return JSONUtil.parse(userInfo).toString();
    }

    /**
     * <pre>
     * 获取用户绑定手机号信息
     * </pre>

    @GetMapping("/phone")
    public String phone(String sessionKey, String signature, String rawData, String encryptedData, String iv) {
        // 用户信息校验
        if (!this.wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = this.wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);

        return JsonUtils.toJson(phoneNoInfo);
    }
     */
}
