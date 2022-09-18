package com.flash.EE.core.system;


import com.flash.EE.core.control.loader.ESceneLoader;
import com.flash.EE.core.control.loader.SceneLoader;
import com.flash.EE.core.object.scene.Scene;

public class Eagle {

	private static final ESceneLoader sceneLoader = new ESceneLoader();

	public static SceneLoader getSceneLoader() {
		return sceneLoader;
	}
	
	public static Scene getCurrentScene() {
		return sceneLoader.getCurrentScene();
	}
	
	
	private static final Camera camera = new Camera();
	public static Camera getCamera() {
		return camera;
	}
	
	private static final Time time = new Time();
	public static Time getTime() {
		return time;
	}
	
	private static final ViewControler controler = new ViewControler();
	public static ViewControler getViewControler() {
		return controler;
	}
	
}
