/**
 * Classname : RolePermissionModel.java
 * Date : 13 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.globals.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "role_permission", schema = "account", indexes = {
    @Index(name = "idx_role_permission_active", columnList = "active"),
    @Index(name = "idx_role_permission_role_id", columnList = "role_id"),
    @Index(name = "idx_role_permission_permission_id", columnList = "permission_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RolePermission {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_perm_seq_gen")
    @SequenceGenerator(name = "role_perm_seq_gen", sequenceName = "account.seq_role_permission", allocationSize = 1
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fk_role_permission_role"))
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id", foreignKey = @ForeignKey(name = "fk_role_permission_permission"))
    private Permission permission;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    private Boolean active;

    private Boolean deleted;
}
