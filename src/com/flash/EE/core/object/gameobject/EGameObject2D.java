package com.flash.EE.core.object.gameobject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.flash.EE.core.object.behaviour.GameObjectBehaviour;
import com.flash.EE.core.object.renderer.ERenderer;
import com.flash.EE.core.object.renderer.Renderer;
import com.flash.EE.core.object.scene.EScene;
import com.flash.EE.core.object.sprite.ESprite2D;
import com.flash.EE.core.object.sprite.Sprite2D;
import com.flash.EE.core.object.transform.ETransform;
import com.flash.EE.core.object.transform.Transform;
import com.flash.EE.util.Vector2D;

public class EGameObject2D implements GameObject2D{
	
	public EGameObject2D(String name) {
		this.name = name;
	}
	
	private String name = "";

	@Override
	public String getName() {
		return this.name;
	}
	
	private String tag = "";

	@Override
	public String getTag() {
		return tag;
	}

	@Override
	public void setTag(String tag) {
		if(tag==null) {
			this.tag = "";
			return;
		}
		this.tag = tag;
	}

	private final Sprite2D sprite = new ESprite2D();
	
	@Override
	public Sprite2D getSprite2D() {
		return sprite;
	}
	
	private final Renderer renderer = new ERenderer();
	
	@Override
	public Renderer getRenderer() {
		return renderer;
	}
	
	private Transform transform = new ETransform();
	
	public Transform getTransform() {
		return transform;
	}
	
	@Override
	public void move(float amountOfMovement) {
		move(amountOfMovement , transform.getRotation());
	}
	
	public void move(float amountOfMovement , float rotation) {
		if(amountOfMovement==0) {
			return;
		}
		
		if(Float.isNaN(transform.getPosition().getX())) {
			return;
		}
		double rotateAmount = Math.PI*(rotation/180);
		transform.getPosition().setX(((float)Math.sin(rotateAmount))*amountOfMovement
					+transform.getPosition().getX());
		transform.getPosition().setY((-(float)Math.cos(rotateAmount))*amountOfMovement
					+transform.getPosition().getY());
	}
	
	@Override
	public float forward(GameObject2D gameobject) {
		if(gameobject==null) {
			return 0;
		}
		return this.forward(gameobject.getTransform().getPosition());
	}

	@Override
	public float forward(Vector2D position) {
		if(transform.getPosition().equals(position)) {
			return 0;
		}
		float x = transform.getPosition().getX()-position.getX();
		float x2 = x*x;
		float y = transform.getPosition().getY()-position.getY();
		float y2 = y*y;
		float distance = (float) Math.sqrt(x2+y2);
		float rotate =(float) (Math.acos(y/distance)/Math.PI*180);
		if(x<=0) {
			transform.setRotation(rotate);
		}else{
			transform.setRotation(-rotate);
		}
		return distance;
	}
	
	
	
	private final List<GameObjectBehaviour> behaviours = new ArrayList<>();
	
	@Override
	public List<GameObjectBehaviour> getBehaviours() {
		return this.behaviours;
	}

	@Override
	public void addBehaviour(GameObjectBehaviour behaviour) {
		behaviour.setGameObject(this);
		this.behaviours.add(behaviour);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void destoryBehaviour(Class className) {
		for(int i=0;i<behaviours.size();i++) {
			GameObjectBehaviour behaviour = behaviours.get(i);
			if(behaviour.getClass().equals(className)) {
				behaviours.remove(i);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public GameObjectBehaviour findBehaviour(Class className) {
		for(int i=0;i<behaviours.size();i++) {
			GameObjectBehaviour behaviour = behaviours.get(i);
			if(behaviour.getClass().equals(className)) {
				return behaviour;
			}
		}
		return null;
	}
	
	
	
	private final List<GameObject2D> children = new Vector<>();

	@Override
	public List<GameObject2D> getChildren() {
		return children;
	}
	
	private GameObject2D root;
	
	@Override
	public GameObject2D getRoot() {
		return root;
	}

	/**
	 * 	To set the parent view.
	 * 	Do not designate a NULL value for it , otherwise , the view will dead.
	 * @param view
	 */
	public void setRoot(GameObject2D view) {
		if(view==null || root!=null) {
			return;
		}
		this.root = view;
	}
	
	
	/**
	 * 	保存该物体所在的场景
	 */
	private EScene scene ;
	public void setScene(EScene scene) {
		this.scene = scene;
	}
	
	@Override
	public void setEnable(boolean enable) {
		if(enable==true) {
			if(this.enable == false) {
				scene.enableGameObject(this);
			}
		}else {
			if(this.enable == true) {
				scene.disableGameObject(this);
			}
		}
	}
	
	private boolean enable = true;
	
	public void enable() {
		this.enable = true;
	}
	
	public void disable() {
		this.enable = false;
	}

	@Override
	public boolean isEnable() {
		return this.enable;
	}

	private final Map<String, String> extras = new HashMap<>();
	
	@Override
	public Map<String, String> getExtras() {
		return extras;
	}

	

}
