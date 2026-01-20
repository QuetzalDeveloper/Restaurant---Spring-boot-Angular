/**
 * Classname : PermissionRepository.java
 * Date : 13 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quetzal.restaurant.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
