/**
 * Classname : RoleModel.java
 * Date : 13 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "role", schema = "account")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq_gen")
    @SequenceGenerator(name = "role_seq_gen", sequenceName = "account.seq_role", allocationSize = 1)
    private Short id;

    @Column(name = "key", length = 50)
    private String key;

    @Column(name = "description", length = 60)
    private String description;

}
