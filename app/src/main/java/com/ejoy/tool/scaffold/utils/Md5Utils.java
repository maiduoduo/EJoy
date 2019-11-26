package com.ejoy.tool.scaffold.utils;
//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃                  神兽保佑
//    ┃　　　┃                  永无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * CN:      Md5Utils
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/10/23
 * Des:    MD5工具
 */
public class Md5Utils {
    public Md5Utils() {
    }

    public static String parseStrToMd5L32(String var0) {
        String var1 = null;

        try {
            MessageDigest var2 = MessageDigest.getInstance("MD5");
            byte[] var3 = var2.digest(var0.getBytes());
            StringBuffer var4 = new StringBuffer();
            byte[] var5 = var3;
            int var6 = var3.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                byte var8 = var5[var7];
                int var9 = var8 & 255;
                if (var9 < 16) {
                    var4.append(0);
                }

                var4.append(Integer.toHexString(var9));
            }

            var1 = var4.toString();
        } catch (NoSuchAlgorithmException var10) {
            var10.printStackTrace();
        }

        return var1;
    }

    public static String parseStrToMd5U32(String var0) {
        String var1 = parseStrToMd5L32(var0);
        if (var1 != null) {
            var1 = var1.toUpperCase();
        }

        return var1;
    }

    public static String parseStrToMd5U16(String var0) {
        String var1 = parseStrToMd5L32(var0);
        if (var1 != null) {
            var1 = var1.toUpperCase().substring(8, 24);
        }

        return var1;
    }

    public static String parseStrToMd5L16(String var0) {
        String var1 = parseStrToMd5L32(var0);
        if (var1 != null) {
            var1 = var1.substring(8, 24);
        }

        return var1;
    }

    public static void main(String[] var0) {
    }
}
