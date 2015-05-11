package edu.chl.Game.sound;

import java.util.LinkedList;

import javax.sound.sampled.*;


/**
 * Music for the Game.
 */
public class Music {
	
	private LinkedList<Music> musicList = new LinkedList<>();
	private Clip clip;
	
	public Music(String s) {
		
		try {
			
			AudioInputStream ais =
				AudioSystem.getAudioInputStream(getClass().getResourceAsStream(s));
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
	
	/**
	 * Get the Music List.
	 * @return - Returns the Music List of type LinkedList<Music>
	 */
	public LinkedList<Music> getMusicList() {
		return this.musicList;
	}
	/**
	 * Add music to the music list
	 * @param music - Add music of the type Music.
	 */
	public void addMusicList(Music music) {
		musicList.add(music);
	}
	/**
	 * Remove a music from the music list.
	 * @param music - Remove music of the type Music
	 */
	public void removeMusicList(Music music) {
		musicList.remove(music);
	}
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
}














