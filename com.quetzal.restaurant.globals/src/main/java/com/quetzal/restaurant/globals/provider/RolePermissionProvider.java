/**
 * Classname: RolePermissionProvider.java
 * Date: 12 feb 2026
 * Author: Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.globals.provider;

import java.util.List;
import java.util.UUID;

/**
 * 
 */
public interface RolePermissionProvider {

	List<String> getPathPermissionByUuid(UUID uuid);
}
