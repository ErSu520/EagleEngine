package com.flash.EE.compnent.animation;

public class EagleAnimationClip implements AnimationClip{
	
	public EagleAnimationClip(String name , String[] imagePaths) {
		this.name = name;
		this.imagePaths = imagePaths;
	}

	private String[] imagePaths;
	
	@Override
	public String getFrameOfClipAt(int index) {
		if(index<-1 && index>size()) {
			return null;
		}
		return imagePaths[index];
	}

	@Override
	public int size() {
		return imagePaths.length;
	}

	private String name;

	@Override
	public String getName() {
		return this.name;
	}
	
	
}
