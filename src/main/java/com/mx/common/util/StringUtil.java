package com.mx.common.util;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

/**
 * 字符串类
 *
 * @author mx
 */
public class StringUtil {

    /**
     * 判断是否为空
     * null和"" 均为空
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

    /**
     * 判断是否都为空
     */
    public static boolean isEmpty(String str, String str2) {
        return StringUtils.isEmpty(str) && StringUtils.isEmpty(str2);
    }

    /**
     * 判断是否非空
     *
     * @param str string
     * @return boolean
     */
    public static boolean isNotEmpty(String str) {
        return !StringUtils.isEmpty(str);
    }

    /**
     * 将驼峰属性转为DB形式 -> userName-> user_name
     *
     * @param str 字符串
     * @return string
     */
    public static String convertStringToDB(String str) {
        if (isEmpty(str)) {
            return str;
        }

        char[] charArray = str.toCharArray();
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < charArray.length; i++) {
            // 大写、非第一个、非最后一个
            if (Character.isUpperCase(charArray[i]) && i != 0 && i != (charArray.length - 1)) {
                newStr.append("_").append(Character.toLowerCase(charArray[i]));
            } else {
                newStr.append(charArray[i]);
            }
        }
        return newStr.toString();
    }
}
