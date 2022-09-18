package com.flash.EE.compnent.audio;

public interface AudioClip {
	
	
	void start();
	
	
	void stop();
	
	
	boolean isStop();
	
	
	void setLoop(boolean loop);
	
	
	boolean isLoop();
	
	
	boolean isAlive();
	
	
	void setVolume(float volume);
	

	String getFilePath();
	
}
