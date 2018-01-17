package com.mx.system.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ISystemMessageService {

	/**
	 * 上传系统消息图片
	 * @param uploadFile 图片
	 * @return json对象
	 */
	JSONObject uploadImg(MultipartFile uploadFile);

}
