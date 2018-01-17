package com.mx.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mx.common.constant.CommonConstant;
import com.mx.system.service.ISystemMessageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 系统消息service
 *
 * @author mx
 */
@Service("systemMessageService")
// TODO 事务 @Transactional
public class SystemMessageServiceImpl implements ISystemMessageService {

    private static Logger logger = LogManager.getLogger(SystemMessageServiceImpl.class);

//    @Autowired
//    SystemMessageMapper systemMessageMapper;

    @Override
    public JSONObject uploadImg(MultipartFile uploadFile) {
        JSONObject res = new JSONObject();
        try {
            // 上传文件名，转为小写
            String fileName = uploadFile.getOriginalFilename().toLowerCase();
            // 图片格式：jpg、png、jpeg、bmp
            if (!fileName.endsWith(".jpg") && !fileName.endsWith(".png")
                    && !fileName.endsWith(".jpeg") && !fileName.endsWith(".bmp")) {
                res.put("error", 1);
                res.put("message", "文件格式错误！只能上传图片文件！");
                return res;
            }

            // 文件最大1M
            long maxSize = 1024 * 1000;
            if (uploadFile.getSize() > maxSize) {
                res.put("error", 1);
                res.put("message", "上传图片失败！文件太大（超过1M）！");
                return res;
            }

            // 文件新名称：当前时间 + .png
            String newFileName = System.currentTimeMillis() + ".png";
            // 当前盘根目录 /uploadImg/message
            String localPath = CommonConstant.UPLOAD_MESSAGE_FOLDER;
            File localFile = new File(localPath, newFileName);
            if (!localFile.exists()) {
                if (!localFile.mkdirs()) {
                    res.put("error", 1);
                    res.put("message", "上传失败");
                    return res;
                }
            }
            // 上传图片
            uploadFile.transferTo(localFile);

            // 上传成功
            res.put("error", 0);
            res.put("url", CommonConstant.GET_IMG_PREFIX + localPath + "/" + newFileName);
            return res;
        } catch (Exception e) {
            logger.info("消息中心图片上传失败：" + e.getMessage());
            // 上传错误
            res.put("error", 1);
            res.put("message", "上传失败" + e.getMessage());
            return res;
        }
    }
}
