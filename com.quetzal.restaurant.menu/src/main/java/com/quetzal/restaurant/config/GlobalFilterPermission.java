/**
 * Classname : GlobalFilterPermission.java
 * Date : 19 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.config;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.quetzal.restaurant.repository.RolePermissionRepository;
import com.quetzal.restaurant.utils.Utils;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(1)
public class GlobalFilterPermission implements Filter {
	
	private static Logger log = LoggerFactory.getLogger(GlobalFilterPermission.class);
	
	private RolePermissionRepository rolePermissionRepository;
	
	
	public GlobalFilterPermission(RolePermissionRepository rolePermissionRepository) {
		super();
		this.rolePermissionRepository = rolePermissionRepository;
	}

	private static final List<String> publicEndpoint = List.of(
	        "/login"
	    );
	
	private final Cache<String, Set<String>> permissionCache = Caffeine.newBuilder()
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .maximumSize(1000)
            .build();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String path = httpRequest.getRequestURI();

		if (!publicEndpoint.contains(path)) {
			String uuid = httpRequest.getHeader("userId");
			
			if (!Utils.isUUID(uuid)) {
				sendError(httpResponse, "The requesting user's UUID is empty or invalid.");
				return;
			}
			log.info("Filter  {} : {}", uuid, path);
			Set<String> permission = permissionCache.get(uuid, k -> getPermission(k));
			if (permission.isEmpty()) {
				sendError(httpResponse, "The permission is empty");
			} else {
				if (permission.contains(path))
					chain.doFilter(request, response);
				else
					sendError(httpResponse, "The user does not have permission for this service.");
			}
		} else {
			chain.doFilter(request, response);
		}

	}
	
	private Set<String> getPermission(String uuid) {
		log.info("Consulta");
		List<String> permissions = rolePermissionRepository.getPathPermissionByUuid(UUID.fromString(uuid));
		return new HashSet<>(permissions);
	}
	
	private void sendError(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"" + message + "\"}");
    }

}
