package com.mx.common.util.json;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 实现了json-lib.jar包中的JsonValueProcessor接口，用于将日期格式化为指定的json字符串。<br>
 *
 * @author binarykey
 */
public class DateJsonValueProcessor implements JsonValueProcessor
{

    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private DateFormat dateFormat;

    public DateJsonValueProcessor(String datePattern)
    {
        try
        {
            dateFormat = new SimpleDateFormat(datePattern);
        }
        catch(Exception e)
        {
            dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        }
    }

    public Object processArrayValue(Object paramObject, JsonConfig paramJsonConfig)
    {
        return process(paramObject);
    }

    public Object processObjectValue(String paramString, Object paramObject, JsonConfig paramJsonConfig)
    {
        return process(paramObject);
    }

    private Object process(Object value)
    {
        return value == null ? "" : dateFormat.format((Date) value);
    }

}
