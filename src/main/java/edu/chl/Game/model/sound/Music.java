package edu.chl.Game.model.sound;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


import edu.chl.Game.controller.*;

/**
 * Music for the Game.
 * 
 * 
 *   								WHAT LEFT:
 * 						CREATE A MUSIC VOLUME CONTROLLER
 * @author Mansoor
 * @version 1.0
 */
public final class Music extends Sound implements MusicInterface {
	
	private static Map<String, Sound> musicHashMap = new HashMap<String, Sound>();
	
	private Sound sound = new Sound();
	
	/**
	 * Volume Controller
	 */
	private FloatControl volumeMusicController;
	/**
	 * Volume set at 5
	 */
	private float volumeMusic = 5;
	
	public Music() {
		

		

	}
	
	public FloatControl getVolMusicControl() {
		return volumeMusicController;
	}
	
	/**
	 * 
	 * @return
	 */
	private static Map<String, Sound> getMusic() {
		return musicHashMap;
	}
	
	public void vol() {
		
		/**
		 * Volume Controller 
		 * Problem at .VOLUME
//		 */
//		volumeMusicController = (FloatControl)Sound.getClip().
//				getControl(FloatControl.Type.MASTER_GAIN);
	}
	
	public static void addToAccessMusic() {
		addMusic("w1m1", "w1m1.mp3");
		addMusic("menu", "menu.mp3");
		addMusic("music", "music.mp3");
		addMusic("music2", "music2.mp3");
		addMusic("musicdrums", "musicdrums.mp3");
		addMusic("musicdrums2", "musicdrums2.mp3");
		addMusic("gamemusic", "gameMusic.mp3");
	}
	
	/**
	 * Print the Music List
	 * which have all the music.
	 */
	public static void printMusicList() {
		Set<Entry<String, Sound>> hashSet = getMusic().entrySet();
		
		if(hashSet.size() > 0) {
			for(Entry entry : hashSet) {
				System.out.println("Music name: " + entry.getKey() +
						"\n" + "Music value: " + entry.getValue());
			}
		} else {
			System.out.println("Music list is empty");
		}
	}

	/**
	 * Add music to the music list. 
	 * File format MUST be mp3.
	 * @param name - Enter keyword for the music.
	 * @param filePath - Name of the music. For example Music2.mp3
	 */
	private static void addMusic(String name, String filePath) {
		getMusic().put(name, new Sound(("/Music/" + filePath)));
	}
	
	/**
	 * Remove a music from the music list
	 * @param music 
	 */
	public static void removeMusic(Music music) {
		getMusic().remove(music);
	}

	/**
	 * Clear the music list
	 */
	public static void clearMusicList() {
		getMusic().clear();
	}
	
	
	/**
	 * Increase music volume with 2.
	 */
	public void volumeMusicIncrease() {
		getVolMusicControl().setValue(getCurrentMusicVolume() + 2);
	}
	/**
	 * Decrease music volume with 2.
	 */
	public void volumeMusicDecrease() {
		getVolMusicControl().setValue(getCurrentMusicVolume() - 2);
	}
	
	public void setCurrentMusicVolume(float f) {
		getVolMusicControl().setValue(f);
	}
	/**
	 * Get the current volume of Music.
	 * @return volumeMusic - Current Volume Set
	 */
	private float getCurrentMusicVolume() {
		return volumeMusic;
	}
	
	
	
	
	
	public static void playMenu() {
		getMusic().get("menu").loop();
	}
	public static void stopMenu() {
		getMusic().get("menu").stop();
	}
	
	/**
	 * Play music suited for World One Map One
	 */
	public static void playWorldOneMapOne() {
		getMusic().get("gamemusic").loop();
	}
	public void stopWorldOneMapOne() {
		
	}
	public void playWorldOneMapTwo() {
		
	}
	public void stopWorldOneMapTwo() {
		
	}
	public void playWorldOneMapThree() {
		
	}
	public void stopWorldOneMapThree() {
		
	}
	public void playWorldOneMapFour() {
	}
		
	public void stopWorldOneMapFour() {
		
	}
	
	
	/**
	 * Music for W O R L D - T W O  
	 */
	public void playWorldTwoMapOne() {
		
	}
	public void stopWorldTwoMapOne() {
		
	}
	public void playWorldTwoMapTwo() {
		
	}
	public void stopWorldTwoMapTwo() {
		
	}
	public void playWorldTwoMapThree() {
		
	}
	public void stopWorldTwoMapThree() {
		
	}
	public void playWorldTwoMapFour() {
	}
		
	public void stopWorldTwoMapFour() {
		
	}
	
	/**
	 * Music for W O R L D - T H R E E
	 * 
	 */
	public void playWorldThreeMapOne() {
		
	}
	public void stopWorldThreeMapOne() {
		
	}
	public void playWorldThreeMapTwo() {
		
	}
	public void stopWorldThreeMapTwo() {
		
	}
	public void playWorldThreeMapThree() {
		
	}
	public void stopWorldThreeMapThree() {
		
	}
	
	/**
	 * Music for W O R L D - F O U R 
	 * 
	 */
	public void playWorldFourMapOne() {
		
	}
	public void stopWorldFourMapOne() {
		
	}
	public void playWorldFoureMapTwo() {
		
	}
	public void stopWorldFoureMapTwo() {
		
	}
	public void playWorldFourMapThree() {
		
	}
	public void stopWorldFourMapThree() {
		
	}
}
