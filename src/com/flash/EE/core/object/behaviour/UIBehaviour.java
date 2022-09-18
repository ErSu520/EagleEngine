package com.flash.EE.core.object.behaviour;

import com.flash.EE.core.object.ui.AbstractUIView;

public abstract class UIBehaviour implements Behaviour{
	
	private AbstractUIView view;
	public final AbstractUIView getUIView() {
		return view;
	}
	public final void setUIView(AbstractUIView view) {
		if(this.view!=null) {
			return;
		}
		this.view = view;
	}


	/**
	 * 	This fuction will be called when this compnent join to th project.
	 * 	And will be called only once.
	 */
	public void start() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 	This fuction will be called when this compnent showed in screem.
	 */
	public void enable() {
		// TODO Auto-generated method stub
		
	}
	
	public void beforeUpdate() {
		// TODO Auto-generated method stub
		
	}

	
	public void update() {
		// TODO Auto-generated method stub
		
	}

	
	public void laterUpdate() {
		// TODO Auto-generated method stub
		
	}

	
	public void disable() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 	This function will be called when this compnent be clicked.
	 */
	public void onClick() {
		
	}
	
}
