package com.flash.EE.core.control.launch;

import com.flash.EE.util.Vector2D;

public class LauncherBuilder {
	
	/**
	 * 	The view must be a frame in java that can be view in the computer,
	 * 		and it must be implemented @class Furbisher.
	 * 	The part named viewSize is useless.
	 * @param view
	 * @param viewSize
	 * @return
	 */
	public static Launcher instantiation(Furbisher view , Vector2D viewSize) {
		return new EagleLauncher(view,viewSize);
	}
	
}
