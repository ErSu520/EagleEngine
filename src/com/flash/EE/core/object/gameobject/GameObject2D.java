package com.flash.EE.core.object.gameobject;

import java.util.List;
import java.util.Map;

import com.flash.EE.core.object.behaviour.GameObjectBehaviour;
import com.flash.EE.core.object.renderer.Renderer;
import com.flash.EE.core.object.sprite.Sprite2D;
import com.flash.EE.core.object.transform.Transform;
import com.flash.EE.util.Vector2D;

public interface GameObject2D{
	
	/**
	 * 	To get the only name of this gameobject.
	 * 	'The only' means the value of name will not change from it init on.
	 * @return The only name of this gameobject.
	 */
	String getName();
	
	/**
	 * 	To get the tag of this gameobject.
	 * 	The tag is a sort,can be reflect to lots of gameobject.
	 * @return
	 */
	String getTag();
	
	/**
	 * 	To reset the tag of this gameobject.
	 * 	The tag is a sort,can be reflect to lots of gameobject.
	 * @param tag
	 */
	void setTag(String tag);
	
	/**
	 * 
	 * @param enable
	 */
	void setEnable(boolean enable);
	
	/**
	 * 	
	 * @return
	 */
	boolean isEnable();
	
	/**
	 * 	To get the sprite of this gameobject.
	 * 	The sprite is what will view in the frame on behalf of this gameobject.
	 * @return
	 */
	Sprite2D getSprite2D();
	
	/**
	 * 	To get the renderer of this gameobject.
	 * @return
	 */
	Renderer getRenderer();
	
	/**
	 * 	To get the transform of this gameobject.
	 * 	The transform include position,rotation and size of this gameobject.
	 * @return
	 */
	Transform getTransform();
	
	/**
	 * 	To reset local position for current 2d sprite.
	 * 	The position means that the center of sprite.
	 * 	Ps: 
	 * 		This function will move gameobject along its own direction.
	 * 		Formally, it will not put the sprite to position for absolute coordinate.
	 * 		If you need change its absolute coordinate, please use the function named {@method setPosition()} in this class.
	 * 	
	 * @param amountOfMovement  The specified amount of movement for object.
	 */	
	void move(float amountOfMovement);
	
	/**
	 * 	To reset local position for current 2d sprite.
	 * 	The position means that the center of sprite.
	 * 	Ps: 
	 * 		This function will move gameobject along the designated direction.
	 * 		Formally, it will not put the sprite to position for absolute coordinate.
	 * 		If you need change its absolute coordinate, please use the function named {@method setPosition()} in this class.
	 * 		
	 * @param amountOfMovement  The specified amount of movement for sprite.
	 * @param rotation  The designated direction.
	 */	
	void move(float amountOfMovement , float rotation);
	
	/**
	 * 	Make this gameobject look forward to the specified position.
	 * @param position	The specified position to forward.
	 * @return Return the distance from self to there.
	 */
	float forward(Vector2D position);
	
	/**
	 * 	Make this gameobject look forward to the specified gameobject.
	 * @param position	The specified gameobject to forward.
	 * @return Return the distance from self to it.
	 */
	float forward(GameObject2D gameobject);

	/**
	 * 	To add a behaviour to the container which can store all of behaviours in this gameobject.
	 * 	You can add only one behaviour belong to the same class,
	 * 		otherwise,the old one will be instead of the new one.
	 * 	All of those behaviours will be run in every frame.(:fps)
	 * @param behaviour
	 */
	void addBehaviour(GameObjectBehaviour behaviour);
	
	/**
	 * 	To remove the behaviours of specified class in this gameobject.
	 * 	You can remove any of them in this gameobject
	 * 		, and the behaviour will not run in next frame.
	 */
	@SuppressWarnings("rawtypes")
	void destoryBehaviour(Class className);
	
	/**
	 * 	To get the behaviour of specified class in this gameobject.
	 * 	You can remove any of them in this list, and the behaviour will not run in next frame.
	 * @return Behaviours of this gameobject.
	 */
	@SuppressWarnings("rawtypes")
	GameObjectBehaviour findBehaviour(Class className);
	
	/**
	 * 	To get all of behaviours in this object.
	 * @return
	 */
	List<GameObjectBehaviour> getBehaviours();
	
	
	/**
	 * 	To get all of child of this view.
	 * @return
	 */
	List<GameObject2D> getChildren();
	
	
	/**
	 * 	To get the parent view.
	 * @return
	 */
	GameObject2D getRoot();
	
	
	Map<String,String> getExtras(); 
	
}
