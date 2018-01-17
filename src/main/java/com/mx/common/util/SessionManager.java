package com.mx.common.util;

import com.mx.common.constant.CommonConstant;
import com.mx.generator.pojo.SysUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * session管理
 */
public class SessionManager {

    HttpServletRequest request;

    private SessionManager() {
    }

    private static final SessionManager single = new SessionManager();

    public static SessionManager getInstance() {
        single.request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return single;
    }

    /**
     * 设置键值对
     *
     * @param key
     * @param value
     */
    public void setValue(String key, Object value) {
        request.getSession().setAttribute(key, value);
    }

    /**
     * 获取键值对
     *
     * @param key
     * @return
     */
    public Object getValue(String key) {
        return request.getSession().getAttribute(key);
    }

    public HttpSession getSession() {
        return request.getSession();
    }

    public void clearSession() {
        request.getSession().removeAttribute(CommonConstant.SESSION_USER_ID);
        request.getSession().removeAttribute(CommonConstant.SESSION_USER);
    }

    /**
     * 获得登录的用户
     * @return
     */
    public static SysUser getLoginUser() {
        return (SysUser)getInstance().getValue(CommonConstant.SESSION_USER);
    }

    /**
     * 获得登录的用户Id
     * @return
     */
    public static Integer getLoginUserId() {
        return (Integer) getInstance().getValue(CommonConstant.SESSION_USER_ID);
    }

}
