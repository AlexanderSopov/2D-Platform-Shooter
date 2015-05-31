package edu.chl.Game.model.gameobject;


public class UnitState {

	private boolean solid;
	private boolean animate;
	private Id id;
	
	public UnitState(Id id, boolean solid){
		this.id = id;
		this.solid = solid;
		this.animate = false;
	}

	public Id getId() {
		return id;
	}

	public boolean isSolid() {
		return solid;
	}

	public boolean isAnimate() {
		return animate;
	}

	public void setAnimate(boolean animate) {
		this.animate = animate;
	}

}
