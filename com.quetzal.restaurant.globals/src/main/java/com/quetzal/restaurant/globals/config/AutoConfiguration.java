/**
 * Classname: AutoConfiguration.java
 * Date: 12 feb 2026
 * Author: Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.globals.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.quetzal.restaurant.globals.cors.CorsConfig;
import com.quetzal.restaurant.globals.filter.GlobalFilterPermission;
import com.quetzal.restaurant.globals.provider.RolePermissionProvider;

@Configuration
public class AutoConfiguration {

	@Bean
    public GlobalFilterPermission globalFilterPermission(RolePermissionProvider rolePermissionRepository) {
        return new GlobalFilterPermission(rolePermissionRepository);
    }

    @Bean
    @ConditionalOnMissingBean
    public CorsConfig sharedCorsConfig() {
        return new CorsConfig();
    }
}
