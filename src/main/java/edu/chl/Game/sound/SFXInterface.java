package edu.chl.Game.sound;

import java.util.LinkedList;

public interface SFXInterface {

	
	/**
	 * Get an instance of Music.
	 * @return sfx - An instance
	 */
	public SFXInterface getSFX();
	
	/**
	 * Get the SFX (Sound Effect) List
	 * @return - The SFX list of type LinkedList<SFX>
	 */
	LinkedList<SFX> getSFXList();
	/**
	 * Add a sound effect to the list
	 * @param sfx - Type SFX of 
	 */
	void addSFX(SFX sfx);
	/**
	 * Clear the sound effect list
	 */
	void clearSFXList();
	/**
	 * Remove a sound effect from the list
	 * @param sfx - Type SFX 
	 */
	void removeSFX(SFX sfx);
	
	void setFileName(String fileName);
}
