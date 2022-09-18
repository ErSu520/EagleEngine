package com.flash.EE.input;

import java.util.ArrayList;
import java.util.List;
import com.flash.EE.util.Vector2D;

public class EMouseInput implements MouseInput{

	private List<Vector2D> pressedPositions = new ArrayList<>();
	
	private Vector2D position = Vector2D.ZERO();
	
	private volatile boolean mousePressed = false;
	
	public void setMousePressed(boolean pressed) {
		this.mousePressed = pressed;
	}

	@Override
	public boolean isMousePressed() {
		return mousePressed;
	}

	@Override
	public List<Vector2D> getPressedPositions() {
		return pressedPositions;
	}
	
	public void setPosition(float x , float y) {
		position.setVec(x,y);
	}
	
	@Override
	public Vector2D getPosition() {
		return position;
	}
	
}
