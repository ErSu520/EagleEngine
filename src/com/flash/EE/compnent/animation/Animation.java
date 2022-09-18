package com.flash.EE.compnent.animation;

import java.util.HashMap;
import java.util.Map;
import com.flash.EE.core.object.behaviour.GameObjectBehaviour;
import com.flash.EE.core.system.Eagle;


public class Animation extends GameObjectBehaviour{
	
	public Animation(float intervalTime , AnimationListenner animationListenner) {
		this.intervalTime = this.theIntervalTime = intervalTime;
		if(animationListenner!=null) {
			this.animationListenner = animationListenner;
		}else {
			this.animationListenner = new AnimationAdapter();
		}
	}
	
	private AnimationListenner animationListenner = null;

	private final Map<String,AnimationClip> clips = new HashMap<>();
	
	private AnimationClip currentClip = null;
	
	private int index = -1 ;
	
	private float intervalTime , theIntervalTime;
	
	public void addClip(AnimationClip clip) {
		this.clips.put(clip.getName(),clip);
	}
	
	public boolean loadClipNamed(String name) {
		if(name==null) {
			currentClip = null;
			return true;
		}
		AnimationClip clip = this.clips.get(name);
		if(clip==null) {
			return false;
		}
		this.index=-1;
		this.currentClip = clip;
		return true;
	}
	
	@Override
	public void update() {
		if(this.currentClip==null) {
			return;
		}
		//检查动画播放的时间间隙
		if(this.intervalTime<=0) {
			//替换图片
			index++;
			if(index==currentClip.size()-1) {
				//The ending of current animation clip.
				this.animationListenner.onAnimationExit(getGameObject(),currentClip.getName());
			}else if(index==0) {
				//The start of current animation clip.
				this.animationListenner.onAnimationEnter(getGameObject(),currentClip.getName());
			}else if(index>=currentClip.size()) {
				index=0;
				//The start of current animation clip.
				this.animationListenner.onAnimationEnter(getGameObject(),currentClip.getName());
			}
			//设置新图片
			getGameObject().getSprite2D().setImage(currentClip.getFrameOfClipAt(index));
			//重新设置冷却时间
			this.intervalTime = this.theIntervalTime;
		}else {
			//减少冷却时间
			this.intervalTime -= Eagle.getTime().getDeltaTime();
		}
		
	}
	
	
	
	
}
