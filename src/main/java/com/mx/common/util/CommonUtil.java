package com.mx.common.util;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

/**
 * 工具类
 *
 * @author mx
 */
public class CommonUtil {

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

    /**
     * 数组合并
     *
     * @param array 数据
     * @return string
     */
    public static String join(String[] array) {
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(array));
        return join(arrayList);
    }

    /**
     * set数据合并
     *
     * @param set set
     * @return string
     */
    public static String join(Set<String> set) {
        ArrayList<String> arrayList = new ArrayList<>(set);
        return join(arrayList);
    }

    /**
     * 字符串list合并
     *
     * @param list list
     * @return string
     */
    public static String join(ArrayList<String> list) {
        return join(list, ",");
    }

    public static String join(ArrayList<String> list, String linkStr) {
        if (list == null || list.size() == 0) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        for (String str : list) {
            res.append(str).append(linkStr);
        }

        return res.substring(0, res.length() - 1);
    }

    /**
     * 拼接DB insert值语句
     *
     * @param array 数据
     * @return string
     */
    public static String insertDataJoin(Object[] array) {
        if (array == null || array.length == 0) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        for (Object obj : array) {
            if (obj instanceof String) {

                res.append("'").append(obj.toString()).append("',");
            } else {
                res.append(obj.toString()).append(",");
            }

        }

        return "(" + res.substring(0, res.length() - 1) + ")";
    }
}
