package com.flash.EE.core.object.renderer;

import java.awt.Color;

public interface Renderer {
	
	/**
	 * 	To get the color of object should render with.
	 * @return
	 */
	Color getColor();
	
	/**
	 * To set the color of object should render with.
	 * @param color
	 */
	void setColor(Color color);
	

	/**
	 * 	To get the transparency of object should render with.
	 * @return
	 */
	int getTransparency();
	
	/**
	 * 	To set the transparency of object should render with.
	 * @param transparency
	 */
	void setTransparency(int transparency);
	

}
