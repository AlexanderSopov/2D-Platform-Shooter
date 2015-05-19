package edu.chl.Game.model.sound;

//import javax.sound.sampled.*;

/**
 * Sound class have common traits for
 * SFX (Sound Effects) and Music.
 * 
 * @author Mansoor 
 * @version 2.0
 */
//public abstract class SoundBackup {
//	
//	
//	private static Clip clip;
//	
//	/**
//	 * Volume Controller
//	 */
//	private FloatControl floatControl;
//	
//
//
//	
//	public Sound(String path) {
//		
//		try {
//			
//			/**
//			 * Volume Controller 
//			 * Problem at .VOLUME
//			 */
//			floatControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
//			
//			/**
//			 * InputStream is top of the food chain that have 
//			 * control over ALL input to the machine.
//			 * AudioInputStream is a subclass of InputStream class.
//			 * We create a variable of type AudioInputStream because
//			 * of an input of audio. 
//			 */
//			AudioInputStream ais =
//				AudioSystem.getAudioInputStream(
//						getClass()
//						.getResourceAsStream(path)
//				);
//			
//			AudioFormat baseFormat = ais.getFormat();
//			AudioFormat decodeFormat = new AudioFormat(
//				AudioFormat.Encoding.PCM_SIGNED,
//				baseFormat.getSampleRate(),
//				16,
//				baseFormat.getChannels(),
//				baseFormat.getChannels() * 2,
//				baseFormat.getSampleRate(),
//				false
//			);
//			AudioInputStream dais =
//				AudioSystem.getAudioInputStream(
//					decodeFormat, ais);
//			clip = AudioSystem.getClip();
//			clip.open(dais);
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public Sound() {
//		
//	}
//	
//	/**
//	 * Play the Sound
//	 */
//	public void play() {
//		if(clip == null) return;
//		stop();
//		clip.setFramePosition(0);
//		clip.start();
//	}
//	
//	
//	/**
//	 * Stop the sound
//	 */
//	public void stop() {
//		if(clip.isRunning()) 
//			clip.stop();
//	}
//	
//	/**
//	 * Close the sound
//	 */
//	public void close() {
//		stop();
//		clip.close();
//	}
//	
//	public void loop() {
//		if(clip.isRunning()) return;
//		clip.start();
//	}
//	
//	/**
//	 * 							MAJOR PROBLEM
//	 * 					INCREASING, DECREASING VOLUME AND SFX
//	 * 					AT THE SAME TIME. 
//	 * 							
//	 * 							NEED TO BE FIXED !!
//	 * @return
//	 */
//	public FloatControl getFloatControl() {
//		return floatControl;
//	}
//	
//}

