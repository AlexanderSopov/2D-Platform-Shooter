package edu.chl.Game.sound;

import javax.sound.sampled.Clip;

import edu.chl.Game.model.gameobject.entity.*;


/**
 * Sound Effects for the Game.
 */
public class SFX extends Sound {
	
	private EntityState entityState;
	private SFXInterface sfx = new SFX();
	
	public SFX() {
		super();
		entityState = new EntityState(FacingDirection.FacingRight);
		sfx = new SFX();
	}
	public void playerJumping() {
		
		if(entityState.isJumping()) {
			sfx.setFileName("Jump.mp3");
		}
	}
	/*
	public boolean checkIfPlayerJumping() {
		return entityState.isJumping();
	}*/
	public void hitWall() {
		
	}
	@Override
	public Clip getClip() {
		return null;
	}
	@Override
	public void setFileName(String fileName) {
		fileName = fileName;
	}
}	
