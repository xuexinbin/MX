package com.mx.common.constant;

/**
 * 错误及提示语定义
 */
public enum ErrorCodeEnum {

    //登录相关
    USER_NOT_EXSIT(9000001,"用户名或密码不正确，请重试"),
    
	// 密码重置：密码错误
	PASSWORD_ERROR(9000002, "原密码不正确，请重新输入"),
    // 品牌logo上传
	FILE_TYPE_ERROR(9000003, "上传图像格式不正确！"),
	FILE_SIZE_ERROR(9000004, "上传图像太大！"),
	SAVE_ERROR(9000005, "保存失败！"),

    //订单相关
    ORDER_SELECT_ERROR(9000006, "订单不存在或已被他人认领，请确认！"),
    ORDER_SELECT_SUCCESS(9000007, "订单认领成功，请进入我的任务查看订单详情，请确保任务在规定时间内完成！");


    private Integer errorCode;
    private String message;

    /**
     * @param id
     * @param message
     */
    ErrorCodeEnum(Integer id, String message) {
        this.errorCode = id;
        this.message = message;
    }

    public static ErrorCodeEnum queryErrorCode(Integer errorCode)
    {
        ErrorCodeEnum result = null;
        if (errorCode != null)
        {
            for (ErrorCodeEnum ec : ErrorCodeEnum.values())
            {
                if (ec.getErrorCode().equals(errorCode))
                {
                    result = ec;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * @return the id
     */
    public Integer getErrorCode() {
        return errorCode;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}
