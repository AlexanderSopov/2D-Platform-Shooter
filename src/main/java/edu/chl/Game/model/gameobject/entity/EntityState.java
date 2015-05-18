package edu.chl.Game.model.gameobject.entity;

public class EntityState {
	
	private boolean jumping = false;
	private boolean falling = true;
	private boolean contactWithGround = false;
	private FacingDirection facingDirection;
	
	public EntityState(FacingDirection facingDirection){
		this.facingDirection = facingDirection;
	}
	
	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	
	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	
	public void setFacingDirection(FacingDirection d) {
		facingDirection = d;
	}

	public FacingDirection getFacingDirection() {
		return facingDirection;
	}
	
	public boolean getContactWithGround() {
		return contactWithGround;
	}

	public void setContactWithGround(boolean contactWithGround) {
		this.contactWithGround = contactWithGround;
	}

}
