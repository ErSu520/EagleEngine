package com.flash.EE.core.object.behaviour;

import com.flash.EE.core.object.gameobject.GameObject2D;

public abstract class GameObjectBehaviour implements Behaviour{
	
	private GameObject2D gameObject;
	public final GameObject2D getGameObject() {
		return gameObject;
	}
	public final void setGameObject(GameObject2D gameObject) {
		if(this.gameObject!=null) {
			return;
		}
		this.gameObject = gameObject;
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

	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 	It will be caled when the collider happened.
	 * @param self
	 * @param collider
	 */
	public void collision(GameObject2D collider) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 	It will be caled when the trigger happened.
	 * @param self
	 * @param collider
	 */
	public void trigger(GameObject2D collider) {
		// TODO Auto-generated method stub
		
	}

	
	
}
