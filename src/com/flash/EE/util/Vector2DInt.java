package com.flash.EE.util;

public class Vector2DInt {
	
	
	public Vector2DInt() {
		x = 0;
		y = 0;
	}
	
	
	public Vector2DInt(int x,int y) {
		this.x = x;
		this.y = y;
	}

	
	private int x = 0;
	
	
	public void setX(int x) {
		this.x = x;
	}
	
	
	public int getX() {
		return x;
	}
	
	
	private int y = 0;
	
	
	public void setY(int y) {
		this.y = y;
	}
	
	
	public int getY() {
		return y;
	}
	
	public void setVec(int x , int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setVec(Vector2DInt vec) {
		if(vec==null) {
			return;
		}
		this.x = vec.x;
		this.y = vec.y;
	}
	
	public int distanceFrom(Vector2DInt vec) {
		if(vec==null) {
			return 0;
		}
		int x = this.x-vec.x;
		int x2 = x*x;
		int y = this.y-vec.y;
		int y2 = y*y;
		return (int) Math.sqrt(x2+y2);
	}
	
	
	public Vector2DInt clone() {
		return new Vector2DInt(x,y);
	}
	
	public boolean equals(Vector2DInt vec) {
		if(vec==null) {
			return false;
		}
		if(this == vec) {
			return true;
		}
		return (x==vec.x && y==vec.y);
	}
	
	public Vector2D toVector2D() {
		return new Vector2D(x,y);
	}
	
	public static Vector2DInt ZERO() {
		return new Vector2DInt(0,0);
	}
	
}
