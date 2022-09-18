package com.flash.EE.input;


public class EKeyStatus implements KeyStatus{
	
	public EKeyStatus(int keyCode) {
		this.keyCode = keyCode;
	}
	
	int status = KeyStatus.RELEASE;

	@Override
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}

	
	int keyCode;
	
	@Override
	public int getKeyCode() {
		return keyCode;
	}

	
	
	
	
	
}
