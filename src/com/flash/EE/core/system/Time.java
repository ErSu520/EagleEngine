package com.flash.EE.core.system;

public class Time {

	private long frameTime = 20;
	
	public void setFrameTime(long time) {
		frameTime = time;
	}
	
	/**
	 * To get the current number of frames.
	 * @return
	 */
	public int getFPS() {
		return (int) (1000/frameTime);
	}
	
	/**
	 * 	To get the time spent getting a frame.
	 * @return
	 */
	public float getDeltaTime() {
		return frameTime/1000f;
	}
	
}
