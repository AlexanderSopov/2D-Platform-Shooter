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
		ep.setFrameDelay(ep.getFrameDelay() + 1);
		checkFrameDelayLimit();
	}

	public void checkFrameDelayLimit() {
		if (frameLimit <= ep.getFrameDelay()) {
			increaseFrame();
			setFrameDelayToZero();
		}
	}

	public void increaseFrame() {
		ep.setFrame(ep.getFrame() + 1);
		checkFrameLimit();
	}

	public void checkFrameLimit() {
		if (frameDelayLimit <= ep.getFrame()) {
			setFrameToZero();
		}
	}

	public void setFrameDelayToZero() {
		ep.setFrameDelay(0);
	}

	public void setFrameToZero() {
		ep.setFrame(0);
	}

}
