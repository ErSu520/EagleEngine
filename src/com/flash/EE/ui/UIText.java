package com.flash.EE.ui;

import java.awt.Color;

import com.flash.EE.core.object.ui.AbstractUIView;

public class UIText extends AbstractUIView{

	public UIText(String name , String text) {
		super(name);
		this.setText(text);
	}
	
	public UIText(String name , String text , int textSize , Color textColor) {
		this(name,text);
		this.setTextSize(textSize);
		this.setTextColor(textColor);
	}
	
	public void setText(String text) {
		if(text==null) {
			this.text="";
			return;
		}
		this.text = text;
	}
	
	public void setTextSize(int textSize) {
		if(textSize<1) {
			return;
		}
		this.textSize = textSize;
	}
	
	public void setTextColor(Color textColor) {
		if(textColor==null) {
			return;
		}
		this.textColor = textColor;
	}
	
}
