/**
 * Classname : Utils.java
 * Date : 12 ene 2026
 * Author : Diego Hernandez Cote
 * Email : quetzal.developer@gmail.com
 */
package com.quetzal.restaurant.utils;

/**
 * 
 */
public class Utils {

	public static boolean isNullOrEmpty(String string) {
		return string == null || string.isBlank();
	}

	public static boolean isUUID(String uuid) {
		if(uuid == null) 
			return false;
		return uuid.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
	}

}
