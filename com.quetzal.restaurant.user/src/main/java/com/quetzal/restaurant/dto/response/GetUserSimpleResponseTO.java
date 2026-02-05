/**
 * Classname : UserResponseTO.java
 * Date : 20 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.dto.response;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetUserSimpleResponseTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long total;
	private Map<UUID, String> content;	
}
