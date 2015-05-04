package edu.chl.Game.entity;

public class GravitationalProperties {
	UnitProperties unitProperties;
	EntityProperties entityProperties;
	EntityState entityState;
	
	public GravitationalProperties(UnitProperties unitProperties, EntityProperties entityProperties, EntityState entityState){
		this.unitProperties = unitProperties;
		this.entityProperties = entityProperties;
		this.entityState = entityState;
	}
	
	public void jumpingMechanics(){
		if (entityState.isJumping()) {													// if the player is jumping
			entityProperties.setGravity(entityProperties.getGravity() - 0.1);			// reduces the gravity by 0.1
			unitProperties.setVelY(((int) -entityProperties.getGravity()));				// sets the Y-velocity to the negative gravity
			if (entityProperties.getGravity() <= 0.0) {									// if the gravity is below or equal to 0
				entityState.setFalling(true);											// sets falling to true 
				entityState.setJumping(false);											// sets the jumping to false
			}
		}
	}
	
	public void fallingMechanics(){
		if (entityState.isFalling()) {													
			entityProperties.setGravity(entityProperties.getGravity() + 0.1);		
			unitProperties.setVelY(((int) entityProperties.getGravity()));		
		}
	}
	

	
	
}
