package com.flash.EE.compnent.physics;

import java.util.List;

import com.flash.EE.core.object.behaviour.GameObjectBehaviour;
import com.flash.EE.core.object.gameobject.GameObject2D;

public class RigidBody extends GameObjectBehaviour {
	
	private AbstractCollider collider;
	
	private Area arr ;
	
	@Override
	public void start() {
		collider = (AbstractCollider) getGameObject().findBehaviour(RectangleCollider.class);
		if(collider==null) {
			collider = (AbstractCollider) getGameObject().findBehaviour(CircularCollider.class);
		}
		if(collider==null) {
			try {
				throw new Exception("挂载刚体组件的物体必须挂载碰撞体组件");
			} catch (Exception e) {}
		}
		arr = AreaManager.getArea();
	}
	
	
	public void move(float amountOfMovement) {
		move(amountOfMovement , getGameObject().getTransform().getRotation());
	}
	
	public void move(float amountOfMovement , float angle) {
		if(amountOfMovement==0 || (amountOfMovement<0.001f && amountOfMovement>-0.001f)) {
			return;
		}
		getGameObject().move(amountOfMovement,angle);//物体先移动
		
		if(collider.getClass().equals(CircularCollider.class)) {
			r = getGameObject().getTransform().getSize().getX()/2;
			circularArea(arr);
		}
		
	}
	
	private float r;
	
	private void circularArea(Area area) {
		collisionCircularArea(area);//碰撞检测
		
		if(area.getChildren()==null) {
			return;
		}
		
		for(Area a : area.getChildren()) {
			if(a.getMaxX()<getGameObject().getTransform().getPosition().getX()-r
					|| a.getMinX()>getGameObject().getTransform().getPosition().getX()+r
					|| a.getMaxY()<getGameObject().getTransform().getPosition().getY()-r
					|| a.getMinY()>getGameObject().getTransform().getPosition().getY()+r) {
				continue;
			}
			circularArea(a);
		}
	}
	
	private void collisionCircularArea(Area area) {
		List<GameObject2D> objects = area.getGameObjects();
		
		for(GameObject2D gg : objects) {
			if(gg==getGameObject()) {
				return;
			}
			CircularCollider cc = (CircularCollider) gg.findBehaviour(CircularCollider.class);
			if(cc!=null) {
				float distance = getGameObject().getTransform().getPosition().distanceFrom(gg.getTransform().getPosition());
				float minDistance = cc.radius+((CircularCollider)collider).radius;
				if(distance<minDistance) {
					collision(cc, gg, minDistance-distance);
				}
				continue;
			}
			
			RectangleCollider rr = (RectangleCollider) gg.findBehaviour(RectangleCollider.class);
			if(rr!=null) {
				
			}
		}
	}
	
	private void collision(AbstractCollider cc , GameObject2D gg , float distance) {
		if(collider.isTrigger || cc.isTrigger) {//判断为触发器
			for(int j=0;j<getGameObject().getBehaviours().size();j++) {
				getGameObject().getBehaviours().get(j).trigger(gg);
			}
			for(int j=0;j<gg.getBehaviours().size();j++) {
				gg.getBehaviours().get(j).trigger(getGameObject());
			}
		}else {//判断为碰撞器
			if(gg.findBehaviour(RigidBody.class)!=null) {//对方为动态物体 一人退后一半
				float previousRotation = gg.getTransform().getRotation();
				gg.forward(getGameObject());
				gg.move(distance/2,gg.getTransform().getRotation()+180);
				gg.getTransform().setRotation(previousRotation);
				
				previousRotation = getGameObject().getTransform().getRotation();
				getGameObject().forward(gg);
				getGameObject().move(distance/2,getGameObject().getTransform().getRotation()+180);
				getGameObject().getTransform().setRotation(previousRotation);
			}else {//对方为静态物体 自己退后全部
				float previousRotation = getGameObject().getTransform().getRotation();
				getGameObject().forward(gg);
				getGameObject().move(distance,getGameObject().getTransform().getRotation()+180);
				getGameObject().getTransform().setRotation(previousRotation);
			}
			
			for(int j=0;j<getGameObject().getBehaviours().size();j++) {
				getGameObject().getBehaviours().get(j).collision(gg);
			}
			for(int j=0;j<gg.getBehaviours().size();j++) {
				gg.getBehaviours().get(j).collision(getGameObject());
			}
		}
	}
	
	
	
	
	
	
}
