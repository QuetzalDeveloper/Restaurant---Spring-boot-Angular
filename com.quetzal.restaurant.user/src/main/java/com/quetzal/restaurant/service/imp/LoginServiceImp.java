/**
 * Classname : LoginServiceImp.java
 * Date : 12 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.service.imp;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.quetzal.restaurant.dto.request.LoginRequestTO;
import com.quetzal.restaurant.dto.response.LoginResposeTO;
import com.quetzal.restaurant.enu.ExceptionEnum;
import com.quetzal.restaurant.exception.AppException;
import com.quetzal.restaurant.model.RolePermission;
import com.quetzal.restaurant.model.User;
import com.quetzal.restaurant.repository.RolePermissionRepository;
import com.quetzal.restaurant.repository.UserRepository;
import com.quetzal.restaurant.service.LoginService;
import com.quetzal.restaurant.utils.Utils;
import com.quetzal.restaurant.utils.Validations;

@Service
public class LoginServiceImp implements LoginService{
	
	private static Logger log = LoggerFactory.getLogger(LoginServiceImp.class);
	
	private UserRepository userRepository;
	private RolePermissionRepository rolePermissionRepository;
	
	public LoginServiceImp(UserRepository userRepository, RolePermissionRepository rolePermissionRepository) {
		super();
		this.userRepository = userRepository;
		this.rolePermissionRepository = rolePermissionRepository;
	}

	@Override
	public LoginResposeTO login(LoginRequestTO request) throws AppException {
		log.info("login. Request : {}", request);
		Optional<User> login = Validations.optionalUser(userRepository.findByUserTagAndPasswordAndActiveTrueAndDeletedFalse(request.getUser(), request.getPassword()));
		List<RolePermission> permissionList = rolePermissionRepository.findAllByRoleAndActiveTrueAndDeletedFalse(login.get().getRole());
		LoginResposeTO response = new LoginResposeTO();
		response.setUserId(login.get().getUuid().toString());
		response.setRoleName(login.get().getRole().getKey());
		response.setRole(login.get().getRole().getId());
		response.setContent(null);
		response.setContent(permissionList.stream().map(rp -> rp.getPermission().getKey()).toList());
		response.setUserName(login.get().getName() +" "+ login.get().getLastName());
		
		return response;
	}

}
