package com.mx.common.interceptor;

import com.mx.common.constant.Constant;
import com.mx.common.util.SessionManager;
import com.mx.generator.pojo.SysUser;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 登录认证：session没有userInfo信息，跳转到登录画面
        SysUser sysUserInfo = SessionManager.getLoginUser();
		if (sysUserInfo == null) {
			// 判断是不是ajax请求
		    if ( "XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
		    	response.setStatus(Constant.STAUTS_CODE_901);
		    } else {
		    	response.sendRedirect(request.getContextPath() + "/login");
		    }
			return false;
		}
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
}
