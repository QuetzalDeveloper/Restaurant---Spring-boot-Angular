/**
 * Classname : UserResponseTO.java
 * Date : 20 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.dto.response;

import java.io.Serializable;
import java.util.List;

import com.quetzal.restaurant.dto.GetUserTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetUserResponseTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long total;
	private List<GetUserTO> content;	
}
