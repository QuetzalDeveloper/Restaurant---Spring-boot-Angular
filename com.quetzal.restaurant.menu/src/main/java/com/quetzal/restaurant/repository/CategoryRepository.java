/**
 * Classname: CategoryRepository.java
 * Date: 13 feb 2026
 * Author: Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.quetzal.restaurant.dto.response.CategoryComboResponseTO;
import com.quetzal.restaurant.globals.model.Category;

/**
 * 
 */
public interface CategoryRepository extends JpaRepository<Category, Long>{

	/**
	 * Returns the ID and name of active and undeleted categories
	 * @return
	 */
	@Query("SELECT new com.quetzal.restaurant.dto.response.CategoryComboResponseTO(c.id, c.name)"
			+ " FROM Category c WHERE c.active=true AND c.deleted = false")
	List<CategoryComboResponseTO> getCategoryCombo();

	/**
	 * Returns a category by name
	 * @param name
	 * @return
	 */
	Optional<Category> findByNameAndDeletedFalse(String name);

	/**
	 * Returns how many records exist with the name
	 * @param name
	 * @return
	 */
	Integer countByNameAndDeletedFalse(String name);

}
