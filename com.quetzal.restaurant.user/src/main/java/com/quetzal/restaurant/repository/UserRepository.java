/**
 * Classname : UserRepository.java
 * Date : 13 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quetzal.restaurant.dto.GetUserTO;
import com.quetzal.restaurant.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Returns a user filtered by user tag and password, who is active and not deleted.
	 * @param user
	 * @param password
	 * @return
	 */
	Optional<User> findByUserTagAndPasswordAndActiveTrueAndDeletedFalse(String user, String password);

	/**
	 * Returns the user by the UUID and whether it is active and not deleted
	 * @param fromString
	 * @return
	 */
	Optional<User> findByUuidAndActiveTrueAndDeletedFalse(UUID fromString);

	/**
	 * Returns TRUE if a user with the specified userTag exists
	 * @param userTag
	 * @return
	 */
	boolean existsByUserTag(String userTag);

	/**
	 * Returns a list with the basic user data. Includes pagination.
	 * @param pageable
	 * @return
	 */
	@Query("SELECT new com.quetzal.restaurant.dto.GetUserTO(u.uuid, u.userTag, u.name, u.lastName, u.active, u.role.id, u.role.key)"
			+ " FROM User u WHERE u.deleted = false ORDER BY u.id") 
	Page<GetUserTO> getAllUsersDeletedFalse(Pageable pageable);

	/**
	 * Returns a list with the basic user data by name. Includes pagination.
	 * @param name
	 * @param pageable
	 * @return
	 */
	@Query("SELECT new com.quetzal.restaurant.dto.GetUserTO(u.uuid, u.userTag, u.name, u.lastName, u.active, u.role.id, u.role.key)"
			+ " FROM User u WHERE u.deleted = false AND (UPPER(u.name) like %:name% OR UPPER(u.lastName) like %:name%) ORDER BY u.id") 
	Page<GetUserTO> getAllUsersDeletedFalseByName(String name, Pageable pageable);

	/**
	 * Returns the active and undeleted users assigned to a role
	 * @param roleId
	 * @return
	 */
	Page<User> findAllByRoleIdAndActiveTrueAndDeletedFalse(Short roleId, Pageable pageable);

}
