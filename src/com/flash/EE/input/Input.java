package com.flash.EE.input;


public class Input {

	/**
	 * 	The object to receive input from keyboard.
	 */
	private static KeyboardInput keyboardInput = new EKeyboardInput();
	
	/**
	 *  To get the object which can receive input from keyboard.
	 * @return
	 */
	public static KeyboardInput getKeyboardInput() {
		return keyboardInput;
	}
	
	/**
	 * 	
	 */
	private static MouseInput mouseInput = new EMouseInput();
	
	/**
	 * 
	 */
	public static MouseInput getMouseInput() {
		return mouseInput;
	}
	
}
