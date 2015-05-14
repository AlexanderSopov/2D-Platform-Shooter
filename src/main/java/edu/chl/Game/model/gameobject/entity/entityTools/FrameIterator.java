package edu.chl.Game.model.gameobject.entity.entityTools;
import edu.chl.Game.model.gameobject.entity.*;
import edu.chl.Game.model.gameobject.entity.enemy.*;

public class FrameIterator {
	
	private Entity en;
	private int frame;
	private int frameDelay;
	private int frameDelayLimit;
	private int frameLimit;
	private boolean active;
	
	public FrameIterator(Entity en, int frameDelayLimit, int frameLimit){
		this.frameDelayLimit = frameDelayLimit;
		this.frameLimit = frameLimit;
		this.en = en;
		this.frame = 0;
		this.frameDelay = 0;
	}
	
	public void updateFrameCounter() {
		activate();
		increaseFrameDelay();
	}
	
	public void activate(){
		if(!active){
			active = true;
		}
	}
	
	public void deActivate(){
		active = false;
	}

	public void increaseFrameDelay() {
		frameDelay++;
		checkFrameDelayLimit();
	}

	public void checkFrameDelayLimit() {
		if (frameDelayLimit <= frameDelay) {
			increaseFrame();
			setFrameDelayToZero();
		}
	}

	public void increaseFrame() {
		frame++;
		checkFrameLimit();
	}

	public void checkFrameLimit() {
		if (frameLimit <= frame) {
			setFrameToZero();
		}
	}

	public void setFrameDelayToZero() {
		frameDelay = 0;
	}

	public void setFrameToZero() {
		frame = 0;
		deActivate();
	}

	public boolean isActive(){
		return active;
	}
	
	public int getFrame(){
		return frame;
	}
	
	public int getFrameDelay(){
		return frameDelay;
	}
	
}
