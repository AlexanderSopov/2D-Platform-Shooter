package edu.chl.Game.model.sound;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.sound.sampled.FloatControl;

import edu.chl.Game.controller.RefreshTimer;
import edu.chl.Game.controller.State;

/**
 * Music for the Game.
 * 
 * @author Mansoor
 * @version 1.0
 */
public final class Music extends Sound implements MusicInterface {
	
	public Music(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}
	private static Map<String, Sound> musicHashMap = new HashMap<String, Sound>();
	
	
	/**
	 * Volume Controller
	 */
	private static FloatControl volumeMusicController;
	
	
	/**
	 * Volume set at 5
	 */
	private static float volumeMusic = 5;
	
	
	/**
	 * Plays the music depending on the state of the application
	 */
	public static void playMusic(){
		if(RefreshTimer.state == State.MAIN_MENU || RefreshTimer.state == State.MAP){
			playMenu();
		}else if(RefreshTimer.state == State.GAME){
			if(RefreshTimer.selectedMap == RefreshTimer.levels[0]){
				playWorldOneMapOne();
			}else if(RefreshTimer.selectedMap == RefreshTimer.levels[1]){
				playWorldTwoMapOne();
			}
		}
	}
	
	/**
	 * Stops all playning music
	 */
	public static void stopMusic(){
		for(Entry<String, Sound> f : musicHashMap.entrySet()){
			getMusic().get(f.getKey()).stop();
		}
	}
	
	
	public static FloatControl getVolMusicControl() {
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
		addMusic("advancedwarfare", "advancedwarfare.mp3");
		addMusic("awake", "Awake.mp3");
		addMusic("awalk", "AWalk.mp3");
		addMusic("nubus", "Nubus.mp3");
		addMusic("togeheter", "Togeheter.mp3");
		addMusic("walker", "Walker.mp3");
		addMusic("adair", "Adair.mp3");
		addMusic("luvdeluxe", "LuvDeluxe.mp3");
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
	public static void volumeMusicIncrease() {
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
	private static float getCurrentMusicVolume() {
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
	public static void stopWorldOneMapOne() {
		getMusic().get("gamemusic").stop();
	}
	public static void playWorldOneMapTwo() {
		getMusic().get("music").loop();
	}
	public static void stopWorldOneMapTwo() {
		getMusic().get("music").stop();
	}
	public static void playWorldOneMapThree() {
		getMusic().get("w1m1").loop();
	}
	public static void stopWorldOneMapThree() {
		getMusic().get("w1m1").stop();
	}
	public static void playWorldOneMapFour() {
		getMusic().get("music2").loop();
	}
		
	public static void stopWorldOneMapFour() {
		getMusic().get("music2").stop();
	}
	
	
	/**
	 * Music for W O R L D - T W O  
	 */
	public static void playWorldTwoMapOne() {
		getMusic().get("musicdrums").loop();
	}
	public static void stopWorldTwoMapOne() {
		getMusic().get("musicdrums").stop();
	}
	public static void playWorldTwoMapTwo() {
		getMusic().get("musicdrums2").loop();
	}
	public static void stopWorldTwoMapTwo() {
		getMusic().get("musicdrums2").stop();
	}
	public static void playWorldTwoMapThree() {
		getMusic().get("advancedwarfare").loop();
	}
	public static void stopWorldTwoMapThree() {
		getMusic().get("advancedwarfare").stop();	
	}
	public void playWorldTwoMapFour() {
		getMusic().get("awake").loop();
	}
	public void stopWorldTwoMapFour() {
		getMusic().get("awake").stop();
	}
	
	/**
	 * Music for W O R L D - T H R E E
	 * 
	 */
	public void playWorldThreeMapOne() {
		getMusic().get("awalk").loop();
	}
	public void stopWorldThreeMapOne() {
		getMusic().get("awalk").stop();
	}
	public void playWorldThreeMapTwo() {
		getMusic().get("nubus").loop();
	}
	public void stopWorldThreeMapTwo() {
		getMusic().get("nubus").stop();
	}
	public void playWorldThreeMapThree() {
		getMusic().get("togeheter").loop();
	}
	public void stopWorldThreeMapThree() {
		getMusic().get("togeheter").stop();
	}
	
	/**
	 * Music for W O R L D - F O U R 
	 * 
	 */
	public void playWorldFourMapOne() {
		getMusic().get("walker").loop();
	}
	public void stopWorldFourMapOne() {
		getMusic().get("walker").stop();
	}
	public void playWorldFoureMapTwo() {
		getMusic().get("adair").loop();
	}
	public void stopWorldFoureMapTwo() {
		getMusic().get("adair").stop();
	}
	public void playWorldFourMapThree() {
		getMusic().get("luvdeluxe").loop();
	}
	public void stopWorldFourMapThree() {
		getMusic().get("luvdeluxe").stop();
	}
}
