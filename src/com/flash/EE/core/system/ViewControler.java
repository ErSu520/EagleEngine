package com.flash.EE.core.system;

import com.flash.EE.util.Vector2D;

public class ViewControler {

	
	
	private final Vector2D gmaeViewSize = Vector2D.ZERO();
	
	public  void setGameViewSize(Vector2D viewSize) {
		gmaeViewSize.setVec(viewSize);;
	}
	
	public  void setGameViewSize(float x,float y) {
		gmaeViewSize.setVec(x,y);;
	}
	
	public  Vector2D getGameViewSize() {
		return gmaeViewSize;
	}
	
	private final  Vector2D realViewSize = Vector2D.ZERO();
	
	public  void setRealViewSize(Vector2D size) {
		realViewSize.setVec(size);;
	}
	
	public  void setRealViewSize(float x,float y) {
		realViewSize.setVec(x,y);;
	}
	
	public Vector2D getRealViewSize() {
		return realViewSize;
	}
	
	
	private final  Vector2D realViewPosition = Vector2D.ZERO();
	
	public  void setRealViewPosition(Vector2D size) {
		realViewPosition.setVec(size);;
	}
	
	public  void setRealViewPosition(float x,float y) {
		realViewPosition.setVec(x,y);;
	}
	
	public Vector2D getRealViewPosition() {
		return realViewPosition;
	}
	
}
