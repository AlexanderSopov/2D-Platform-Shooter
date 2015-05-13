package edu.chl.Game.sound;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


/**
 * Music for the Game.
 * 
 * @author Mansoor
 * @version 1.0
 */
public class Music extends Sound implements MusicInterface {
	
	private Map<String, Sound> musicHashMap = new HashMap<String, Sound>();
	private Music music;
	
	public Music() {
		
		addMusic("intro", "intro.mp3");
	}
	
	/**
	 * 
	 * @return
	 */
	public Map<String, Sound> getMusicHashMap() {
		return this.musicHashMap;
	}
	
	@Override
	public Music getMusic() {
		return music;
	}

	@Override
	public void printMusicList() {
		Set<Entry<String, Sound>> hashSet = getMusicHashMap().entrySet();
		
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
	@Override
	public void addMusic(String name, String filePath) {
		getMusicHashMap().put(name, new Sound("/Music/" + filePath));
	}
	
	/**
	 * Remove a music from the music list
	 * @param music 
	 */
	@Override
	public void removeMusic(Music music) {
		getMusicHashMap().remove(music);
	}

	/**
	 * Clear the music list
	 */
	@Override
	public void clearMusicList() {
		getMusicHashMap().clear();
	}
	
	public void playIntro() {
		getMusicHashMap().get("intro").play();
	}
}














