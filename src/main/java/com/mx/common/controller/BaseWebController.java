package com.mx.common.controller;

import com.mx.common.exception.WebBusinessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Web controller基础
 *
 * @author mx
 */
public class BaseWebController {

    private static Logger logger = LogManager.getLogger(BaseWebController.class);

    /**
     * 异常处理
     *
     * @param exception 异常
     * @param request   req
     * @param response  res
     * @return 异常返回值
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception exception, HttpServletRequest request, HttpServletResponse response) {

        if (exception == null) {
            logger.error("exception == null");
        } else if (exception instanceof WebBusinessException) {
            WebBusinessException ex = (WebBusinessException) exception;
            logger.error("WebBusinessException Msg:" + ex.getMsg(), ex.getException());
        } else {
            logger.error("UNCATCH Exception", exception);
        }
        String message = "系统出现未知错误！";
        if (exception.getCause() != null) {
            message = exception.getCause().getMessage();
        }
        Map<String, Object> resultMap = new HashMap<>();
        // BJUI错误代码 statusCode: {ok:200, error:300, timeout:301},
        resultMap.put("statusCode", 300);
        resultMap.put("message", message);
        return new ModelAndView(new MappingJackson2JsonView(), resultMap);
    }
}
