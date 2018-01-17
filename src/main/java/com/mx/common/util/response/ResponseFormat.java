package com.mx.common.util.response;

import com.mx.common.constant.ErrorCodeEnum;

/**
 * 接口返回数据类
 */
public class ResponseFormat {

    /**
     * 是否成功
     */
    private boolean success = true;

    /**
     * 返回消息
     */
    private String msg;

    /**
     * 错误码
     */
    private int errorCode;

    /**
     * 返回客户端的数据
     */
    private Object data;


    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public int getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(int errorCode)
    {
        this.errorCode = errorCode;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    /**
     * 获得错误的返回消息
     *
     * @param errorCode
     * @return
     */
    public void setErrorInfo(ErrorCodeEnum errorCode)
    {
        this.errorCode=errorCode.getErrorCode();
        this.success=false;
        this.msg=errorCode.getMessage();
    }

    /**
     * 获得成功的返回消息
     *
     * @param errorCode
     * @return
     */
    public void setSuccessInfo(ErrorCodeEnum errorCode)
    {
        this.errorCode=errorCode.getErrorCode();
        this.success=true;
        this.msg=errorCode.getMessage();
    }

}
