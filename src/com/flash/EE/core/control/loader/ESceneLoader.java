package com.flash.EE.core.control.loader;

import com.flash.EE.core.control.builder.SceneBuilder;
import com.flash.EE.core.object.scene.EScene;
import com.flash.EE.core.object.scene.Scene;

public class ESceneLoader implements SceneLoader{
	
	private Scene currentScene = null;
	public Scene getCurrentScene() {
		return currentScene;
	}
	public void setCurrentScene(Scene scene) {
		this.currentScene = scene;
	}
	
	private Scene nextScene = null;
	public Scene getNextScene() {
		return nextScene;
	}
	public void setNextScene(Scene scene) {
		this.nextScene = scene;
	}
	
	@Override
	public Scene loadScene(SceneBuilder builder) {
		if(builder==null) {
			return null;
		}
		this.currentBuilder = builder;
		
		//create a scene
		Scene scene = new EScene();
		//init scene
		builder.builder(scene);
		//若是当前有场景 则加载为下一场景
		if(currentScene==null) {
			currentScene = scene;
		}else {
			nextScene = scene;
		}
		return scene;
	}
	
	private SceneBuilder currentBuilder = null;
	
	@Override
	public Scene reloadScene() {
		if(currentBuilder==null) {
			return null;
		}
		return loadScene(currentBuilder);
	}

}
