package edu.chl.Game.model.gameobject.entity.entityTools;

public class UnitAttraction {
	
	private int visionRange;
	private boolean foundTarget;
	
	public UnitAttraction(int visionRange){
		this.visionRange = visionRange;
	}
	
	public void searchAttractionArea(int vision, int target){
		if( ((vision-visionRange) <= target) && (target <= (vision+visionRange)) ){
			this.foundTarget = true;
		}
	}
	
	public boolean targetIsFound(){
		return foundTarget;
	}
	
	

}
