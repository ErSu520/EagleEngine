package com.flash.EE.painter.window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.flash.EE.input.EMouseInput;
import com.flash.EE.input.Input;


public class EagleMouseListener implements MouseListener,MouseMotionListener{

	EMouseInput input = (EMouseInput)Input.getMouseInput();
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
//		System.out.println("1");
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		input.setMousePressed(true);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		input.setMousePressed(false);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
//		System.out.println("4");
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
//		System.out.println("5");
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
//		System.out.println("6");
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
//		System.out.println("7");
		
	}
	
}









