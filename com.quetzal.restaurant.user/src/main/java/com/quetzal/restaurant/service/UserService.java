/**
 * Classname : UserService.java
 * Date : 13 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.service;

import com.quetzal.restaurant.dto.request.CreateUserRequestTO;
import com.quetzal.restaurant.dto.request.UpdateUserRequestTO;
import com.quetzal.restaurant.dto.response.CreateUserResponseTO;
import com.quetzal.restaurant.exception.AppException;

import jakarta.validation.Valid;

public interface UserService {

	/**
	 * Create a user
	 * @param userId
	 * @param request
	 * @return
	 */
	CreateUserResponseTO createUser(String userId, CreateUserRequestTO request) throws AppException ;

	/**
	 * Update a user
	 * @param userId
	 * @param request
	 * @return
	 * @throws AppException 
	 */
	CreateUserResponseTO updateUser(String userId, @Valid UpdateUserRequestTO request) throws AppException;

}
