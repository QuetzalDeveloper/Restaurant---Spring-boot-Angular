/**
 * Classname : RolePermission.java
 * Date : 12 feb 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.provider;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.quetzal.restaurant.globals.provider.RolePermissionProvider;
import com.quetzal.restaurant.repository.RolePermissionRepository;

@Service
public class RolePermission implements RolePermissionProvider {

	private final RolePermissionRepository repository;
	
	public RolePermission(RolePermissionRepository repository) {
		super();
		this.repository = repository;
	}


	@Override
	public List<String> getPathPermissionByUuid(UUID uuid) {
		return repository.getPathPermissionByUuid(uuid);
	}

}
