package com.flash.EE.compnent.audio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.flash.EE.core.object.behaviour.GameObjectBehaviour;

public class AudioSource extends GameObjectBehaviour{

	private HashMap<String, AudioClip> hash = new HashMap<>();
	private List<AudioClip> clips = new ArrayList<>();
	
	
	@Override
	public void update() {
		for(int i=0;i<clips.size();i++) {
			if(clips.get(i).isStop()) {
				continue;
			}
			
			if(!clips.get(i).isAlive() && clips.get(i).isLoop()) {
				clips.get(i).start();
			}
		}
	}
	
	@Override
	public void disable() {
		//关闭所有音乐
		for(int i=0;i<clips.size();i++) {
			clips.get(i).stop();
		}
	}
	
	@Override
	public void destroy() {
		//关闭所有音乐
		for(int i=0;i<clips.size();i++) {
			clips.get(i).stop();
		}
	}
	
	
	public boolean isPlaying(String filePath) {
		if(hash.get(filePath)==null) {
			return false;
		}
		return hash.get(filePath).isAlive();
	}
	
	public void stop(String filePath) {
		if(hash.get(filePath)==null) {
			return;
		}
		hash.get(filePath).stop();
	}
	
	
	public void load(String filePath) {
		load(filePath,false,100f);
	}
	
	public void load(String filePath , boolean loop) {
		load(filePath,loop,100f);
	}
	
	public void load(String filePath,float volume) {
		load(filePath,false,volume);
	}
	
	public void load(String filePath , boolean loop , float volume) {
		AudioClip clip = hash.get(filePath);
		if(clip==null) {
			clip = new EAudioClip(filePath);
			hash.put(filePath, clip);
			clips.add(clip);
		}
		clip.setLoop(loop);
		clip.setVolume(volume);
		clip.start();
	}
	
	
	
}
