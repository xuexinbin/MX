package com.mx.common.util;

import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

/**
 * 工具类
 *
 * @author mx
 */
public class ArrayUtil {

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

    /**
     * 数组是否包含某个值
     * @param array 数组
     * @param obj 包含值
     * @return true 包含
     */
    public static boolean contains(Object[] array, Object obj) {
        return ArrayUtils.contains(array, obj);
    }

}
