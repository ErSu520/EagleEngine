package com.flash.EE.core.object.sprite;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

public class SpritePool {
	
	/**
	 * 	To contain all of images had been loading.
	 * 	When use the image in the second time,it need not to load again but get from here.
	 */
	private static Map<String, Image> images = new HashMap<>();
	
	public static void put(String key,Image value) {
		if(key==null || value==null) {
			return;
		}
		
		images.put(key, value);
	}
	
	public static Image get(String key) {
		if(key==null) {
			return null;
		}
		
		return images.get(key);
	}
	
	public static Image remove(String key) {
		if(key==null) {
			return null;
		}
		
		return images.remove(key);
	}
	

}
