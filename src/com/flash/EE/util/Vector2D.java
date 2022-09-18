package com.flash.EE.util;

public class Vector2D {
	
	
	public Vector2D() {
		x = 0;
		y = 0;
	}
	
	
	public Vector2D(float x,float y) {
		this.x = x;
		this.y = y;
	}

	
	private float x = 0;
	
	
	public void setX(float x) {
		this.x = x;
	}
	
	
	public float getX() {
		return x;
	}
	
	
	private float y = 0;
	
	
	public void setY(float y) {
		this.y = y;
	}
	
	
	public float getY() {
		return y;
	}
	
	public void setVec(float x , float y) {
		this.x = x;
		this.y = y;
	}
	
	public void setVec(Vector2D vec) {
		if(vec==null) {
			return;
		}
		this.x = vec.x;
		this.y = vec.y;
	}
	
	public float distanceFrom(Vector2D vec) {
		float x = this.x-vec.x;
		float x2 = x*x;
		float y = this.y-vec.y;
		float y2 = y*y;
		return (float) Math.sqrt(x2+y2);
	}
	
	
	public Vector2D clone() {
		return new Vector2D(x,y);
	}
	
	public boolean equals(Vector2D vec) {
		if(vec==null) {
			return false;
		}
		if(this == vec) {
			return true;
		}
		float X = x-vec.getX();
		float Y = y-vec.getY();
		return (X<0.1f && X>-0.1f && Y<0.1f && Y>-0.1f);
	}
	
	public Vector2DInt toVector2D() {
		return new Vector2DInt((int)x,(int)y);
	}
	
	public static Vector2D ZERO() {
		return new Vector2D(0,0);
	}
	
}
