/**
 * Classname : LoginController.java
 * Date : 12 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.controlller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quetzal.restaurant.dto.request.LoginRequestTO;
import com.quetzal.restaurant.dto.response.LoginResposeTO;
import com.quetzal.restaurant.globals.exception.AppException;
import com.quetzal.restaurant.service.LoginService;

import jakarta.validation.Valid;

/**
 * 
 */
@RestController
@RequestMapping("login")
public class LoginController {

	private LoginService loginService;

	public LoginController(LoginService loginService) {
		super();
		this.loginService = loginService;
	}
	
	@PostMapping(consumes="application/json", produces="application/json")
	public ResponseEntity<LoginResposeTO> login(@Valid @RequestBody LoginRequestTO request) throws AppException {
		LoginResposeTO response = loginService.login(request);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
