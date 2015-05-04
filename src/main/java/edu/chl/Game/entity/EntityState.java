package edu.chl.Game.entity;

public class EntityState {
	
	private boolean jumping = false;
	private boolean falling = true;
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

}
