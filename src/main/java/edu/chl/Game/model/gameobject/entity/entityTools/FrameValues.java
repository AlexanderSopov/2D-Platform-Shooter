package edu.chl.Game.model.gameobject.entity.entityTools;
import edu.chl.Game.model.gameobject.entity.*;

public class FrameValues {
	private int frameDelayLimit;
	private int frameLimit;
	
	public FrameValues(int frameDelayLimit, int frameLimit){
		this.frameDelayLimit = frameDelayLimit;
		this.frameLimit = frameLimit;
	}
	
	public int getFrameLimit(){
		return frameLimit;
	}
	
	public int getFrameDelayLimit(){
		return frameDelayLimit;
	}

}
