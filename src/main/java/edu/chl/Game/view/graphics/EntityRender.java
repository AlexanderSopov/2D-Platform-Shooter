package edu.chl.Game.view.graphics;

import java.awt.Font;
import java.awt.Graphics;

import edu.chl.Game.model.gameobject.entity.*;

public class EntityRender {

	/**
	 * 
	 * Precondition: Postcondition:
	 * 
	 * @param g
	 * @param unit
	 * @param frame
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @throws Exception
	 */

	public void render(Graphics g, Sprite[] unit, int frame, int x, int y,
			int width, int height, FacingDirection f, int frameAmount,
			boolean moving) throws Exception {
		if ( (g == null) || (unit == null) ) {
			throw new NullPointerException("Parameter: < Graphics g > is null");
		} 

		if (!moving) {
			if (f == FacingDirection.FacingRight) {
				renderNotAnimateRight(g, unit, x, y, width, height);
			} else {
				renderNotAnimateLeft(g, unit, x, y, width, height, frameAmount);
			}
		}

		if (f == FacingDirection.FacingRight) {
			renderAnimateRight(g, unit, frame, x, y, width, height);
		} else {
			renderAnimateLeft(g, unit, frame, x, y, width, height, frameAmount);
		}
	}

	public void renderAnimateRight(Graphics g, Sprite[] unit, int frame, int x,
			int y, int width, int height) throws Exception {
		g.drawImage(unit[frame].getBufferedImage(), x, y, width, height, null);
	}

	public void renderAnimateLeft(Graphics g, Sprite[] unit, int frame, int x, int y, int width, int height, int frameAmount) throws Exception {
		g.drawImage(unit[frame + frameAmount].getBufferedImage(), x, y, width, height, null);
	}

	public void renderNotAnimateRight(Graphics g, Sprite[] unit, int x, int y,
			int width, int height) throws Exception {
		g.drawImage(unit[0].getBufferedImage(), x, y, width, height, null);
	}

	public void renderNotAnimateLeft(Graphics g, Sprite[] unit, int x, int y,
			int width, int height, int frameAmount) throws Exception {
		g.drawImage(unit[frameAmount].getBufferedImage(), x, y, width, height,
				null);
	}

}
