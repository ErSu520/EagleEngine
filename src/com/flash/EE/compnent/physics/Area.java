package com.flash.EE.compnent.physics;

import java.util.ArrayList;
import java.util.List;

import com.flash.EE.core.object.gameobject.GameObject2D;

public class Area {
	
	private float minX ;
	public float getMinX() {
		return minX;
	}
	
	private float maxX ;
	public float getMaxX() {
		return maxX;
	}
	 
	private float minY ;
	public float getMinY() {
		return minY;
	}
	
	private float maxY ;
	public float getMaxY() {
		return maxY;
	}
	
	private float pivotX ;
	public float getPivotX() {
		return pivotX;
	}
	
	private float pivotY ;
	public float getPivotY() {
		return pivotY;
	}
	
	private float maxSize;
	public float getMaxSize() {
		return maxSize;
	}
	
	private Area[] children = null;
	public Area[] getChildren() {
		return children;
	}

	public Area(float minX , float maxX , float minY , float maxY , float maxSize) {
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
		this.pivotX = (minX+maxX)/2;
		this.pivotY = (minY+maxY)/2;
		this.maxSize = maxSize;
		if((maxX-minX)*(maxY-minY)>maxSize) {
			children = new Area[4];
			children[0] = new Area(pivotX, maxX, minY, pivotY, maxSize);
			children[1] = new Area(minX, pivotX, minY, pivotY, maxSize);
			children[2] = new Area(minX, pivotX, pivotY, maxY, maxSize);
			children[3] = new Area(pivotX, maxX, pivotY, maxY, maxSize);
		}
	}
	
	public Area() {}
	
	private List<GameObject2D> gameObjects = new ArrayList<>();
	
	public List<GameObject2D> getGameObjects(){
		return gameObjects;
	}
	
	public static Area emptyArea = new Area();
	
}






