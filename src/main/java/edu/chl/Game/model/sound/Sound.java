package edu.chl.Game.model.sound;

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
	
	/**
	 * Global Volume Controller
	 */
	//private FloatControl globalVolControl;
	
	private int globalVol = 5;
	private AudioFormat soundFormat;
	private AudioFormat soundDecode;
	private AudioInputStream soundResult;
	private AudioInputStream soundInput;
	
	private static FloatControl volControl;
	
	public Sound(String path) {
		setSoundInput(path);
		setSoundFormat(getSoundInput().getFormat());
		initSound();

		try {
			initClip();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Use for init instance of sound
	 */
	public Sound() {
		
	}
	
	
	/**
	 * Set sound input. If the path valid then we get to access
	 * to the actual mp3 file properties such as channels, sample rate etc. 
	 * This is crucial since java generally don't support mp3 file playing.
	 * Therefore we need to access the file, get the file properties and convert it.
	 * @param path - Path to the sound.
	 * @throws IllegalArgumentException - Throws if path is incorrect
	 */
	private void setSoundInput(String path) throws IllegalArgumentException {
		if(checkPath(path)) {
			try {
				this.soundInput = AudioSystem.getAudioInputStream(
						getClass().
						getResourceAsStream(path));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	
	/**
	 * Get Sound Input
	 * @return soundInput - Type AudioInputStream
	 */
	private AudioInputStream getSoundInput() {
		return this.soundInput;
	}
	
	
	/**
	 * Set the sound format
	 * @param af
	 */
	private void setSoundFormat(AudioFormat af) {
		this.soundFormat = af;
	}
	
	/**
	 * Get the Sound Format
	 * @return soundFormat 
	 */
	private AudioFormat getSoundFormat() {
		return this.soundFormat;
	}

	
	/**
	 * Initialize Sound
	 */
	private void initSound() {
		initSoundDecoding();
		initSoundResult();
	}
	
	/**
	 * Check if the path is valid. All Sound related should
	 * be in project map Resource.
	 * @param path - Type string 
	 * @return boolean - True if the path is correct otherwise false.
	 */
	private boolean checkPath(String path) {
		boolean answer = false;
		if(path != null) {
			if(path.endsWith(".mp3")) {
				answer = true;
			}
		}
		return answer;
	}

	
	/**
	 * Initialize Sound Decoding<p>
	 * To understand below code then read the constructor below.
	 * 
	 * @see AudioFormat#AudioFormat(Encoding encoding, float sampleRate, 
	 * int sampleSizeInBits, int channels, 
	 * int frameSize, float frameRate, boolean bigEndian)
	 */
	private void initSoundDecoding() {
		setSoundDecode(new AudioFormat(
				AudioFormat.Encoding.PCM_SIGNED,
				getSoundFormat().getSampleRate(),
				16,
				getSoundFormat().getChannels(),
				getSoundFormat().getChannels() * 2,
				getSoundFormat().getSampleRate(),
				false
			));
	}
	
	
	/**
	 * Set the decoded version of the original sound<p>
	 * @see #setSoundInput(String) 
	 * @see AudioFormat#AudioFormat.AudioFormat(Encoding encoding, float sampleRate, 
	 * int sampleSizeInBits, int channels, 
	 * int frameSize, float frameRate, boolean bigEndian)
	 * @param ai - Type AudioFormat 
	 */
	private void setSoundDecode(AudioFormat ai) {
		this.soundDecode = ai;
	}
	
	
	/**
	 * Get the decoded sound.
	 * @return soundDecoded - Type AudioFormat
	 */
	private AudioFormat getSoundDecode() {
		return this.soundDecode;
	}
	
	/**
	 * Initialize sound result<p> 
	 */
	private void initSoundResult() {
		this.soundResult = AudioSystem.getAudioInputStream(
				getSoundDecode(), getSoundInput());
	}
	
	
	/**
	 * Get the sound result 
	 * @return soundResult - Type AudioInputStream
	 */
	private AudioInputStream getSoundResult() {
		return this.soundResult;
	}
	
	
	/**
	 * Initialize Clip 
	 * @throws Exception 
	 */
	private void initClip() throws Exception {
		try {
			this.clip = AudioSystem.getClip();
			this.clip.open(getSoundResult());
			volControl = (FloatControl)getClip().
					getControl(FloatControl.Type.MASTER_GAIN);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Get the Clip
	 * @return clip
	 */
	public Clip getClip() {
		return this.clip;
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
	
	
	/**
	 * Loop the Sound
	 */
	public void loop() {
		if(clip.isRunning()) return;
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	/**
	 * Get Volume Controller
	 * @return volControl - Type FloatControl
	 */
	public FloatControl getVolControl() {
		return volControl;
	}
	
	
	/**
	 * Decrease Global Volume with 2
	 */
	public void decreaseGlobalVol() {
		System.out.println("SOUND");
		getVolControl().setValue(getVolControl().getValue() - 2);
	}
	
	
	/**
	 * Increase Global Volume with 2
	 */
	public void increaseGlobalVol() {
		System.out.println("SOUND");
		if(checkVolValue()) {
			getVolControl().setValue(getVolControl().getValue() + 2);
		}
	}
	
	
	/**
	 * Check the Volume value. If the value meets the requirements - true otherwise false
	 * @return True if meets requirements else false
	 */
	private boolean checkVolValue() {
		boolean value = false;
		if(getVolControl().getValue() < getVolControl().getMaximum() && 
				getVolControl().getValue() > getVolControl().getMinimum()) {
			value = true;
		}
		return value;
	}
	
	
	/**
	 * Get the current global volume
	 * @return globalVol - An integer that returns the current volume.
	 */
	public int getGlobalVol() {
		return globalVol;
	}
	
	
	/**
	 * Set Global Volume<p>
	 * Add a float number within range -80 to 6 otherwise false argument.
	 * @param vol - Type float
	 * @throws IllegalArgumentException Throws if not meet requirements
	 */
	public void setGlobalVol(float vol) throws IllegalArgumentException {
		if(vol > -80 && vol < 6) {
			getVolControl().setValue(vol);
		} else {
			throw new IllegalArgumentException("Requested value: " + vol + 
					"\n" + "Stay within range -80 to 6");
		}
	}
}