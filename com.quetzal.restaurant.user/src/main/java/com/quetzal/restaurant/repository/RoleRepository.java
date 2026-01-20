/**
 * Classname : RoleRepository.java
 * Date : 13 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quetzal.restaurant.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	/**
	 * Returns a role based on a given id
	 * @param roleId
	 * @return
	 */
	Optional<Role> findById(Short roleId);
}