package com.flash.EE.core.object.renderer;

import java.awt.Color;

public class ERenderer implements Renderer{

	private Color color = Color.WHITE;;
	
	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color color) {
		if(color==null) {
			return;
		}
		this.color = color;
	}
	
	
	int transparency = 100;

	@Override
	public int getTransparency() {
		return transparency;
	}

	@Override
	public void setTransparency(int transparency) {
		if(transparency<0 || transparency>100) {
			return;
		}

		this.transparency = transparency;
	}

}
