package edu.chl.Game.model.sound;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


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
	
	private Sound sound;
	
	/**
	 * Volume Controller
	 */
//	private static FloatControl volumeMusicController;
	/**
	 * Volume set at 5
	 */
	private static float volumeMusic = 5;
	
	public Music() {
		
		/**
		 * Volume Controller 
		 * Problem at .VOLUME
		 */
//		volumeMusicController = (FloatControl)getClip().
//				getControl(FloatControl.Type.MASTER_GAIN);
		sound = new Sound();

	}
	
//	public static FloatControl getVolMusicControl() {
//		return volumeMusicController;
//	}
	
	/**
	 * 
	 * @return
	 */
	private static Map<String, Sound> getMusic() {
		return musicHashMap;
	}
	
	public static void addToAccessMusic() {
		addMusic("w1m1", "w1m1.mp3");
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
	public static void addMusic(String name, String filePath) {
		getMusic().put(name, new Sound("/Music/" + filePath));
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
	
	
//	/**
//	 * Increase music volume with 2.
//	 */
//	public static void volumeMusicIncrease() {
//		getVolMusicControl().setValue(getCurrentMusicVolume() + 2);
//	}
//	/**
//	 * Decrease music volume with 2.
//	 */
//	public static void volumeMusicDecrease() {
//		getVolMusicControl().setValue(getCurrentMusicVolume() - 2);
//	}
	
	/**
	 * Get the current volume of Music.
	 * @return volumeMusic - Current Volume Set
	 */
	private static float getCurrentMusicVolume() {
		return volumeMusic;
	}
	
	
	
	
	
	public void playMenu() {
			
	}
	public void stopMenu() {
		
	}
	
	/**
	 * Music for W O R L D - O N E 
	 */
	public static void playWorldOneMapOne() {
		try {
//			sound.getGlobalVolControl().setValue(10);
			getMusic().get("w1m1").play();
		} catch (NullPointerException e) {
			System.out.println("Problem @ World 1 Map 1");
			e.printStackTrace();
		}
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














