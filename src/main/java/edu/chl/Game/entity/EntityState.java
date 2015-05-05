package edu.chl.Game.entity;

public class EntityState {
	
	private FacingDirection facingDirection;
	
	public EntityState(FacingDirection facingDirection){
		this.facingDirection = facingDirection;
	}
	
	public void setFacingDirection(FacingDirection d) {
		facingDirection = d;
	}

	public FacingDirection getFacingDirection() {
		return facingDirection;
	}

}
