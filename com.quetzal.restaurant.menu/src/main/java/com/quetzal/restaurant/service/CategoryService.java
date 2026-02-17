/**
 * Classname : CategoryService.java
 * Date : 05 feb 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.service;

import java.util.List;

import com.quetzal.restaurant.dto.request.UpsertCategoryRequestTO;
import com.quetzal.restaurant.dto.response.CategoryComboResponseTO;
import com.quetzal.restaurant.globals.exception.AppException;

import jakarta.validation.Valid;

public interface CategoryService {

	/**
	 * Returns the ID and name of the active and undeleted categories for the category combo box
	 * @param userId
	 * @return
	 */
	List<CategoryComboResponseTO> getCategoryCombo(String userId) throws AppException;

	/**
	 * Insert or update a category
	 * @param userId
	 * @param request
	 * @return
	 */
	CategoryComboResponseTO upsertCategory(String userId, @Valid UpsertCategoryRequestTO request) throws AppException;

}
