/**
 * Classname : UserRepository.java
 * Date : 13 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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

}
