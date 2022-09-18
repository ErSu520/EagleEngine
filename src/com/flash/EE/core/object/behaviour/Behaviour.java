package com.flash.EE.core.object.behaviour;


public interface Behaviour{

	/**
	 * 	This fuction will be called when this compnent join to th project.
	 * 	And will be called only once.
	 */
	void start();
	
	/**
	 * 	This fuction will be called when this compnent showed in screem.
	 */
	void enable();
	
	/**
	 * 	This function will be called first of every frame.
	 */
	void beforeUpdate();

	/**
	 * 	This function will be called every frame follow {@method beforeUpdate()}
	 */
	void update();

	/**
	 * 	This function will be called every frame follow {@method update()}
	 */
	void laterUpdate();

	/**
	 * 	This fuction will be called when this compnent can not show in screem.
	 */
	void disable();

	/**
	 * 	This fuction will be called when this compnent remove of this game.
	 */
	void destroy();
	
}
