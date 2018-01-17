package com.mx.common.util.response;

import com.mx.common.util.json.JsonConfigSingleton;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 接口返回数据类
 */
public class ResponseHandler {
    private static Logger logger = LogManager.getLogger(ResponseHandler.class);

    public static void write(HttpServletResponse response, Object result)
    {
        PrintWriter out = null;
        try
        {
            JSONObject json = JSONObject.fromObject(result, JsonConfigSingleton.getInstance());
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("success", json.get("success"));
            map.put("msg", json.get("msg"));
            map.put("errorCode", json.get("errorCode"));
            map.put("data", json.get("data"));
            // 将数据库存储的<br>还原为"\n"
            String str = JSONObject.fromObject(map).toString();
            str = str.replaceAll("<br>", "\n");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Methods", "*");
            out = response.getWriter();
            out.write(str);
        }
        catch(UnsupportedEncodingException e)
        {
            logger.error("UnsupportedEncodingException=>", e);
        }
        catch(IOException e)
        {
            logger.error("IOException=>", e);
        } finally
        {
            if (null != out)
            {
                out.flush();
                out.close();
            }
        }
    }

    public static void writeData(HttpServletResponse response, Object result)
    {
        PrintWriter out = null;
        try
        {
            JSONObject json = JSONObject.fromObject(result, JsonConfigSingleton.getInstance());
            // 将数据库存储的<br>还原为"\n"
            String str = json.toString();
            str = str.replaceAll("<br>", "\n");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Methods", "*");
            out = response.getWriter();
            out.write(str);
        }
        catch(UnsupportedEncodingException e)
        {
            logger.error("UnsupportedEncodingException=>", e);
        }
        catch(IOException e)
        {
            logger.error("IOException=>", e);
        } finally
        {
            if (null != out)
            {
                out.flush();
                out.close();
            }
        }
    }

    public static void writeList(HttpServletResponse response, List result)
    {
        PrintWriter out = null;
        try
        {
            JSONArray json = JSONArray.fromObject(result, JsonConfigSingleton.getInstance());
            // 将数据库存储的<br>还原为"\n"
            String str = json.toString();
            str = str.replaceAll("<br>", "\n");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Methods", "*");
            out = response.getWriter();
            out.write(str);
        }
        catch(UnsupportedEncodingException e)
        {
            logger.error("UnsupportedEncodingException=>", e);
        }
        catch(IOException e)
        {
            logger.error("IOException=>", e);
        } finally
        {
            if (null != out)
            {
                out.flush();
                out.close();
            }
        }
    }

}
