/**
 * Classname : UserController.java
 * Date : 13 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.controlller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quetzal.restaurant.dto.request.CreateUserRequestTO;
import com.quetzal.restaurant.dto.request.UpdateUserRequestTO;
import com.quetzal.restaurant.dto.response.CreateUserResponseTO;
import com.quetzal.restaurant.dto.response.GetUserResponseTO;
import com.quetzal.restaurant.dto.response.GetUserSimpleResponseTO;
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
	
	@GetMapping(value="getList", produces="application/json")
	public ResponseEntity<GetUserResponseTO> getAllUsers(@RequestHeader String userId, @RequestParam(required=false) String name, Pageable pageable) throws AppException {
		GetUserResponseTO response = userService.getAllUsers(userId, name, pageable);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value="getSimpleList", produces="application/json")
	public ResponseEntity<GetUserSimpleResponseTO> getUsersSimpleByRole(@RequestHeader String userId, @RequestParam Short roleId, Pageable pageable) throws AppException {
		GetUserSimpleResponseTO response = userService.getUsersSimplebyRole(userId, roleId, pageable);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping(value="activate", produces="application/json")
	public ResponseEntity<Boolean> activateUser(@RequestHeader String userId, @RequestParam String activateId, @RequestParam Boolean active) throws AppException {
		Boolean response = userService.activateUser(userId, activateId, active);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping(value="delete", produces="application/json")
	public ResponseEntity<Boolean> deleteUser(@RequestHeader String userId, @RequestParam String deleteUserId) throws AppException {
		Boolean response = userService.deleteUser(userId, deleteUserId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
