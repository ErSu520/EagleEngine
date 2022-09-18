package com.flash.EE.input;

public interface KeyStatus {

	public static int PRESS_DOWN = 0x123 , HOLD_DOWN = 0x231 , RELEASE = 0x312 ;
	
	/**
	 * 	Return the status of the key with code as PRESS_DOWN,HOLD_DOWN and RELEASE.
	 * @return
	 */
	int getStatus(); 
	
	/**
	 * 	Return the name of the key.
	 * @return
	 */
	int getKeyCode();
	
	
}
