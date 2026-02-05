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
import com.quetzal.restaurant.model.Role;
import com.quetzal.restaurant.model.User;

public class Validations {
	
	private Validations() {}

	public static Optional<User> optionaUser(Optional<User> user) throws AppException {
		if(user.isEmpty()) 
			throw new AppException(HttpStatus.CONFLICT.value(), "The user does not exist or could not be found", ExceptionEnum.ERROR_ROLE_PERMISSION);
		return user;
	}

	public static Optional<Role> optionalRole(Optional<Role> role) throws AppException{
		if(role.isEmpty()) 
			throw new AppException(HttpStatus.CONFLICT.value(), "The role does not exist or could not be found", ExceptionEnum.ERROR_INVALID_REQUEST);
		return role;
	}

	public static void existsUserTag(boolean exists) throws AppException{
		if(exists)
			throw new AppException(HttpStatus.CONFLICT.value(), "The userTag already exists", ExceptionEnum.ERROR_USER_USER_TAG);
	}
}
