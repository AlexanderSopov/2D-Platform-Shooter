package edu.chl.Game.model.gameobject.entity.entityTools;
import edu.chl.Game.model.gameobject.entity.*;
import edu.chl.Game.model.gameobject.entity.enemy.*;

public class FrameIterator {
	
	private EnemyUnit eu;
	private int frameDelayLimit;
	private int frameLimit;
	
	public FrameIterator(EnemyUnit eu, int frameDelayLimit, int frameLimit){
		this.frameDelayLimit = frameDelayLimit;
		this.frameLimit = frameLimit;
		this.eu = eu;
	}
	
	public void iterateThroughFrames() {
		increaseFrameDelay();
	}

	public void increaseFrameDelay() {
		eu.getEntityProperties().setFrameDelay(eu.getEntityProperties().getFrameDelay() + 1);
		checkFrameDelayLimit();
	}

	public void checkFrameDelayLimit() {
		if (frameDelayLimit <= eu.getEntityProperties().getFrameDelay()) {
			increaseFrame();
			setFrameDelayToZero();
		}
	}

	public void increaseFrame() {
		eu.getEntityProperties().setFrame(eu.getEntityProperties().getFrame() + 1);
		checkFrameLimit();
	}

	public void checkFrameLimit() {
		if (frameLimit <= eu.getEntityProperties().getFrame()) {
			setFrameToZero();
		}
	}

	public void setFrameDelayToZero() {
		eu.getEntityProperties().setFrameDelay(0);
	}

	public void setFrameToZero() {
		eu.getEntityProperties().setFrame(0);
	}

}
