/**
 * Classname : RolePermissionRepository.java
 * Date : 13 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quetzal.restaurant.globals.model.Role;
import com.quetzal.restaurant.globals.model.RolePermission;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
	
	/**
	 * Returns the list of permissions for a specific role
	 * @param role
	 * @return
	 */
	List<RolePermission> findAllByRoleAndActiveTrueAndDeletedFalse(Role role);

	/**
	 * @param id
	 * @param string
	 * @return
	 */
	Optional<RolePermission> findByRoleIdAndPermissionKeyAndActiveTrueAndDeletedFalse(Short roleId, String key);
	
	/**
	 * Returns the list of paths that a user has permission to access based on their UUID
	 * @param uuid
	 * @return
	 */
	@Query(value="SELECT p.path FROM account.permission p inner join account.role_permission rp on rp.permission_id = p.id and rp.deleted = false and rp.active = true"
			+ " inner join account.role r on r.id = rp.role_id inner join account.user u on u.role_id = r.id and u.active= true and u.deleted = false"
			+ " where u.uuid = :uuid", nativeQuery=true )
	List<String> getPathPermissionByUuid(UUID uuid);
}
