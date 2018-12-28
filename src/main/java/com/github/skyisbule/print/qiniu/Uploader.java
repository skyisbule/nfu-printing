package com.github.skyisbule.print.qiniu;

import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Uploader {


    @Value("${qiniu.accessKey}")
    private String accessKey;
    @Value("${qiniu.secretKey}")
    private String secretKey;
    @Value("${qiniu.bucket}")
    private String bucket;


    @RequestMapping("/qiniu/upload")
    public String getToken(String picName){
        if (picName==null) return "{\"code\":400,\"dsc\":\"图片名称为空\"}";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket, picName);
        return upToken;
    }

    @RequestMapping("/qiniu/simple")
    public String simpleToken(){
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        return upToken;
    }

}
