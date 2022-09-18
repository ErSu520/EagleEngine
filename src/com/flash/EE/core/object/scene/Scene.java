package com.flash.EE.core.object.scene;

import com.flash.EE.core.control.builder.GameObjectBuilder;
import com.flash.EE.core.object.gameobject.GameObject2D;
import com.flash.EE.core.object.ui.AbstractUIView;

public interface Scene{
	
	/**
	 * 	To get the only name of this scene.
	 * 	'The only' means the value of name will not change from it init on.
	 * @return The only name of this scene.
	 */
//	String getName();
	
	/**
	 * 	To add a gameobject to this scene.
	 * 	Formally, the system will create a gameobject by specified name, and then return it.
	 * 	If the name is already exist in this scene, then the behaviour of create will be stop, and will return null.
	 * 	Meanwhile,the parent of this object will be the root object.
	 * @param name  A specified string that must not exist in this scene.
	 * @return	A object of class named GameObject2D.
	 */
	GameObject2D addGameObject2DNamed(String name);
	
	/**
	 * 	To add a gameobject to this scene.
	 * 	Formally, the system will use builder to create a gameobject by specified name, and then return it.
	 * 	If the name is already exist in this scene, then the behaviour of create will be stop, and will return null.
	 * 	Meanwhile,the parent of this object will be the root object.
	 * @param name  A specified string that must not exist in this scene.
	 * @return	A object of class named GameObject2D.
	 */
	GameObject2D addGameObject2DWithBuilder(String name , GameObjectBuilder builder);
	
	/**
	 * 	To add a gameobject to this scene.
	 * 	Formally, the system will create a gameobject by specified name, and then return it.
	 * 	If the name is already exist in this scene, then the behaviour of create will be stop, and will return null.
	 * 
	 *	Meanwhile,the parent of this object will be the object named as parentName in this scene.
	 *	However,if there is not a object named as parentName,the parent of this object will be the root object.
	 *
	 * @param name  A specified string that must not exist in this scene.
	 * @param parentName A specified string that must not exist in this scene.
	 * @return	A object of class named GameObject2D.
	 */
	GameObject2D addGameObject2DWithParent(String name , String parentName);
	
	/**
	 * 	To add a gameobject to this scene.
	 * 	Formally, the system will use builder to create a gameobject by specified name, and then return it.
	 * 	If the name is already exist in this scene, then the behaviour of create will be stop, and will return null.
	 * 
	 * 	Meanwhile,the parent of this object will be the object named as parentName in this scene.
	 *	However,if there is not a object named as parentName,the parent of this object will be the root object.
	 *
	 * @param name  A specified string that must not exist in this scene.
	 * @return	A object of class named GameObject2D.
	 */
	GameObject2D addGameObject2DWithBuilderAndParent(String name , GameObjectBuilder builder, String parentName);
	
	
	/**
	 * 	To find a gameobject named the specified name.
	 * 	If no gameobject named it,reutrn null.
	 * 	Otherwise return that gameobject.
	 * @param name
	 * @return
	 */
	GameObject2D findGameObject2DNamed(String name);
	
	/**
	 * 	To remove a gameobject from this scene.
	 * 	Formally, the system will remove the gameobject related the specified name, and then remove it from this scene.
	 * 	If the name is not exist in this scene, nothing will happen.
	 * @param name  A specified string that must exist in this scene.
	 */
	void destroyGameObject2DNamed(String name);

	/**
	 * 	To add a UI view to this scene.
	 * 	Formally, the system will check specified name, and then return wheater add the view or not.
	 * 	If the name is already exist in this scene, then the behaviour of adding will be stop, and will return false.
	 * @param name  A specified string that must not exist in this scene.
	 * @return	A object of class named component.
	 */
	boolean addUIView(AbstractUIView view);
	
	/**
	 * 	To add a UI view to this scene.
	 * 	Formally, the system will check specified name, and then return wheater add the view or not.
	 * 	If the name is already exist in this scene, then the behaviour of adding will be stop, and will return false.
	 * 
	 *	Meanwhile,the parent of this object will be the object named as parentName in this scene.
	 *	However,if there is not a object named as parentName,the parent of this object will be the root object.
	 * @param name  A specified string that must not exist in this scene.
	 * @return	A object of class named component.
	 */
	boolean addUIViewWithParent(AbstractUIView view , String parentName);
	
	/**
	 * 	To find a UI view named the specified name.
	 * 	If no UI view named it,reutrn null.
	 * 	Otherwise return that UI view.
	 * @param name
	 * @return
	 */
	AbstractUIView findUIViewNamed(String name);
	
	/**
	 * 	To remove a component (or said a view) from this scene.
	 * 	Formally, the system will remove the component related the specified name, and then remove it from this scene.
	 * 	If the name is not exist in this scene, nothing will happen.
	 * @param name  A specified string that must exist in this scene.
	 */
	void destroyUIViewNamed(String name);
	
}
