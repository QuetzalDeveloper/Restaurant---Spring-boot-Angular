/**
 * Classname : LoginService.java
 * Date : 12 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.service;

import com.quetzal.restaurant.dto.request.LoginRequestTO;
import com.quetzal.restaurant.dto.response.LoginResposeTO;
import com.quetzal.restaurant.exception.AppException;

public interface LoginService {

	/**
	 * User login. Returns the user's role and privileges.
	 * @param request
	 * @return
	 * @throws AppException 
	 */
	LoginResposeTO login(LoginRequestTO request) throws AppException;

}
