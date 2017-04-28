package com.hotbitmapgg.bilibili.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Designed by guoyx on 4/28/17.
 * 字符串工具类
 */

public class StringUtil {

    /**
     * 验证手机号是否符合大陆的标准格式
     */
    public static boolean isMobileNumberValid(String mobiles) {
        Pattern p = Pattern.compile("^(17(0-9)|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }


    /**
     * 验证邮箱号是否标准格式
     */
    public static boolean emailValidation(String email) {
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return email.matches(regex);
    }


    /**
     * 推荐密码至少长度为 6 位
     */
    private static boolean isPasswordValid(String password) {
        return password.length() > 6;
    }


    /**
     * 手机号长度至少为 11 位
     * @param mobile
     * @return
     */
    public static boolean isLengthVaild(String mobile){
        return mobile.length() == 11;
    }
}
