package edu.chl.Game.model.sound;

import java.io.File;
import java.util.HashMap;				
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.sound.sampled.FloatControl;


/**
 * Sound Effects for the Game.
 * 
 * THE PURPOSE OF THIS CLASS IS TO CHECK THE OTHER CLASSES
 * NOT, NOT BEING IMPLEMENTED AS A DELEGATION IN CLASSES AND CALL.
 * 
 * NOT FINISHED. 
 * ADD MORE METHODS TO COVER ALL SFX POSSIBILITES
 * ADD MORE SOUNDS EFFECTS (MP3)
 * 
 * 								WHAT LEFT:
 * 						CREATE A SFX VOLUME CONTROLLER
 * @author Mansoor
 * @version 2.0
 */
public final class SFX extends Sound implements SFXInterface {

	private static Map<String, Sound> sfxHashMap = new HashMap<String, Sound>();
	private SFX sfx;

	
	/**
	 * Volume Controller
	 */
	private FloatControl volSFXControl;
	
	/**
	 * Volume set at 5.
	 */
	private static float volSFX = 0;
	
	private SFX() {
		
		/**
		 * Volume Controller 
		 * Problem at .VOLUME
		 */
		//volSFXControl = (FloatControl)getClip().getControl(FloatControl.Type.MASTER_GAIN);
		
		
	}

	private static void addToAccessSFX() {
		addSFX("monster", "monster.mp3");
		addSFX("shot", "shot.mp3");	
	}
	
//	private SFX(Entity entity) {
//		this.entity = entity;
//		this.entityState = entity.getEntityState();
//	}
	
	private FloatControl getVolSFXControl() {
		return volSFXControl;
	}
	
	/**
	 * Get the SFX (Sound Effect) HashMap
	 * @return the SFX HashMap
	 */
	private static Map<String, Sound> getSFX() {
		return sfxHashMap;
	}
	
	
	/**
	 * Add a Sound Effect to the list
	 * @param name - Give the sound effect a name. Name is then used to find it.
	 * @param filePath - The path to the sound effect.
	 */
	private static void addSFX(String name, String filePath) {
		sfxHashMap.put(name, new Sound("/SFX/" + filePath));
	}
	
	
	/**
	 * Prints all Sound Effects in the list with name and path
	 */
	 private void printSFXList() {
		Set<Entry<String, Sound>> hashSet = sfxHashMap.entrySet();
		
		// BELOW CODE IS NOT GORGEOUS !
		if(hashSet.size() > 0) {
			for(Entry entry : hashSet) {
				System.out.println("SFX name: " + entry.getKey() +
						"\n" + "SFX value: " + entry.getValue());
			}
		} else {
			System.out.println("SFX list is empty");
		}
	}

	
	/**
	 * Get the value
	 * @param soundFile - Enter the name of the file you need.
	 * @return Returns the need.
	 */
	private Sound getSoundFile(String soundFile) {
		return getSFX().get(soundFile);
	}
	
	
	/**
	 * Clearing the Sound Effect list
	 */
	private static void clearSFXList() {
		getSFX().clear();
	}
	
	
	/**
	 * Remove a Sound Effect from the list SFX list.
	 * @param sfx
	 */
	private static void removeSFX(SFX sfx) {
		getSFX().remove(sfx);
	}
	
	/**
	 * Increase music volume with 2.
	 */
	public void volumeSFXIncrease() {
		getVolSFXControl().setValue(getCurrentSFXVolume() + 2);
	}
	/**
	 * Decrease music volume with 2.
	 */
	public void volumeSFXDecrease() {
		getVolSFXControl().setValue(getCurrentSFXVolume() - 2);
	}
	
	/**
	 * Get the current volume of SFX (Sound Effect). 
	 * @return volumeSFX - Current Volume Set
	 */
	private static float getCurrentSFXVolume() {
		return volSFX;
	}
	
	
	/**
	 * Player Jumping Sound
	 */
	public static void playPlayerJumping() {
		getSFX().get("jump").play();
	}
	
	public static void stopPlayerJumping() {
		getSFX().get("jump").stop();
	}
	
	private static void playMonsterJumping() {
		
		
	}
	
	/**
	 * Player is hitting wall
	 */
	private static void playIsHittingWall() {
		
		
	}
	
	
	private static void playPlayerDead() {
		
		
	}
	
	private static void playMonsterDead() {
		
		
	}
	
	/**
	 * Player is hitting Monster. This method plays the sound of it.
	 */
	public static void playerHitMonster() {
		getSFX().get("monster").play();
	}
	
	
	/**
	 * The default gun which the player have from the beginning.
	 * This method plays the sound of it.
	 */
	public static void playDefaultGunShot() {
		getSFX().get("shoot").play();
	}
	
	
	/**
	 * The default gun which the player have from the beginning.
	 * This method stops the sound of it.
	 */
	public static void stopDefaultGunShot() {
		getSFX().get("shoot").stop();
	}
}	
