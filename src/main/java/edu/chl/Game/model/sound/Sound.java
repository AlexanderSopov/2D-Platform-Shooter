package edu.chl.Game.model.sound;

import javax.sound.sampled.*;

/**
 * Sound class have common traits for
 * SFX (Sound Effects) and Music.
 * 
 * 
 * 
 * 								WHAT LEFT:
 * 						CREATE A GLOBAL VOLUME CONTROLLER
 * 
 * @author Mansoor 
 * @version 2.0
 */
public class Sound {
	
	
	private Clip clip;
	
	/**
	 * Global Volume Controller
	 */
	private FloatControl globalVolControl;
	
	private static int globalVol = 5;
	
	
	public Sound(String path) {
		
		try {
			// Variable is null
//			globalVolControl = (FloatControl)getClip()
//					.getControl(FloatControl.Type.MASTER_GAIN);
			/**
			 * InputStream is top of the food chain that have 
			 * control over ALL input to the machine.
			 * AudioInputStream is a subclass of InputStream class.
			 * We create a variable of type AudioInputStream because
			 * of an input of audio. 
			 */
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
	
	public Sound() {}
	
	public Clip getClip() {
		return clip;
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
	
	public void loop() {
		if(clip.isRunning()) return;
		clip.start();
	}
	
	/**
	 * 
	 * @return
	 */
	public FloatControl getGlobalVolControl() {
		return globalVolControl;
	}
	
	/**
	 * Decrease Global Volume with 2
	 */
	public void decreaseGlobalVol() {
		getGlobalVolControl().setValue(getGlobalVol() - 2);
	}
	
	/**
	 * Increase Global Volume with 2
	 */
	public void increaseGlobalVol() {
		getGlobalVolControl().setValue(getGlobalVol() + 2);
	}
		
	/**
	 * Get the current global volume
	 * @return globalVol - An integer that returns the current volume.
	 */
	public static int getGlobalVol() {
		return globalVol;
	}
}
