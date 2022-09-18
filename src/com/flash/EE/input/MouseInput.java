package com.flash.EE.input;

import java.util.List;

import com.flash.EE.util.Vector2D;

public interface MouseInput {
	
	
	/**
	 * 	To check wheater the mouse has been pressed or not.
	 * 	It will return true if the mouse is pressed,otherwise,return false.
	 * @return
	 */
	boolean isMousePressed();
	
	
	/**
	 * 	To get the list of mouse that has been pressd.
	 * 	It will return a List if the mouse is pressed,otherwise,return null.
	 * @return
	 */
	List<Vector2D> getPressedPositions();
	
	
	/**
	 * 	To get the position of mouse.
	 * @return
	 */
	Vector2D getPosition();
	
	
}
