package edu.chl.Game.model.gameobject.entity.player;
import edu.chl.Game.model.gameobject.entity.entityTools.*;
import edu.chl.Game.model.gameobject.entity.*;


public class WeaponProperties {

	private Entity en;
	private FrameIterator weaponCooldown;
	private boolean readyToFire;

	public WeaponProperties(Entity en, FrameIterator weaponCooldown) {
		this.weaponCooldown = weaponCooldown;
		readyToFire = true;
		this.en = en;
	}

	public void updateCooldown() {
		//checkMovement();
		if (!readyToFire) {
			weaponCooldown.updateFrameCounter();
		}
		checkFire();
	}
	
	public void checkMovement(){
		if(considerVelocity()){
			activateCooldown();
		}
	}
	
	public boolean considerVelocity(){
		return !(en.getVelX()==0);
	}

	public boolean coolDownIsComplete() {
		return !(weaponCooldown.isActive());
	}

	public void checkFire() {
		readyToFire = coolDownIsComplete();
	}

	public void activateCooldown() {
		readyToFire = false;
	}
	
	public boolean readyToFire() {
		return readyToFire;
	}
	
	
	
	//testMethod
	public FrameIterator getFrameIterator(){
		return weaponCooldown;
	}

	
	

}
