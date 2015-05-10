package edu.chl.Game.entity;

public class GravitationalProperties {
	private UnitProperties unitProperties;
	private EntityProperties entityProperties;
	private EntityState entityState;
	
	public GravitationalProperties(UnitProperties unitProperties, EntityProperties entityProperties, EntityState entityState){
		this.unitProperties = unitProperties;
		this.entityProperties = entityProperties;
		this.entityState = entityState;

	}
	
	public void jumpingMechanics(){
		if(entityState.getContactWithGround()){
			entityProperties.setGravity(25.0);
		}
		
		if (entityState.isJumping()) {
			entityState.setContactWithGround(false);
			entityProperties.setGravity(entityProperties.getGravity() - 1.0);			
			unitProperties.setVelY(((int) -entityProperties.getGravity()));				
			if (entityProperties.getGravity() <= 0.0) {									
				entityState.setFalling(true);											 
				entityState.setJumping(false);											
			}
		}
	}
	
	public void fallingMechanics(){
		if (entityState.isFalling()) {													
			entityProperties.setGravity(entityProperties.getGravity() + 1.0);		
			unitProperties.setVelY(((int) entityProperties.getGravity()));		
		}
	}
	

	
	
}
