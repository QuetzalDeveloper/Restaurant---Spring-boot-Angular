/**
 * Classname : Validations.java
 * Date : 12 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.utils;

import java.util.Optional;

import org.springframework.http.HttpStatus;

import com.quetzal.restaurant.enu.ExceptionEnum;
import com.quetzal.restaurant.exception.AppException;
import com.quetzal.restaurant.model.User;

public class Validations {
	
	private Validations() {}

	public static Optional<User> optionaUser(Optional<User> user) throws AppException {
		if(user.isEmpty()) 
			throw new AppException(HttpStatus.CONFLICT.value(), "The user does not exist or could not be found", ExceptionEnum.ERROR_ROLE_PERMISSION);
		return user;
	}
	
	public static void optionalPermission(boolean present) throws AppException {
		if(!present)
			throw new AppException(HttpStatus.CONFLICT.value(), "The user does not have the necessary permissions", ExceptionEnum.ERROR_SEARCHING_USER);
	}

	public static void optionalRole(boolean present) throws AppException{
		if(!present)
			throw new AppException(HttpStatus.CONFLICT.value(), "The role does not exist or could not be found", ExceptionEnum.ERROR_INVALID_REQUEST);
	}

}
