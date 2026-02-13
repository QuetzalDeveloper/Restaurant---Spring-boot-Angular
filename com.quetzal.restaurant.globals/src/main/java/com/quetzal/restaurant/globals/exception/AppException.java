/**
 * Classname : AppException.java
 * Date : 12 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.globals.exception;

import com.quetzal.restaurant.globals.enu.ExceptionEnum;

import lombok.Getter;

@Getter
public class AppException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private final Integer status;
	private final String code;
	private final String message;
	private final String messageKey;
	private final String description;
	
	public AppException(Integer status, String code, String message, String messageKey, String description) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
		this.messageKey = messageKey;
		this.description = description;
	}
	
	public AppException (Integer status, String message, ExceptionEnum errorEnum) {
		this(status, errorEnum.getCode(), message, errorEnum.getMessageKey(), errorEnum.getDescription());
	}

	public ExceptionResponse getExceptionResponse() {
		ExceptionResponse res = new ExceptionResponse();
		res.setCode(code);
		res.setMessagekey(messageKey);
		return res;
		
	}
	
}
