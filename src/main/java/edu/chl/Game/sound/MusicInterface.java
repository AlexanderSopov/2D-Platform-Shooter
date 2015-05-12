package edu.chl.Game.sound;

import java.util.LinkedList;


public interface MusicInterface {

	/**
	 * Get an instance of Music.
	 * @return music - An instance
	 */
	MusicInterface getMusic();
	/**
	 * Get the Music List.
	 * @return - Returns the Music List of type LinkedList<Music>
	 */
	LinkedList<Music> getMusicList();
	/**
	 * Add music to the music list
	 * @param music - Add music of the type Music.
	 */
	void addMusic(Music music);
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
