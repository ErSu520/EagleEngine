package com.flash.EE.core.object.sprite;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ESprite2D implements Sprite2D{
	
	/**
	 * 	To remind the default image.
	 * 	If this sprite did not assign a image, the image of this sprite will be this.
	 */
	private static Image IMAGE_DEFAULT = null;
	
	static {
		if(IMAGE_DEFAULT==null) {
			try {
				IMAGE_DEFAULT = ImageIO.read(ESprite2D.class.getResource("/private/picture/IMAGE_DEFAULT.png"));
			} catch (IOException e) {
				
			}
		}
	}
	
	public ESprite2D() {
		this.image = IMAGE_DEFAULT;
	}
	
	public ESprite2D(Image image) {
		this.setImage(image);
	}
	
	public ESprite2D(String imagePath) {
		this.setImage(imagePath);
	}

	/**
	 * 	The picture will be displayed on the scream.
	 */
	private Image image;
	
	@Override
	public void setImage(Image image) {
		if(image==null) {
			image = IMAGE_DEFAULT;
			return;
		}
		this.image = image;
	}
	
	@Override
	public void setImage(String imagePath) {
		if(imagePath==null) {
			image = IMAGE_DEFAULT;
			return;
		}
		Image image = SpritePool.get(imagePath);
		if(image==null) {
			try {
				image = ImageIO.read(new File(imagePath));
				SpritePool.put(imagePath, image);
				this.image = image;
			} catch (IOException e) {
				this.image = IMAGE_DEFAULT;	
			}
		}else {
			this.image = image;
		}
	}

	@Override
	public Image getImage() {
		return this.image;
	}


}
