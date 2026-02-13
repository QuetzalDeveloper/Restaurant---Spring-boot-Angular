/**
 * Classname : CorsConfig.java
 * Date : 6 feb 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.globals.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods(HttpMethod.GET.name(),
				HttpMethod.POST.name(), HttpMethod.DELETE.name(), HttpMethod.PUT.name())
				.allowedHeaders(HttpHeaders.CONTENT_TYPE, "userId").exposedHeaders("userId");
    }
}
