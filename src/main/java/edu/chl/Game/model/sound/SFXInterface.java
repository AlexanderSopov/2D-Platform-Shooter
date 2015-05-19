package edu.chl.Game.model.sound;

/**
 * Contains the necessary methods for
 * Sound Effect
 * 
 * @author Mansoor
 * @version 1.0 
 */
public interface SFXInterface {

	//static SFXInterface sfx;
	
	/**
	 * Get an instance of Music.
	 * @return sfx - An instance
	 */
/*	public static SFX getSFX() {
		return sfx;
	}*/
	
	
	/**
	 * Prints all Sound Effects in the list with name and path
	 */
	static void printSFXList() {}
	
	
	/**
	 * Add a sound effect to the list
	 * @param sfx - Type SFX of 
	 */
	static void addSFX(String name, String filePath) {}
	
	
	/**
	 * Clear the sound effect list
	 */
	static void clearSFXList() {}

	
	/**
	 * Remove a sound effect from the list
	 * @param sfx - Type SFX 
	 */
	static void removeSFX(SFX sfx) {}
}
