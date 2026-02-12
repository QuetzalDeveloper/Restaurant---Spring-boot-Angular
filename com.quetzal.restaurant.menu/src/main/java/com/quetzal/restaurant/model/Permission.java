/**
 * Classname : PermissionModel.java
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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "permission", schema = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permission_seq_gen")
    @SequenceGenerator(name = "permission_seq_gen", sequenceName = "account.seq_permission", allocationSize = 1)
    private Long id;

    @Column(name = "key", length = 50)
    private String key;

    @Column(name = "description", length = 60)
    private String description;

    @Column(name = "path", length = 255)
    private String path;
}
