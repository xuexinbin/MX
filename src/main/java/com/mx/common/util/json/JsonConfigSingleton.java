package com.mx.common.util.json;

import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * JsonConfig单例，解决JSONObject.fromObject方法将封装类null值转换成默认值（如0）问题
 * 注：获取单例后不要对instance做任何修改
 *
 * @author
 */
public class JsonConfigSingleton {

    private static JsonConfig instance;

    public static synchronized JsonConfig getInstance() {

        if (instance == null) {
            instance = new JsonConfig();
            // Integer
            registerMyDefaultValueProcessor(Integer.class);
            // BigDecimal
            registerMyDefaultValueProcessor(BigDecimal.class);
            // Short
            registerMyDefaultValueProcessor(Short.class);
            // Byte
            registerMyDefaultValueProcessor(Byte.class);
            // 设置时间格式
            instance.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
            instance.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
            instance.registerJsonValueProcessor(java.sql.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        }
        return instance;
    }

    private static void registerMyDefaultValueProcessor(Class<?> target) {
        instance.registerDefaultValueProcessor(target, new DefaultValueProcessor() {
            @SuppressWarnings("rawtypes")
            public Object getDefaultValue(Class paramClass) {
                return "";
            }
        });
    }
}
