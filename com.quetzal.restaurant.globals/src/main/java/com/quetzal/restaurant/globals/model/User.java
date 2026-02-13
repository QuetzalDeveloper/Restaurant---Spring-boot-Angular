/**
 * Classname : UserModel.java
 * Date : 13 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.globals.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user", schema = "account", indexes = {
    @Index(name = "idx_user_uuid", columnList = "uuid", unique = true),
    @Index(name = "idx_user_user_tag", columnList = "user_tag", unique = true),
    @Index(name = "idx_user_active", columnList = "active"),
    @Index(name = "idx_user_deleted", columnList = "deleted"),
    @Index(name = "idx_user_role", columnList = "role_id"),
    @Index(name = "idx_user_birthdate", columnList = "birthdate")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "account.seq_user", allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private UUID uuid;

    @Column(name = "user_tag", length = 20, unique = true)
    private String userTag;

    @Column(length = 255)
    private String password;

    @Column(length = 60)
    private String name;

    @Column(name = "last_name", length = 80)
    private String lastName;

    private LocalDate birthdate;

    @Column(length = 10)
    private String phone;

    @Column(length = 70)
    private String email;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    private Boolean active;

    private Boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fk_user_role"))
    private Role role;
}
