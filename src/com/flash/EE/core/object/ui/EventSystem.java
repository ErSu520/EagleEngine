package com.flash.EE.core.object.ui;

import java.util.Map.Entry;

import com.flash.EE.core.object.behaviour.UIBehaviour;
import com.flash.EE.input.EKeyStatus;
import com.flash.EE.input.EKeyboardInput;
import com.flash.EE.input.EMouseInput;
import com.flash.EE.input.Input;
import com.flash.EE.input.KeyStatus;
import com.flash.EE.ui.UIButton;
import com.flash.EE.util.Ergodic;
import com.flash.EE.util.Vector2D;

public class EventSystem extends UIBehaviour{
	
	EMouseInput mouseInput = (EMouseInput)Input.getMouseInput();
	EKeyboardInput keyboardInput = (EKeyboardInput)Input.getKeyboardInput();
	
	
	@Override
	public void beforeUpdate() {
		
		if(mouseInput.isMousePressed()) {
			mouseInput.getPressedPositions().add(mouseInput.getPosition().clone());
			if(mouseInput.getPressedPositions().size()==1) {
				Vector2D point = mouseInput.getPressedPositions().get(0);
				//加载点击事件
				Ergodic.ergodicUIView(getUIView(), new Ergodic.ErgodicUIViewCallback(){
					@Override
					public void callBack(AbstractUIView view) {
						if(view.getClass().getName().equals(UIButton.class.getName())) {
							float width = view.getTransform().getSize().getX()/2;
							float height = view.getTransform().getSize().getY()/2;
							float x = view.getTransform().getPosition().getX();
							float y = view.getTransform().getPosition().getY();
							
							if(point.getX()>x-width && point.getX()<x+width
									&& point.getY()>y-height && point.getY()<y+height) {
								for(int i=0;i<view.getBehaviours().size();i++) {
									view.getBehaviours().get(i).onClick();
								}
							}
							
						}
					}
				});
				
			}
		}else {
			mouseInput.getPressedPositions().clear();
		}
		
		synchronized (EKeyboardInput.class) {
			//首先遍历按下的按键
			for (Integer key : keyboardInput.getDownKeys().keySet()) {
				if(!keyboardInput.getKeys().containsKey(key)) {
					EKeyStatus status = new EKeyStatus(key);
					status.setStatus(KeyStatus.RELEASE);
					keyboardInput.getKeys().put(key,status);
				}
			}
		}
			
		//遍历状态
		for (Entry<Integer, EKeyStatus> entry : keyboardInput.getKeys().entrySet()) {
			if(keyboardInput.getDownKeys().containsKey(entry.getKey())) {
				if(entry.getValue().getStatus() == KeyStatus.RELEASE) {
					entry.getValue().setStatus(KeyStatus.PRESS_DOWN);
				}else if(entry.getValue().getStatus() == KeyStatus.PRESS_DOWN){
					entry.getValue().setStatus(KeyStatus.HOLD_DOWN);
				}
			}else {
				entry.getValue().setStatus(KeyStatus.RELEASE);
			}
		}
		
	}
	

}
