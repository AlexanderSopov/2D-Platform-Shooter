package edu.chl.Game.sound;

import java.util.LinkedList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Prototype sound system
 * 
 * @author Mansoor
 * @version 1.0
 */
public abstract class Sound implements MusicInterface, SFXInterface {

	private LinkedList<Music> musicList = new LinkedList<Music>();
	private LinkedList<SFX> sfxList = new LinkedList<SFX>();
	private Clip clip;
	private String fileName;
	private SFXInterface sfx = new SFX();
	private MusicInterface music = new Music();
	
	public Sound() {
		
		
		try {	
			AudioInputStream ais =
				AudioSystem.getAudioInputStream(getClass().getResourceAsStream(
						getFileName()));
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(
				AudioFormat.Encoding.PCM_SIGNED,
				baseFormat.getSampleRate(),
				16,
				baseFormat.getChannels(),
				baseFormat.getChannels() * 2,
				baseFormat.getSampleRate(),
				false
			);
			AudioInputStream dais =
				AudioSystem.getAudioInputStream(
					decodeFormat, ais);
			clip = AudioSystem.getClip();
			clip.open(dais);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	//public abstract void 
	/**
	 * Get an instance of clip
	 * @return clip - An instance
	 */
	public Clip getClip() {
		return clip;
	}

	/**
	 * Get an instance of Music.
	 * @return music - An instance
	 */
	public MusicInterface getMusic() {
		return music;
	}
	/**
	 * Get an instance of Music.
	 * @return sfx - An instance
	 */
	public SFXInterface getSFX() {
		return sfx;
	}
	/**
	 * Get the file name
	 * @return fileName
	 */
	public String getFileName() {
		return this.fileName;
	}
	
	/**
	 * Set the file name
	 * @param fileName
	 */
	public abstract void setFileName(String fileName);
	/**
	 * Get the Music List.
	 * @return - Returns the Music List of type LinkedList<Music>
	 */
	public LinkedList<Music> getMusicList() {
		return musicList;
	}
	/**
	 * Add music to the music list
	 * @param music - Add music of the type Music.
	 */
	public void addMusic(Music music) {
		musicList.add(music);
	}
	/**
	 * Remove a music from the music list.
	 * @param music - Remove music of the type Music
	 */
	public void removeMusic(Music music) {
		musicList.remove(music);
	}
	/**
	 * Clear the music list
	 */
	public void clearMusicList() {
		musicList.clear();
	}
	/**
	 * Play the music
	 */
	public void play() {
		if(clip == null) return;
		stop();
		clip.setFramePosition(0);
		clip.start();
	}
	/**
	 * Stop the current music.
	 */
	public void stop() {
		if(clip.isRunning()) clip.stop();
	}
	/**
	 * Close the  current music.
	 */
	public void close() {
		stop();
		clip.close();
	}
	
	/**
	 * Get the SFX (Sound Effect) List
	 * @return - The SFX list of type LinkedList<SFX>
	 */
	public LinkedList<SFX> getSFXList() {
		return sfxList;
	}
	/**
	 * Add a sound effect to the list
	 * @param sfx - Type SFX of 
	 */
	public void addSFX(SFX sfx) {
		sfxList.add(sfx);
	}
	/**
	 * Clear the sound effect list
	 */
	public void clearSFXList() {
		sfxList.clear();
	}
	/**
	 * Remove a sound effect from the list
	 * @param sfx - Type SFX 
	 */
	public void removeSFX(SFX sfx) {
		sfxList.remove(sfx);
	}
}
