package com.flash.EE.util;

public interface Available {
	
	/**
	 * 	Check whether the object alive or not.
	 * 	If the object is available,return true.
	 * 	Otherwise,return false;
	 * @param exit
	 */
	public void setAvailable(boolean available);
	
	/**
	 * 	Return if the object is available or not.
	 * @param exit
	 */
	public boolean isAvailable();
	

}
