package com.flash.EE.core.system;

import java.awt.Color;
import com.flash.EE.util.Vector2D;

public class Camera {

	
	/**
	 * 	The position of camera
	 */
	private Vector2D position = new Vector2D();
	
	public Vector2D getPosition() {
		return this.position;
	}
	
	public void setPosition(float x, float y) {
		this.position.setVec(x,y);
	}
	
	public void setPosition(Vector2D cameraPosition) {
		if(cameraPosition==null) {
			return;
		}
		this.position.setVec(cameraPosition.getX(), cameraPosition.getY());
	}
	
	/**
	 * 	The color of camera
	 */
	private Color color = Color.WHITE;
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	
	public Vector2D positionOfScreamToWolrd(Vector2D position) {
		Vector2D vector2d = new Vector2D();
		vector2d.setVec(position.getX()+getPosition().getX()-Eagle.getViewControler().getGameViewSize().getX()/2
				,position.getY()+getPosition().getY()-Eagle.getViewControler().getGameViewSize().getY()/2);
	
		return vector2d;
	}
	
	public Vector2D positionOfWolrdToScream(Vector2D position) {
		Vector2D vector2d = new Vector2D();
		vector2d.setVec(position.getX()-getPosition().getX()+Eagle.getViewControler().getGameViewSize().getX()/2
				,position.getY()-getPosition().getY()+Eagle.getViewControler().getGameViewSize().getY()/2);
	
		return vector2d;
	}
	
	
	
	
	
	
	
	
}
