package com.mx.common.util;

import com.mx.common.pojo.BaseBean;
import net.sf.json.JSONArray;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 图片相关工具类
 *
 * @author mx
 */
public class ImageUtil {

    /**
     * 上传图片
     *
     * @param file     头像流
     * @param url      保存路径
     * @param fileName 文件名称->不带后缀
     * @return url 保存路径
     */
    public static String uploadImg(CommonsMultipartFile file, String url, String fileName) throws IOException {
        String returnUrl = null;
        if (file != null && file.getSize() > 0) {
            // 文件新名称：当前时间 + .png
            String newFileName = fileName + ".png";
            String miniFileName = fileName + "_mini.png";
            // 项目目录 /uploadImg/avatar
            String baseUrl = SessionManager.getInstance().getSession().getServletContext().getRealPath("/");
            String localPath = baseUrl + url;
            File localFile = new File(localPath, newFileName);
            if (!localFile.exists()) {
                if (!localFile.mkdirs()) {
                    return "";
                }
            }
            // 上传图片：出错往外抛，自动回滚
            file.transferTo(localFile);

            // 生成缩略图
            File imgFile = new File(localPath, newFileName);
            Image img = ImageIO.read(imgFile);
            BufferedImage bi = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
            Graphics graphics = bi.getGraphics();
            graphics.drawImage(img, 0, 0, 100, 100, null);
            graphics.dispose();
            ImageIO.write(bi, "png", new File(localPath, miniFileName));
            returnUrl = url + "/" + miniFileName;
        }
        return returnUrl;
    }
}
