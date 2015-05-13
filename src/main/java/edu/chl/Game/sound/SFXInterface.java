package edu.chl.Game.sound;

/**
 * Contains the necessary methods for
 * Sound Effect
 * 
 * @author Mansoor
 * @version 1.0 
 */
public interface SFXInterface {

	
	/**
	 * Get an instance of Music.
	 * @return sfx - An instance
	 */
	public SFX getSFX();
	
	
	/**
	 * Prints all Sound Effects in the list with name and path
	 */
	void printSFXList();
	
	
	/**
	 * Add a sound effect to the list
	 * @param sfx - Type SFX of 
	 */
	void addSFX(String name, String filePath);
	
	
	/**
	 * Clear the sound effect list
	 */
	void clearSFXList();

	
	/**
	 * Remove a sound effect from the list
	 * @param sfx - Type SFX 
	 */
	void removeSFX(SFX sfx);
}
