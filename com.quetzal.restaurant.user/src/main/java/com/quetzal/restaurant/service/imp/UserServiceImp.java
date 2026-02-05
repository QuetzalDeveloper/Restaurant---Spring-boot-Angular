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
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.quetzal.restaurant.dto.GetUserTO;
import com.quetzal.restaurant.dto.request.CreateUserRequestTO;
import com.quetzal.restaurant.dto.request.UpdateUserRequestTO;
import com.quetzal.restaurant.dto.response.CreateUserResponseTO;
import com.quetzal.restaurant.dto.response.GetUserResponseTO;
import com.quetzal.restaurant.dto.response.GetUserSimpleResponseTO;
import com.quetzal.restaurant.enu.ExceptionEnum;
import com.quetzal.restaurant.exception.AppException;
import com.quetzal.restaurant.model.Role;
import com.quetzal.restaurant.model.User;
import com.quetzal.restaurant.repository.RoleRepository;
import com.quetzal.restaurant.repository.UserRepository;
import com.quetzal.restaurant.service.UserService;
import com.quetzal.restaurant.utils.Utils;
import com.quetzal.restaurant.utils.Validations;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class UserServiceImp  implements UserService {
	
	private static Logger log = LoggerFactory.getLogger(UserServiceImp.class);
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;

	public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	@Transactional
	public CreateUserResponseTO createUser(String userId, CreateUserRequestTO request) throws AppException {
		log.info("createUser [{}] - Request: {}", userId, request);		
		Validations.existsUserTag(userRepository.existsByUserTag(request.getUserTag()));
		Optional<Role> role = Validations.optionalRole(roleRepository.findById(request.getRoleId()));
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
		
		if(!user.get().getUserTag().equals(request.getUserTag())) {
			Validations.existsUserTag(userRepository.existsByUserTag(request.getUserTag()));
			user.get().setUserTag(request.getUserTag());
		}
		Optional<Role> role = Validations.optionalRole(roleRepository.findById(request.getRoleId()));
		LocalDateTime now = LocalDateTime.now();
		user.get().setPassword(request.getPassword());
		user.get().setName(request.getName());
		user.get().setLastName(request.getLastName());
		user.get().setBirthdate(request.getBirthdate());
		user.get().setPhone(request.getPhone());
		user.get().setEmail(request.getEmail());
		user.get().setUpdateDate(now);
		user.get().setStartDate(request.getStartDate());
		user.get().setEndDate(request.getEndDate());
		user.get().setRole(role.get());
		userRepository.save(user.get());
		
		CreateUserResponseTO response = new CreateUserResponseTO();
		response.setUserId(user.get().getUuid().toString());
		response.setUser(user.get().getUserTag());
		response.setStartDate(user.get().getStartDate());
		
		return response;
	}

	@Override
	public GetUserResponseTO getAllUsers(String userId, String name, Pageable pageable) throws AppException {
		log.info("getAllUsers [{}] - Name: {}", userId, name);
		Page<GetUserTO> list = null;
		
		if(Utils.isNullOrEmpty(name)) {
			list = userRepository.getAllUsersDeletedFalse(pageable);
		} else {
			list = userRepository.getAllUsersDeletedFalseByName(name.toUpperCase(), pageable);
		}
		
		GetUserResponseTO response = new GetUserResponseTO();
		response.setTotal(Utils.isNullOrEmpty(list) ? 0 : list.getTotalElements());
		response.setContent(list.getContent());
		
		return response;
	}

	@Override
	public GetUserSimpleResponseTO getUsersSimplebyRole(String userId, Short roleId, Pageable pageable) throws AppException {
		log.info("getAllUsers [{}] - RoleId: {}", userId, roleId);
		
		if(Utils.isNullOrLessThan1(roleId))
			throw new AppException(HttpStatus.CONFLICT.value(), "The roleId is invalid or empty", ExceptionEnum.ERROR_PARAM_SHORT);
		Page<User> query = userRepository.findAllByRoleIdAndActiveTrueAndDeletedFalse(roleId, pageable);
		GetUserSimpleResponseTO response = new GetUserSimpleResponseTO();
		response.setTotal(0L);
		
		if(!Utils.isNullOrEmpty(query)) {
			response.setTotal(query.getTotalElements());
			response.setContent(query.getContent().stream().collect(Collectors
					.toMap(User::getUuid, u -> String.format("%s %s", u.getName(), u.getLastName())
							)));
		}
		return response;		
	}

}
