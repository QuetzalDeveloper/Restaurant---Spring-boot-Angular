/**
 * Classname : ExceptionEnum.java
 * Date : 12 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.globals.enu;

import lombok.Getter;

@Getter
public enum ExceptionEnum {
	
	ERROR_UNEXPECTED_EXCEPTION("E001", "ERROR_UNEXPECTED_EXCEPTION", "An unexpected exception has ocurred"),
	ERROR_INVALID_REQUEST("E002", "ERROR_INVALID_REQUEST", "The request is empty or invalid"), 
	ERROR_SEARCHING_USER("E003", "ERROR_SEARCHING_USER", "Credentials error"),
	ERROR_PARAM_STRING("E004", "ERROR_PARAM_STRING", "Some parameter of type String is empty or invalid"),
	ERROR_PARAM_SHORT("E005", "ERROR_PARAM_SHORT", "Some parameter of type Short is empty or invalid"),
	ERROR_PARAM_DATE("E006", "ERROR_PARAM_DATE", "Some parameter of type LocalDate is empty or invalid"), 
	ERROR_ROLE_PERMISSION("E007", "ERROR_ROLE_PERMISSION", "The permission does not exist or could not be found"),
	ERROR_USER_USER_TAG("E008", "ERROR_USER_USER_TAG", "Error with the user tag of the user"), 
	ERROR_PARAM_UUID("E009", "ERROR_PARAM_UUID", "Error with the user uuid of the user"),
	;
	
	private String code;
	private String messageKey;
	private String description;
	
	private ExceptionEnum(String code, String messageKey, String description) {
		this.code = code;
		this.messageKey = messageKey;
		this.description = description;
	}

}
