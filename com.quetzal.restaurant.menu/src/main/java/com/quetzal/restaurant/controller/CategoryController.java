/**
 * Classname : CategoryController.java
 * Date : 05 feb 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quetzal.restaurant.dto.request.UpsertCategoryRequestTO;
import com.quetzal.restaurant.dto.response.CategoryComboResponseTO;
import com.quetzal.restaurant.globals.exception.AppException;
import com.quetzal.restaurant.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("category-management")
public class CategoryController {

	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}
	
	@GetMapping(value="getCategoryCombo", produces="application/json")
	public ResponseEntity<List<CategoryComboResponseTO>> getCategoryCombo(@RequestHeader String userId) throws AppException {
		List<CategoryComboResponseTO> response = categoryService.getCategoryCombo(userId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping(value="upsert", consumes="application/json", produces="application/json")
	public ResponseEntity<CategoryComboResponseTO> createCategory(@RequestHeader String userId, @Valid @RequestBody UpsertCategoryRequestTO request) throws AppException {
		CategoryComboResponseTO response = categoryService.upsertCategory(userId, request);
		return new ResponseEntity<>(response, HttpStatus.OK); 
	}
	
	
}
