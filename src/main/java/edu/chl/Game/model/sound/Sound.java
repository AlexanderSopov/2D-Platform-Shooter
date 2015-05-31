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
	
	
	/**
	 * Init clip
	 * @see Clip
	 */
	private Clip clip;
	
	private static boolean allow = true;
	private static float preVol = 0;
	/**
	 * Global Volume 
	 */
	//private static float globalVol;
	
	
	/**
	 * Storage for Sound Format
	 */
	private AudioFormat soundFormat;
	
	
	/**
	 * Storage for Sound Decode
	 */
	private AudioFormat soundDecode;
	
	
	/**
	 * Storage for Sound Result
	 */
	private AudioInputStream soundResult;
	
	
	/**
	 * Storage for Sound Input
	 */
	private AudioInputStream soundInput;
	
	
	/**
	 * Sound Volume Controller
	 */
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
		getVolControl().setValue(4);
		setCurrentVolume();
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
						getClass().getResource(path));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException("Please enter valid path");
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
		return soundResult;
	}
	
	
	/**
	 * Initialize Clip 
	 * @throws Exception 
	 */
	private void initClip() throws Exception {
		try {
			clip = AudioSystem.getClip();
			clip.open(getSoundResult());
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
	private Clip getClip() {
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
	private static FloatControl getVolControl() {
		return volControl;
	}
	
	private static boolean isAllowChange() {
		return allow;
	}
	
	
	private static void setAllowChange(boolean value) {
		allow = value;
	}
	
	
	private static void checkSoundVolume() {
		float vol = convertToWholeNumb(getVolControl().getValue());
		if(vol == 0 || vol == 10) {
			setAllowChange(false);
		} else {
			setAllowChange(true);
		}
	}
	
	
	/**
	 * Increase Global Volume with 1
	 */
	public static void increaseGlobalVol() {
		System.out.println("SOUND Increase");
		
		float f = getCurrentVolume() + 1;
			getVolControl().setValue(convertToWholeNumb(f));
//			clip.start();
			setCurrentVolume();
	}
	
	/**
	 * Decrease Global Volume with 1
	 * @throws Exception 
	 */
	public static void decreaseGlobalVol() {
		System.out.println("SOUND Decrease");
		
			float f = getCurrentVolume() - 1;
			getVolControl().setValue(convertToWholeNumb(f));
			setCurrentVolume();
	}
	
	
	/**
	 * Convert -80-6 to 0-10 volume set<p>
	 * -80 is 0
	 * -43 is 5
	 * 6 is 10
	 * Increasing or Decreasing with 9 
	 * @param preVol
	 * @return postVol 
	 */
	private static float convertToWholeNumb(float preVol) {
		float postVol = 0;
		if(preVol <= 10 && preVol >= 0) {
			if(preVol == 10) {
				postVol = 6;
			} else if(preVol == 9) {
				setCurrentVolume();
				postVol = -9;
			} else if(preVol == 8) {
				setCurrentVolume();
				postVol = -18;
			} else if(preVol == 7) {
				setCurrentVolume();
				postVol = -27;
			} else if(preVol == 6) {
				postVol = -36;
			} else if(preVol == 5) {
				postVol = -43;
			} else if(preVol == 4) {
				postVol = -52;
			} else if(preVol == 3) {
				postVol = -61;
			} else if(preVol == 2) {
				postVol = -70;
			} else if(preVol == 1 ) {
				postVol = -79;
			} else if(preVol == 0) {
				postVol = -80;
			}
		} 
		return postVol;
	}
	
	
	/**
	 * Convert from scale -80-6 to 0-10<p>
	 * The purpose of this converting is to display 
	 * the current volume in option, increase, decrease
	 * and set a volume in a much easier way.
	 * 
	 * @param value - Set a float number from -80-6 to convert
	 * @return postValue - Float type which contains the converted number
	 */
	private static float convertToFCNumb(float value) {
		float postValue = 0;
		if(value >= -80 && value < -79) {
			postValue = 0;
		} else if(value >= -79 && value < -70) {
			postValue = 1; 
		} else if(value >= -70 && value < -61) {
			postValue = 2;
		} else if(value >= -61 && value < -52) {
			postValue = 3;
		} else if(value >= -52 && value < -43) {
			postValue = 4;
		} else if(value >= -43 && value < -36) {
			postValue = 5;
		} else if(value >= -36 && value < -27) {
			postValue = 6;
		} else if(value >= -27 && value < -18) {
			postValue = 7;
		} else if(value >= -18 && value < -9) {
			postValue = 8;
		} else if(value >= -9 && value < 6) {
			postValue = 9;
		} else if(value == 6) {
			postValue = 10;
		}
		return postValue;
	}
	
	
	/**
	 * Mute all Sound
	 */
	public static void setSoundOnOff() {
		setCurrentVolume();
		if(getVolControl().getValue() != -80) {
			getVolControl().setValue(-80);
		} else {
			getVolControl().setValue(getCurrentVolume());
		}
	}
	
	
	/**
	 * Get the Current Volume in scale 0-10
	 * @return preVol - Float type
	 */
	public static float getCurrentVolume() {
		return preVol;
	}
	
	/**
	 * Sets the current volume
	 */
	private static void setCurrentVolume() {
		preVol = convertToFCNumb(getVolControl().getValue());
	}
}