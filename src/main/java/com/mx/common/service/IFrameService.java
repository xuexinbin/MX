package com.mx.common.service;


import com.mx.generator.pojo.SysUser;

public interface IFrameService {
	// 登录认证
	SysUser logining(String userName, String password);
}
