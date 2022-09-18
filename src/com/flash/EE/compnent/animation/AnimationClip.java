package com.flash.EE.compnent.animation;

public interface AnimationClip {
	
	/**
	 * 	Create a new object of class AnimationClip.
	 * 	If the imagePaths or name is a empty object,nothing will be returned.
	 * @param imagePaths
	 * @return
	 */
	public static AnimationClip instantiation(String name , String[] imagePaths) {
		if(name==null || imagePaths==null) {
			return null;
		}
		return new EagleAnimationClip(name , imagePaths);
	}
	
	/**
	 * 	To get the only name of this animation clip.
	 * 	'The only' means the value of name will not change from it init on.
	 * @return The only name of this animation clip.
	 */
	String getName();
	
	/**
	 * 	To get NO.index frame of this animation clip.
	 * @param index
	 * @return
	 */
	String getFrameOfClipAt(int index);
	
	/**
	 * 	To get the count of frame in this animation clip.
	 * @return
	 */
	int size();
	
}
