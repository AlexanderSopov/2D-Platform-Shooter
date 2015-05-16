package edu.chl.Game.model.gameobject.entity.entityTools;

import edu.chl.Game.model.gameobject.entity.*;

public class Cooldown {

	private FrameIterator fi;
	private boolean readyToAttack;

	public Cooldown() {
		fi = new FrameIterator(1, 180);
		readyToAttack = true;
	}

	public void activateCooldown() {
		readyToAttack = false;
	}

	public void updateCooldown() {
		if (!readyToAttack) {
			fi.updateFrameCounter();
			cooldownIsComplete();
		}
	}

	public boolean attackIsReady() {
		cooldownIsComplete();
		return readyToAttack;
	}
	
	public void cooldownIsComplete(){
		readyToAttack = !(fi.isActive());
	}

}
