package com.baobaotao.exception;

public class BusinessException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -7597719237605518026L;

	public BusinessException() {
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String errorCode, Throwable cause) {
		super(errorCode, cause);
		// TODO Auto-generated constructor stub
	}

}
