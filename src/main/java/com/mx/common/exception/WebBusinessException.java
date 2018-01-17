package com.mx.common.exception;

public class WebBusinessException extends RuntimeException {

	private static final long serialVersionUID = 4103901699797239803L;

	/** 错误内容 */
	private String msg;

	/** 异常 */
	private Exception exception;

	public WebBusinessException(String message, Exception e) {
		this.msg = message;
		this.setException(e);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

}
