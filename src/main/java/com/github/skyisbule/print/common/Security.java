package com.github.skyisbule.print.common;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

public class Security {

    //密钥
    private static byte[] key = "smkldospdosldaaasmkldospdosldaaa".getBytes();

    public static String encode(String str) {
        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        //加密为16进制表示
        return aes.encryptHex(str);
        //解密为字符串

    }

    public static String decode(String str) {
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        //解密
        return aes.decryptStr(str, CharsetUtil.CHARSET_UTF_8);
    }

    public static void main(String[] a){
        String as = "asdfdsafdsaf";
        System.out.println(encode(as));
        System.out.println(decode(encode(as)));
    }
}
