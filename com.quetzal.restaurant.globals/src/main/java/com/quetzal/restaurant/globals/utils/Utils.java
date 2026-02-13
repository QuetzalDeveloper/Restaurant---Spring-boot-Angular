/**
 * Classname : Utils.java
 * Date : 12 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.globals.utils;

import org.springframework.data.domain.Page;

/**
 * 
 */
public class Utils {

	public static boolean isNullOrEmpty(String string) {
		return string == null || string.isBlank();
	}

	public static boolean isNullOrEmpty(Boolean status) {
		return status == null;
	}

	public static <T> boolean isNullOrEmpty(Page<T> page) {
		return page == null || page.isEmpty();
	}
	
	public static boolean isUUID(String uuid) {
		if(uuid == null) 
			return false;
		return uuid.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
	}
	
	public static boolean isNullOrLessThan1(Number i) {
	    return i == null || i.longValue() <= 0;
	}

}
