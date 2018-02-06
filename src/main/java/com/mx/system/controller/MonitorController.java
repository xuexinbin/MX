package com.mx.system.controller;

import com.mx.common.service.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * 系统监控controller
 *
 * @author mx
 */
@Controller
@RequestMapping("system/monitor")
public class MonitorController {

    @Autowired
    ICommonService commonService;

    /**
     * 操作日志-初始页面
     *
     * @return jsp
     */
    @RequestMapping(value = "operateLog", method = RequestMethod.GET)
    public String getOperateLogView() {
        return "system/operateLog";
    }

    /**
     * 错误日志-初始页面
     *
     * @return jsp
     */
    @RequestMapping(value = "errorLog", method = RequestMethod.GET)
    public String getErrorLogView() {
        return "system/operateLog";
    }

    private long pointer = 0; //上次文件大小

    @RequestMapping(value = "/getOperateLog", method = RequestMethod.POST)
    public ModelAndView getOperateLog(String date, Integer pointerTemp) throws IOException {
        // 0第一次进入画面：pointer重置为0
        if (pointerTemp == 0) {
            pointer = 0;
        }
        String res = "";
        String path = "C:/log/";
        String fileName = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(new Date());
        if (today.equals(date)) {
            fileName = "logs.log";
            res = readLogFile(path + fileName);
        } else {
            fileName = date.substring(0, 7)+"/logs-" + date + "-1.log.zip";
            res = readZipFile(path + fileName);

        }

        System.out.println(res);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("log", res);
        return new ModelAndView(new MappingJackson2JsonView(), resultMap);
    }

    private String readLogFile(String file) {
        StringBuilder sb = new StringBuilder();
        RandomAccessFile randomFile = null;
        try {
            File logFile = new File(file);
            // 只读文件
            randomFile = new RandomAccessFile(logFile, "r");
            // 移动文件指针位置
            randomFile.seek(pointer);
            String tmp = "";
            int lineCount = 0;
            while ((tmp = randomFile.readLine()) != null && lineCount < 50) {

                lineCount++;
                sb.append(new String(tmp.getBytes("ISO-8859-1"),"GB2312"));
                sb.append("\n");
                pointer = randomFile.getFilePointer();
            }

            randomFile.close();
        } catch (Exception e) {
            if (randomFile != null) {
                try {
                    randomFile.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            if (randomFile != null) {
                try {
                    randomFile.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public String readZipFile(String file){
        StringBuilder sb = new StringBuilder();

        try
        {
            ZipFile zf = new ZipFile(file);
            for (Enumeration entries = zf.entries(); entries.hasMoreElements();)
            {
                ZipEntry ze = (ZipEntry) entries.nextElement();
                try
                {
                    if (ze.isDirectory())
                    {
                    }
                    else
                    {
                        System.err.println("file - " + ze.getName() + " : " + ze.getSize() + " bytes");
                        long size = ze.getSize();
                        if (size > 0)
                        {
                            BufferedReader br = new BufferedReader(new InputStreamReader(zf.getInputStream(ze), "GB2312"));
                            String line;
                            while ((line = br.readLine()) != null)
                            {
                                sb.append(line);
                                sb.append("\n");
                            }
                            br.close();
                        }
                        System.out.println();
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return sb.toString();
    }


}
