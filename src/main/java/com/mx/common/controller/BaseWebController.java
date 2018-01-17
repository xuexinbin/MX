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
     * @param request
     * @param response
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
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("error", "unknownError");
        return new ModelAndView(new MappingJackson2JsonView(), resultMap);
    }
}
