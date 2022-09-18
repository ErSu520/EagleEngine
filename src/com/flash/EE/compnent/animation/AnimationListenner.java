package com.flash.EE.compnent.animation;

import com.flash.EE.core.object.gameobject.GameObject2D;

public interface AnimationListenner {

	void onAnimationEnter(GameObject2D animationer , String animationClipName);
	
	void onAnimationExit(GameObject2D animationer ,String animationClipName);
	
}
