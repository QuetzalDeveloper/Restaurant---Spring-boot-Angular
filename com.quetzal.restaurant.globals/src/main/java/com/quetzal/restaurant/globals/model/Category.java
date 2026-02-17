/**
 * Classname: Catefgory.java
 * Date: 13 feb 2026
 * Author: Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.globals.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="category", schema="menu")
@Getter
@Setter
@ToString
public class Category {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq_gen")
    @SequenceGenerator(name = "category_seq_gen", sequenceName = "menu.seq_category", allocationSize = 1)
    private Long id;
	
	@Column(name="name", length = 60)
	private String name;
	
	@Column
	private Boolean active;
	
	@Column
	private Boolean deleted;
}
