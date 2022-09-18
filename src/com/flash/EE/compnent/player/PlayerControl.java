package com.flash.EE.compnent.player;

import com.flash.EE.compnent.physics.RigidBody;
import com.flash.EE.core.object.behaviour.GameObjectBehaviour;
import com.flash.EE.core.system.Eagle;
import com.flash.EE.input.Input;

/**
 * 	It could control a game object like moving,rotating and so on.
 *  
 */
public class PlayerControl extends GameObjectBehaviour{
	
	/**
	 * 	The speed to move of this object.
	 */
	public float movingSpeed;
	/**
	 * 	The speed to rotate of this object.
	 */
	public float rotatingSpeed;
	
	/**
	 * 	Is the camera follow the player.
	 */
	public boolean isCameraFollowing ;
	
	/**
	 * 	The default constructor,to set :
	 *		movingSpeed as 0,rotatingSpeed as 0.
	 */
	public PlayerControl() {
		this.movingSpeed = 0;
		this.rotatingSpeed = 0;
		this.isCameraFollowing = false;
	}
	
	/**
	 * 	
	 * @param movingSpeed
	 * @param rotatingSpeed
	 * @param isCameraFollowing
	 */
	public PlayerControl(float movingSpeed , float rotatingSpeed,boolean isCameraFollowing) {
		this.movingSpeed = movingSpeed;
		this.rotatingSpeed = rotatingSpeed;
		this.isCameraFollowing = isCameraFollowing;
	}
	
	
	private RigidBody rigid;
	
	@Override
	public void start() {
		rigid = (RigidBody)getGameObject().findBehaviour(RigidBody.class);
		if(rigid==null) {
			try {
				throw new Exception("挂载主角控制组件的物体必须挂载刚体组件");
			} catch (Exception e) {}
		}
	}
	
	@Override
	public void update() {
		rigid.move(Input.getKeyboardInput().getVertical()*movingSpeed*Eagle.getTime().getDeltaTime());
		
		getGameObject().getTransform().rotate(rotatingSpeed*Eagle.getTime().getDeltaTime()*Input.getKeyboardInput().getHorizontal());
		
		if(isCameraFollowing) {
			Eagle.getCamera().setPosition(getGameObject().getTransform().getPosition());
		}
		
	}
	
	
	
	
	
	
	
	
	
}
