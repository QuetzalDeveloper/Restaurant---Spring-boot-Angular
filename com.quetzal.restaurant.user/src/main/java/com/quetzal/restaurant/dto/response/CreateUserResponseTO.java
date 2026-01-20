/**
 * Classname : CreateUserResponseTO.java
 * Date : 13 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.dto.response;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateUserResponseTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String user;
	private LocalDate startDate;

}
