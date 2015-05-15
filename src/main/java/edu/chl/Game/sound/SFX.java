package edu.chl.Game.sound;

import java.util.HashMap;			
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.model.gameobject.entity.EntityState;


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
 * @author Mansoor
 * @version 2.0
 */
public class SFX extends Sound implements SFXInterface {
	
	private Map<String, Sound> sfxHashMap = new HashMap<String, Sound>();
	private SFX sfx;
	
	private EntityState entityState;
	private Entity entity;
	
	public SFX() {
		addSFX("jump", "Jump.mp3");
	}
	public SFX(Entity entity) {
		this.entity = entity;
		this.entityState = entity.getEntityState();
	}
	
	public SFX getSFX() {
		return sfx;
	}
	
	/**
	 * Get the SFX (Sound Effect) HashMap
	 * @return the SFX HashMap
	 */
	public Map<String, Sound> getSFXHashMap() {
		return sfxHashMap;
	}
	
	
	/**
	 * Add a Sound Effect to the list
	 * @param name - Give the sound effect a name. Name is then used to find it.
	 * @param filePath - The path to the sound effect.
	 */
	public void addSFX(String name, String filePath) {
		sfxHashMap.put(name, new Sound("/SFX/" + filePath));
	}
	
	
	/**
	 * Prints all Sound Effects in the list with name and path
	 */
	public void printSFXList() {
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
	public Sound getSoundFile(String soundFile) {
		return getSFXHashMap().get(soundFile);
	}
	
	
	/**
	 * Clearing the Sound Effect list
	 */
	public void clearSFXList() {
		getSFXHashMap().clear();
	}
	
	@Override
	public void removeSFX(SFX sfx) {
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * Player Jumping Sound
	 * @throws NullPointerException - If Jump keyword does not exist.
	 */
	public void isJumping() throws NullPointerException {
		
		if(entityState.isJumping()) {
			
			System.out.println("jumping");
			try {
				getSFXHashMap().get("jump").play();
			} catch (NullPointerException e) {
				e.printStackTrace();
				e.getMessage();
			}
		}
	}
	
	/**
	 * Player is hitting wall
	 */
	public void isWall() {
		
		
	}
	
	
	public void isMonster() {
		
		
	}
	
	
	public void isGunShot() {
		
		
	}
}	
