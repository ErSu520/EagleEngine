package com.flash.EE.core.object.ui;

public class RootView extends AbstractUIView{
	

	public RootView(String name) {
		super(name);
		addBehaviour(new EventSystem());
	}

}
