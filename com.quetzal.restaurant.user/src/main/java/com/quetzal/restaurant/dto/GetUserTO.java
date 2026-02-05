/**
 * Classname : GetUserTO.java
 * Date : 20 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.dto;

import java.io.Serializable;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetUserTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private UUID uuid;
	private String userTag;
	private String name;
	private String lastName;
	private Boolean active;
	private Short roleId;
	private String roleName;
	
	public GetUserTO(UUID uuid, String userTag, String name, String lastName, Boolean active,
			Short roleId, String roleName) {
		super();
		this.uuid = uuid;
		this.userTag = userTag;
		this.name = name;
		this.lastName = lastName;
		this.active = active;
		this.roleId = roleId;
		this.roleName = roleName;
	}
}
