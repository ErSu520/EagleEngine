package com.flash.EE.core.object.transform;

import com.flash.EE.util.Vector2D;

public class ETransform implements Transform{
	
	private Vector2D size = Vector2D.ZERO();
	
	@Override
	public Vector2D getSize() {
		return size;
	}
	
	@Override
	public void setSize(float width, float height) {
		if(width<0 || height<0) {
			return;
		}
		this.size.setVec(width, height);
	}

	
	
	private Vector2D position = Vector2D.ZERO();
	
	@Override
	public Vector2D getPosition() {
		return this.position;
	}
	
	@Override
	public void setPosition(float x, float y) {
		this.position.setVec(x,y);
	}
	
	@Override
	public void setBounds(float x, float y, float width, float height) {
		setPosition(x, y);
		setSize(width, height);
	}
	
	
	
	private float rotation = 0;
	
	@Override
	public  float getRotation() {
		return rotation;
	}
	
	@Override
	public void setRotation(float retation) {
		retation = retation%360;
		this.rotation = retation;
	}
	
	@Override
	public void rotate(float rotate) {
		rotate += this.rotation;
		rotate = rotate%360;
		this.rotation = rotate;
	}

	
}
