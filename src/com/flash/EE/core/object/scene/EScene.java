package com.flash.EE.core.object.scene;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.flash.EE.core.control.builder.GameObjectBuilder;
import com.flash.EE.core.object.gameobject.EGameObject2D;
import com.flash.EE.core.object.gameobject.GameObject2D;
import com.flash.EE.core.object.ui.AbstractUIView;
import com.flash.EE.core.object.ui.RootView;

/**
 * 
 * @author chancloud
 *
 */
public class EScene implements Scene{
	
	/**
	 * 	The map to container all of things.
	 * 	Only wanna to recycle the time to search.
	 */
	private final Map<String,EGameObject2D> gameMap = new ConcurrentHashMap<>();
	public Map<String,EGameObject2D> getGameMap(){
		return gameMap;
	}
	private GameObject2D gameTree = new EGameObject2D("tree_of_the_game_world");
	public GameObject2D getGameTree() {
		return gameTree;
	}
	

	private ArrayList<EGameObject2D> startObjects = new ArrayList<>();
	public ArrayList<EGameObject2D> getStartObjects(){
		return startObjects;
	}
	
	private ArrayList<EGameObject2D> enableObjects = new ArrayList<>();
	public ArrayList<EGameObject2D> getEnableObjects(){
		return enableObjects;
	}
	public void enableGameObject(EGameObject2D gameObject2D) {
		if(!enableObjects.contains(gameObject2D)) {
			enableObjects.add(gameObject2D);	
		}
	}
	
	private ArrayList<EGameObject2D> disableObjects = new ArrayList<>();
	public ArrayList<EGameObject2D> getDisabletObjects(){
		return disableObjects;
	}
	public void disableGameObject(EGameObject2D gameObject2D) {
		if(!disableObjects.contains(gameObject2D)) {
			disableObjects.add(gameObject2D);
		}
	}
	
	private ArrayList<EGameObject2D> destroyObjects = new ArrayList<>();
	public ArrayList<EGameObject2D> getDestroyObjects(){
		return destroyObjects;
	}
	
	/**
	 * 	The map to container all of things.
	 * 	Only wanna to recycle the time to search.
	 */
	private final Map<String,AbstractUIView> viewMap = new ConcurrentHashMap<>();
	public Map<String,AbstractUIView> getViewMap(){
		return viewMap;
	}
	private AbstractUIView viewTree = new RootView("event_control_system");
	public AbstractUIView getViewTree() {
		return viewTree;
	}

	
	private ArrayList<AbstractUIView> startViews = new ArrayList<>();
	public ArrayList<AbstractUIView> getStartViews(){
		return startViews;
	}
	
	private ArrayList<AbstractUIView> enableViews = new ArrayList<>();
	public ArrayList<AbstractUIView> getEnableViews(){
		return enableViews;
	}
	public void enableView(AbstractUIView view) {
		if(!enableViews.contains(view)) {
			enableViews.add(view);
		}
	}
	
	private ArrayList<AbstractUIView> disableViews = new ArrayList<>();
	public ArrayList<AbstractUIView> getDisableViews(){
		return disableViews;
	}
	public void disableView(AbstractUIView view) {
		if(!disableViews.contains(view)) {
			disableViews.add(view);
		}
	}
	
	private ArrayList<AbstractUIView> destroyViews = new ArrayList<>();
	public ArrayList<AbstractUIView> getDestroyViews(){
		return destroyViews;
	}

	@Override
	public GameObject2D addGameObject2DNamed(String name) {
		if(name==null || name.isEmpty()) {
			return null;
		}
		if(gameMap.containsKey(name)) {
			return null;
		}
		//创建物体
		EGameObject2D gameObject2D = new EGameObject2D(name);
		//加入哈希表
		gameMap.put(name, gameObject2D);
		//设置根结点
		gameObject2D.setRoot(gameTree);
		//加入开始队列
		startObjects.add(gameObject2D);
		//添加场景
		gameObject2D.setScene(this);
		return gameObject2D;
	}
	
	@Override
	public GameObject2D addGameObject2DWithBuilder(String name, GameObjectBuilder builder) {
		GameObject2D gameObject2D = addGameObject2DNamed(name);
		if(gameObject2D!=null) {
			builder.builder(gameObject2D);
		}
		return gameObject2D;
	}

	@Override
	public GameObject2D addGameObject2DWithParent(String name, String parentName) {
		if(name==null || name.isEmpty() || parentName==null) {
			return null;
		}
		if(gameMap.containsKey(name)) {
			return null;
		}
		//创建物体
		EGameObject2D gameObject2D = new EGameObject2D(name);
		//加入哈希表
		gameMap.put(name, gameObject2D);
		//获取传入的父节点 若为空则设置为世界根节点
		GameObject2D parent = gameMap.get(parentName);
		if(parent!=null) {
			gameObject2D.setRoot(parent);
		}else {
			gameObject2D.setRoot(gameTree);
		}
		//加入开始队列
		startObjects.add(gameObject2D);
		//添加场景
		gameObject2D.setScene(this);
		return gameObject2D;
	}
	
	@Override
	public GameObject2D addGameObject2DWithBuilderAndParent(String name, GameObjectBuilder builder, String parentName) {
		GameObject2D gameObject2D = addGameObject2DWithParent(name,parentName);
		if(gameObject2D!=null) {
			builder.builder(gameObject2D);
		}
		return gameObject2D;
	}
	
	@Override
	public GameObject2D findGameObject2DNamed(String name) {
		return gameMap.get(name);
	}

	@Override
	public void destroyGameObject2DNamed(String name) {
		EGameObject2D gameObject = gameMap.get(name);
		if(gameObject==null) {
			return;
		}
		//加入破坏队列
		destroyObjects.add(gameObject);
	}
	
	
	
	@Override
	public boolean addUIView(AbstractUIView view) {
		if(view==null || viewMap.containsKey(view.getName())) {
			return false;
		}
		//添加入哈希表
		viewMap.put(view.getName(), view);
		//设置根结点
		view.setRoot(viewTree);
		//加入开始队列
		startViews.add(view);
		//添加场景
		view.setScene(this);
		return false;
	}
	
	@Override
	public boolean addUIViewWithParent(AbstractUIView view, String parentName) {
		if(view==null || parentName==null || viewMap.containsKey(view.getName())) {
			return false;
		}
		//添加入哈希表
		viewMap.put(view.getName(), view);
		//设置根结点
		AbstractUIView parent = viewMap.get(parentName);
		if(parent!=null) {
			view.setRoot(parent);
		}else {
			view.setRoot(viewTree);
		}
		//加入开始队列
		startViews.add(view);
		//添加场景
		view.setScene(this);
		return true;
	}

	@Override
	public AbstractUIView findUIViewNamed(String name) {
		return viewMap.get(name);
	}

	@Override
	public void destroyUIViewNamed(String name) {
		AbstractUIView view = viewMap.get(name);
		if(view==null) {
			return;
		}
		//加入破坏队列
		destroyViews.add(view);
	}
	
}
