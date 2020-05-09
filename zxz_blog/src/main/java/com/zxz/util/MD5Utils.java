package com.zxz.util;


import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * Created by limi on 2017/10/15.
 */
public class MD5Utils {
    private static final String type = "md5";

    private static final String salt = "zxz";

    private static final int hash_iteration = 2;

    /**
     * MD5加密类
     *
     * @param pswd 要加密的字符串
     * @return 加密后的字符串
     */
    public static String code(String username, String pswd) {
        String newPassword = new SimpleHash(type, pswd, ByteSource.Util.bytes(username + salt), hash_iteration).toHex();
        return newPassword;
    }

    public static void main(String[] args) {
        String pas=MD5Utils.code("320761248@qq.com","123");
        System.out.println(pas);
    }


}
