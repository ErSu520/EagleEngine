package com.flash.EE.core.object.transform;

import com.flash.EE.util.Vector2D;

public interface Transform {

	/**
	 * 	To get the size of this transform.
	 * @return
	 */
	Vector2D getSize();
	
	/**
	 * 	To reset the size of this transform.
	 * 	the two parameter should be positive number.
	 * @param width
	 * @param hight
	 */
	void setSize(float width , float height);
	
	/**
	 * 	To get local position of the transform.
	 * 	The position means that the center of a sprite.
	 * 	PS:
	 * 		When you reset local position for a transform.
	 * 		Changing the position will put the transform to position for absolute coordinate.
	 * 		Formally, changing the position will not move transform along its own directions.
	 * 		If you need move it along its own directions, please use the function named {@method move()} in class GameObject2D.
	 * 	@return return the local position of transform.
	 */
	Vector2D getPosition();
	
	/**
	 * 	To reset the position of this transform.
	 * @param width
	 * @param hight
	 */
	void setPosition(float x , float y);
	
	/**
	 * 	To init the location and size of this transform.
	 * 	the width and height should be positive number.
	 * 
	 * @param X	The x of location.
	 * @param Y	The y of location.
	 * @param width	The width of the sprite of this transform.
	 * @param height The hight of the sprite of this transform.
	 */
	void setBounds(float x , float y , float width , float height);
	
	/**
	 * 	Get the rotation of this transform.
	 * 	The rotation is the rotate of x.
	 */
	float getRotation();
	
	/**
	 * 	To reset the rotation of this transform.
	 * 	
	 * @param retation
	 */
	void setRotation(float retation);
	
	/**
	 * 	To rotate the rotation of this transform.
	 * 	It will be added on the original basis rotation.
	 * @param rotate
	 */
	void rotate(float rotate);
	
}
