package com.flash.EE.core.object.sprite;

import java.awt.Image;


/**
 * 
 * 
 * @author ersu
 *
 */
public interface Sprite2D {
	
	/**
	 * 	To reset image which will be showed in screem of current 2d sprite.
	 * @param image  The specified image for sprite.
	 */
	void setImage(Image image);
	
	/**
	 * 	To reset image which will be showed in screem of current 2d sprite.
	 * 	This function need a little time when set a new image path,
	 * 		but will not spend any time in the next time as a same image path.
	 * @param imagePath  The file path of specified image for this sprite.
	 */
	void setImage(String imagePath);
	
	/**
	 * 	To get the image of current 2d sprite.
	 * @return return the image of this sprite.
	 */
	Image getImage();
	
}
