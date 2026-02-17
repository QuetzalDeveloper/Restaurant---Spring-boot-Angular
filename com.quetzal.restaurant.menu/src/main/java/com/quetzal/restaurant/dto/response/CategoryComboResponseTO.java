/**
 * Classname: CategoryComboResponseTO.java
 * Date: 13 feb 2026
 * Author: Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.dto.response;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryComboResponseTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;

	public CategoryComboResponseTO() {}
	
	public CategoryComboResponseTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}
