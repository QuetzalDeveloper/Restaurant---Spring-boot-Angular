/**
 * Classname: CategoryServiceImp.java
 * Date: 12 feb 2026
 * Author: Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.service.imp;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.quetzal.restaurant.dto.request.UpsertCategoryRequestTO;
import com.quetzal.restaurant.dto.response.CategoryComboResponseTO;
import com.quetzal.restaurant.globals.enu.ExceptionEnum;
import com.quetzal.restaurant.globals.exception.AppException;
import com.quetzal.restaurant.globals.model.Category;
import com.quetzal.restaurant.globals.utils.Utils;
import com.quetzal.restaurant.repository.CategoryRepository;
import com.quetzal.restaurant.service.CategoryService;
import com.quetzal.restaurant.utils.Validations;

import jakarta.validation.Valid;

@Service
public class CategoryServiceImp implements CategoryService {
	
	private static Logger log = LoggerFactory.getLogger(CategoryServiceImp.class);
			
	private CategoryRepository categoryRepository;
	
	public CategoryServiceImp(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<CategoryComboResponseTO> getCategoryCombo(String userId) throws AppException {
		log.info("/category-management/getCategoryCombo [{}]", userId);
		return categoryRepository.getCategoryCombo(); 
	}

	@Override
	public CategoryComboResponseTO upsertCategory(String userId, @Valid UpsertCategoryRequestTO request) throws AppException {
		CategoryComboResponseTO response = new CategoryComboResponseTO();
		
		if(Utils.isNullOrLessThan1(request.getId())) {	//New
			validateCategoryName(request.getName());
			
			Category save = new Category();
			save.setName(request.getName());
			save.setActive(Utils.isNullOrEmpty(request.getActive())? Boolean.TRUE : request.getActive());
			save.setDeleted(false);
			categoryRepository.save(save);
			
			response.setId(save.getId());
			response.setName(save.getName());
		} else {	//Update
			Optional<Category> category = categoryRepository.findById(request.getId());
			Validations.optionalCategory(category.isPresent());
			
			if(!category.get().getName().equals(request.getName())) {
				validateCategoryName(request.getName());
			}
			
			category.get().setName(request.getName());
			category.get().setActive(Utils.isNullOrEmpty(request.getActive())? Boolean.TRUE : request.getActive());
			categoryRepository.save(category.get());
			response.setId(category.get().getId());
			response.setName(category.get().getName());
		}
		
		return response;
	}
	
	private void validateCategoryName(String name) throws AppException{
		Optional<Category> validName = categoryRepository.findByNameAndDeletedFalse(name);
		if(validName.isPresent())
			throw new AppException(HttpStatus.CONFLICT.value(), "A category with the same name already exists.", ExceptionEnum.CATEGORY_NAME_ALREADY_EXISTS);
	}
}
