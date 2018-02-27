package com.baobaotao.exception;

public class UserException extends RuntimeException  {

	/**
	 *
	 */
	private static final long serialVersionUID = -4626344268675479435L;
	/**
	 *
	 */

	private String errCode;
	private String errMsg;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}



	public UserException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserException(String message, Throwable cause) {
		super(message, cause);
		this.errMsg = message;
		// TODO Auto-generated constructor stub
	}

	public UserException(String message) {
		super(message);
		this.errMsg = message;
		// TODO Auto-generated constructor stub
	}

	public UserException(Throwable cause) {
		super(cause);
		this.errMsg = cause.getMessage();
		// TODO Auto-generated constructor stub
	}

	public UserException(String code, String message) {
		super(message);
		this.errCode = code;
		this.errMsg = message;
		// TODO Auto-generated constructor stub
	}

}