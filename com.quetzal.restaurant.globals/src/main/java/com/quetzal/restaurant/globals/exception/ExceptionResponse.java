/**
 * Classname : ExceptionResponse.java
 * Date : 12 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.globals.exception;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String code;
	private String messagekey;
}
