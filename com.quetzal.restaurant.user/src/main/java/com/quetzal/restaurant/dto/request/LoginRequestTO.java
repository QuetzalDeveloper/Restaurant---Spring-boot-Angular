/**
 * Classname : LoginRequestTO.java
 * Date : 12 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.dto.request;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginRequestTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message= "The userTag is empty")
	@Size(min=5, message = "The userTag must be at least 5 characters long")
	@Pattern(regexp = ".*[a-zA-ZáéíóúÁÉÍÓÚñÑ].*", message = "The userTag only contains letter")
	private String user;
	
	@NotBlank(message= "The password is empty")
	@Size(min=5, message = "The password must be at least 5 characters long")
	@Pattern(regexp = ".*[a-zA-Z].*", message = "The password must contain at least one letter")
	@Pattern(regexp = ".*\\d.*", message = "The password must contain at least one number")
	private String password;

}
