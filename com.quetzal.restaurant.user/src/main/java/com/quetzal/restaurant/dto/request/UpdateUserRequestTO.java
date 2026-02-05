/**
 * Classname : UpdateUserRequestTO.java
 * Date : 19 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.dto.request;

import java.io.Serializable;
import java.time.LocalDate;

import org.hibernate.validator.constraints.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateUserRequestTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message= "The uuid is empty")
	@UUID(message="The uuid is invalid")
	private String uuid;
	
	@NotBlank(message= "The userTag is empty")
	@Size(min=5, message = "The userTag must be at least 5 characters long")
	@Pattern(regexp = ".*[a-zA-ZáéíóúÁÉÍÓÚñÑ].*", message = "The userTag only contains letter")
	private String userTag;
	
	@NotBlank(message= "The password is empty")
	@Size(min=5, message = "The password must be at least 5 characters long")
	@Pattern(regexp = ".*[a-zA-Z].*", message = "The password must contain at least one letter")
	@Pattern(regexp = ".*\\d.*", message = "The password must contain at least one number")
	private String password;
	
	@NotBlank(message= "The name is empty")
	@Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message="The name is invalid")
	private String name;
	
	@NotBlank(message= "The last name is empty")
	@Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message="The last name is invalid")
	private String lastName;
	
	@NotNull(message= "The birthdate is empty")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Past(message = "The birthdate must be an earlier date")
	private LocalDate birthdate;
	
	@NotBlank(message="The phone number is empty")
	@Size(min = 10, max = 10, message = "The phone number must be 10 characters long.")
	@Pattern(regexp = ".*\\d.*", message = "The phone must contain at least one number")
	private String phone;
	
	@NotBlank(message="The email is empty")
	@Email(message="The email format is invalid")
	private String email;
	
	@NotNull(message= "The start date is empty")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@FutureOrPresent(message = "The start date must be current or later than the current date")
	private LocalDate startDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Future(message = "The start date must be current or later than the current date")
	private LocalDate endDate;
	
	@NotNull(message= "The role id is empty")
	@Positive(message= "The role id must be greater than 0")
	private Short roleId;
}
