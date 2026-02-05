/**
 * Classname : GlobalExceptionHandler.java
 * Date : 12 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.quetzal.restaurant.enu.ExceptionEnum;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		AppException e = new AppException(HttpStatus.BAD_REQUEST.value(), ex.getMostSpecificCause().getMessage(), ExceptionEnum.ERROR_INVALID_REQUEST);
		return new ResponseEntity<>(e.getExceptionResponse(), HttpStatus.valueOf(e.getStatus()));
	}
	
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<Object> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
		AppException e = new AppException(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ExceptionEnum.ERROR_INVALID_REQUEST);
		return new ResponseEntity<>(e.getExceptionResponse(), HttpStatus.valueOf(e.getStatus()));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		AppException e = new AppException(HttpStatus.BAD_REQUEST.value(), buildErrorMessage(ex.getBindingResult().getFieldErrors()), ExceptionEnum.ERROR_INVALID_REQUEST);
		log.error("AppException handled : {} . Message: {}", e.getExceptionResponse(), e.getMessage());
		return new ResponseEntity<>(e.getExceptionResponse(), HttpStatus.valueOf(e.getStatus()));
    }

	@ExceptionHandler(AppException.class)
	public ResponseEntity<Object> handleAppException(AppException e) {
		log.error("AppException handled : {} . Message: {}", e.getExceptionResponse(), e.getMessage());
		return new ResponseEntity<>(e.getExceptionResponse(), HttpStatus.valueOf(e.getStatus()));
	}
	
	private String buildErrorMessage(List<FieldError> fieldErrors) {
		return fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(" ---- "));
	}
}
