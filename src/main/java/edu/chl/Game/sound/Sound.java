package edu.chl.Game.sound;

import javax.sound.sampled.*;

/**
 * Sound class have common traits for
 * SFX (Sound Effects) and Music.
 * 
 * @author Mansoor
 * @version 2.0
 */
public class Sound {
	
	private Clip clip;
	
	public Sound(String path) {
		
		try {
			
			AudioInputStream ais =
				AudioSystem.getAudioInputStream(
						getClass()
						.getResourceAsStream(path)
				);
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
	
	public Sound() {
		
	}
	
	/**
	 * Play the Sound
	 */
	public void play() {
		if(clip == null) return;
		stop();
		clip.setFramePosition(0);
		clip.start();
	}
	
	
	/**
	 * Stop the sound
	 */
	public void stop() {
		if(clip.isRunning()) 
			clip.stop();
	}
	
	
	/**
	 * Close the sound
	 */
	public void close() {
		stop();
		clip.close();
	}
}
