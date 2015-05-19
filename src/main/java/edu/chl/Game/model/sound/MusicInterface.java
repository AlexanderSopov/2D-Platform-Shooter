package edu.chl.Game.model.sound;

/**
 * 
 * @author Mansoor
 * @version 1.0
 */
public interface MusicInterface {


	/**
	 * Prints all Music in the list with name and path
	 */
	static void printMusicList() {}

	
	/**
	 * Add music to the music list
	 * @param filePath - Add music of the type Music.
	 */
	static void addMusic(String name, String filePath) {}
	
	
	/**
	 * Remove a music from the music list.
	 * @param music - Remove music of the type Music
	 */
	static void removeMusic(Music music) {}
	
	
	/**
	 * Clear the music list
	 */
	static void clearMusicList() {}
}
