package com.flash.EE.input;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class EKeyboardInput implements KeyboardInput{

	/**
	 *  The horizontal alter figure.
	 *  This value of horizontal depends on if the left and right (or a and d) key was down.
	 * 	If left or a key was down , the value will be -1.
	 * 	If right or d key was down , the value will be 1.
	 */
	private int horizontal = 0 ;
	
	@Override
	public int getHorizontal() {
		return horizontal;
	}
	
	/**
	 *  The vertical alter figure.  
	 *  This value of vertical depends on if the up and down (or w and s) key was down.
	 * 	If down or s key was down , the value will be -1.
	 * 	If up or w key was down , the value will be 1.
	 */
	private int  vertical = 0;
	@Override
	public int getVertical() {
		return vertical;
	}
	
	/**
	 * 	The container to store all of keys that had bean pressed.
	 * 	When key dowm , put the keyCode to this container .
	 * 	And remove the keyCode from this container on key Releasing.
	 * 	The KeyCode is get from class named KeyEvent. Use like KeyEvent.VK_UP.
	 */
	private final HashMap<Integer,Integer> downKeys = new HashMap<>();
	
	public Map<Integer,Integer> getDownKeys(){
		return downKeys;
	}
	
	@Override
	public boolean isTheKeyDown(int keyCode) {
		return downKeys.containsKey(keyCode);
	}
	
	public void onKeyDown(int keyCode) {
		switch(keyCode) {
			case KeyEvent.VK_UP :
			case KeyEvent.VK_W :
				vertical=1;
				break;
			case KeyEvent.VK_DOWN :
			case KeyEvent.VK_S :
				vertical=-1;
				break;
			case KeyEvent.VK_LEFT :
			case KeyEvent.VK_A :
				horizontal=-1;
				break;
			case KeyEvent.VK_RIGHT :
			case KeyEvent.VK_D :
				horizontal=1;
				break;	
		}
		synchronized (EKeyboardInput.class) {
			downKeys.put(keyCode, keyCode);
		}
	}
	
	public void onKeyReleased(int keyCode) {
		switch(keyCode) {
			case KeyEvent.VK_UP :
			case KeyEvent.VK_DOWN :
			case KeyEvent.VK_W :
			case KeyEvent.VK_S :
				vertical=0;
				break;
			case KeyEvent.VK_RIGHT :
			case KeyEvent.VK_LEFT :
			case KeyEvent.VK_A :
			case KeyEvent.VK_D :
				horizontal=0;
				break;
		}
		synchronized (EKeyboardInput.class) {
			downKeys.remove(keyCode);
		}
	}

	
	/**
	 * 	The container to store all of keys that had bean pressed.
	 * 	When key dowm , put the keyCode to this container .
	 * 	And remove the keyCode from this container on key Releasing.
	 * 	The KeyCode is get from class named KeyEvent. Use like KeyEvent.VK_UP.
	 */
	private final HashMap<Integer,EKeyStatus> keys = new HashMap<>();
	
	public Map<Integer,EKeyStatus> getKeys(){
		return keys;
	}
	
	@Override
	public KeyStatus getKeyStatus(int keyCode) {
		EKeyStatus status = keys.get(keyCode);
		if(status==null) {
			status = new EKeyStatus(keyCode);
			status.setStatus(KeyStatus.RELEASE);
			keys.put(keyCode,status);
		}
		return status;
	}
	
	
	
}
