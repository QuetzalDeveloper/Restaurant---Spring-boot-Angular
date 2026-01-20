/**
 * Classname : UserServiceImp.java
 * Date : 13 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.service.imp;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.quetzal.restaurant.dto.request.CreateUserRequestTO;
import com.quetzal.restaurant.dto.request.UpdateUserRequestTO;
import com.quetzal.restaurant.dto.response.CreateUserResponseTO;
import com.quetzal.restaurant.enu.ExceptionEnum;
import com.quetzal.restaurant.exception.AppException;
import com.quetzal.restaurant.model.Role;
import com.quetzal.restaurant.model.User;
import com.quetzal.restaurant.repository.RolePermissionRepository;
import com.quetzal.restaurant.repository.RoleRepository;
import com.quetzal.restaurant.repository.UserRepository;
import com.quetzal.restaurant.service.UserService;
import com.quetzal.restaurant.utils.Validations;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class UserServiceImp  implements UserService {
	
	private static Logger log = LoggerFactory.getLogger(UserServiceImp.class);
	
	private UserRepository userRepository;
	private RolePermissionRepository rolePermissionRepository;
	private RoleRepository roleRepository;

	public UserServiceImp(UserRepository userRepository, RolePermissionRepository rolePermissionRepository, RoleRepository roleRepository) {
		super();
		this.userRepository = userRepository;
		this.rolePermissionRepository = rolePermissionRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	@Transactional
	public CreateUserResponseTO createUser(String userId, CreateUserRequestTO request) throws AppException {
		log.info("createUser [{}] - Request: {}", userId, request);		
		
		if(userRepository.existsByUserTag(request.getUserTag()))
			throw new AppException(HttpStatus.CONFLICT.value(), "The user with that tag already exists.", ExceptionEnum.ERROR_USER_USER_TAG);
		Optional<Role> role = roleRepository.findById(request.getRoleId());
		Validations.optionalRole(role.isPresent());
		LocalDateTime now = LocalDateTime.now();
		
		User save = new User();
		save.setUuid(UUID.randomUUID());
		save.setUserTag(request.getUserTag());
		save.setPassword(request.getPassword());
		save.setName(request.getName());
		save.setLastName(request.getLastName());
		save.setBirthdate(request.getBirthdate());
		save.setPhone(request.getPhone());
		save.setEmail(request.getEmail());
		save.setCreatedDate(now);
		save.setStartDate(request.getStartDate());
		save.setActive(true);
		save.setDeleted(false);
		save.setRole(role.get());
		
		userRepository.save(save);
		
		CreateUserResponseTO response = new CreateUserResponseTO();
		response.setUserId(save.getUuid().toString());
		response.setUser(save.getUserTag());
		response.setStartDate(save.getStartDate());
		
		return response;
	}

	@Override
	public CreateUserResponseTO updateUser(String userId, @Valid UpdateUserRequestTO request) throws AppException {
		log.info("updateUser [{}] - Request: {}", userId, request);
		Optional<User> user = Validations.optionaUser(userRepository.findByUuidAndActiveTrueAndDeletedFalse(UUID.fromString(request.getUuid())));
				
		return null;
	}

}
