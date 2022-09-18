package com.flash.EE.painter.window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import com.flash.EE.input.Input;
import com.flash.EE.input.EKeyboardInput;


public class EagleKeyBoardListener implements KeyListener{
	
	private EKeyboardInput input = (EKeyboardInput)Input.getKeyboardInput();
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		input.onKeyDown(e.getKeyCode());
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		input.onKeyReleased(e.getKeyCode());
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
