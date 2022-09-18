package com.flash.EE.compnent.physics;

import com.flash.EE.core.object.behaviour.GameObjectBehaviour;
import com.flash.EE.util.Vector2D;

public abstract class AbstractCollider extends GameObjectBehaviour{

	
	/**
	 * 	It(value is true) means this gameobject can not through out.
	 * 	But the fuction name trigger will be called.
	 */
	public boolean isTrigger = false;
	
	public AbstractCollider() {
		isTrigger = false;
	}
	
	/**
	 * 	
	 * @param isStatic
	 * @param isTrigger
	 */
	public AbstractCollider(boolean isTrigger) {
		this.isTrigger = isTrigger;
	}
	
	//获取数组
	protected Area area;
	
	protected Area preArea;
	
	protected Vector2D prePosition;
	
	@Override
	public void start() {
		area = AreaManager.getArea();
		preArea = Area.emptyArea;
		prePosition = Vector2D.ZERO();
	}
	
	@Override
	public void beforeUpdate() {
		if(getGameObject().getTransform().getPosition().equals(prePosition)) {
			return;
		}
		overrideArea();
	}
	
	public abstract void overrideArea();
	
	public abstract void clearArea();
	
	@Override
	public void enable() {
		if(preArea!=Area.emptyArea) {
			preArea.getGameObjects().add(getGameObject());
		}
		overrideArea();
	}
	
	@Override
	public void disable() {
		preArea.getGameObjects().remove(getGameObject());
		clearArea();
	}
	
	
	@Override
	public void destroy() {
		preArea.getGameObjects().remove(getGameObject());
		clearArea();
	}
	
}
