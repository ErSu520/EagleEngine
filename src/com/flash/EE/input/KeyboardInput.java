package com.flash.EE.input;

public interface KeyboardInput {

	/**
	 * To get the horizontal alter figure.
	 * This value depends on if the left and right (or a and d) key was down.
	 * If left or a key was down , the value will be -1.
	 * If right or d key was down , the value will be 1.
	 * @return the horizontal alter figure.
	 */
	int getHorizontal();
	
	/**
	 * To get the vertical alter figure.
	 * This value depends on if the up and down (or w and s) key was down.
	 * If down or s key was down , the value will be -1.
	 * If up or w key was down , the value will be 1.
	 * @return the vertical alter figure.
	 */
	int getVertical();
	
	/**
	 * To check the specified Key is Dowm or press.
	 * If the Key is Dowm return true ,otherwise , return false;
	 * @param key A Keycode get from Class named KeyEvent. Such as KeyEvent.VK_UP.
	 * @return
	 */
	boolean isTheKeyDown(int key);
	
	/**
	 * 	To get the status of the specified key.
	 * @param key
	 * @return
	 */
	KeyStatus getKeyStatus(int key);
	
	
}
