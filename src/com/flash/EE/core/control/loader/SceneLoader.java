package com.flash.EE.core.control.loader;

import com.flash.EE.core.control.builder.SceneBuilder;
import com.flash.EE.core.object.scene.Scene;

public interface SceneLoader {

	Scene loadScene(SceneBuilder builder);
	
	Scene reloadScene();
	
}
