/**
 * Classname : LoginResposeTO.java
 * Date : 12 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.dto.response;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginResposeTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String userId;
	private Short role;
	private String roleName;
	private List<String> content;
	private String userName;

}
