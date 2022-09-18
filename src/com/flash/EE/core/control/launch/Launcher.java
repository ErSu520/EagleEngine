package com.flash.EE.core.control.launch;


/**
 * 	
 * 	This class can start a game that has been configurationed.
 * @author chancloud
 *
 */
public interface Launcher {
	
	/**
	 * 	Start the game with the first scene load.
	 * 	If has no scene in this game, there is a exception will be throwed with info of "There is no scene exist in this game!!!".
	 * 	Otherwise the game will be started.
	 */
	void launch();
	
}
