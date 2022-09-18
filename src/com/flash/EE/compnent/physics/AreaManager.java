package com.flash.EE.compnent.physics;

public class AreaManager {

	private static Area area;
	
	public static Area getArea() {
		return area;
	}
	
	public static void initial(float minX , float maxX , float minY , float maxY , float areaSize) {
		area = new Area(minX, maxX, minY, maxY, areaSize);
	}
	
	
	private static String[][] map;
	
	public static String[][] getMap() {
		return map;
	}
	
	/**
	 * 	
	 * @param width
	 * @param height
	 */
	public static void initial(int width , int height) {
		if(width<0 || height<0) {
			try {
				throw new Exception("世界的宽高不可以是负数");
			} catch (Exception e) {}
		}
		if(width*height>100000000) {
			try {
				throw new Exception("世界的面积太大");
			} catch (Exception e) {}
		}
		
		map = new String[width][height];
	}
	
	
	
	
}
