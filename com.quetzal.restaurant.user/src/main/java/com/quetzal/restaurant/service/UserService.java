/**
 * Classname : UserService.java
 * Date : 13 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.service;

import org.springframework.data.domain.Pageable;

import com.quetzal.restaurant.dto.request.CreateUserRequestTO;
import com.quetzal.restaurant.dto.request.UpdateUserRequestTO;
import com.quetzal.restaurant.dto.response.CreateUserResponseTO;
import com.quetzal.restaurant.dto.response.GetUserResponseTO;
import com.quetzal.restaurant.dto.response.GetUserSimpleResponseTO;
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

	/**
	 * Returns a list with the basic user data. Includes search by name and active status. Includes pagination
	 * @param userId
	 * @param name
	 * @param pageable
	 * @return
	 */
	GetUserResponseTO getAllUsers(String userId, String name, Pageable pageable) throws AppException;

	/**
	 * Returns a simple list (uuid and name) of active and undeleted users assigned to a given role. Includes pagination
	 * @param userId 
	 * @param roleId
	 * @param pageable 
	 * @return
	 * @throws AppException 
	 */
	GetUserSimpleResponseTO getUsersSimplebyRole(String userId, Short roleId, Pageable pageable) throws AppException;

}
