/**
 * Classname : UserController.java
 * Date : 13 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.controlller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quetzal.restaurant.dto.request.CreateUserRequestTO;
import com.quetzal.restaurant.dto.request.UpdateUserRequestTO;
import com.quetzal.restaurant.dto.response.CreateUserResponseTO;
import com.quetzal.restaurant.exception.AppException;
import com.quetzal.restaurant.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("user-management")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping(value="create", consumes="application/json", produces="application/json")
	public ResponseEntity<CreateUserResponseTO> createUser (@RequestHeader String userId, @Valid @RequestBody CreateUserRequestTO request) throws AppException {
		CreateUserResponseTO response = userService.createUser(userId, request);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping(value="update", consumes="application/json", produces="application/json")
	public ResponseEntity<CreateUserResponseTO> updateUser (@RequestHeader String userId, @Valid @RequestBody UpdateUserRequestTO request) throws AppException {
		CreateUserResponseTO response = userService.updateUser(userId, request);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
