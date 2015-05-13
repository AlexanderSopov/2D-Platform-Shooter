package edu.chl.Game.sound;

/**
 * 
 * @author Mansoor
 * @version 1.0
 */
public interface MusicInterface {

	/**
	 * Get an instance of Music.
	 * @return music - An instance
	 */
	Music getMusic();

	/**
	 * Prints all Music in the list with name and path
	 */
	void printMusicList();

	
	/**
	 * Add music to the music list
	 * @param filePath - Add music of the type Music.
	 */
	void addMusic(String name, String filePath);
	
	
	/**
	 * Remove a music from the music list.
	 * @param music - Remove music of the type Music
	 */
	void removeMusic(Music music);
	
	
	/**
	 * Clear the music list
	 */
	void clearMusicList();
}
