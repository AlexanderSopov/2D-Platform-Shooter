package edu.chl.Game.entity.levelup;

import java.awt.image.BufferedImage;

import edu.chl.Game.graphics.Sprite;
import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.Id;

/**
 * Visible Guns on the map that can be used by the Player.
 *
 * Clean and sustainable code. Class have responsible
 * for guns.
 * @author Mansoor	
 * @version 1.0
 */
public class Guns extends Items {
	
	private Sprite guns[] = new Sprite[2];
	private BufferedImage spr;
	
	public Guns(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler) {
		super(x, y, width, height, solid, id, handler);
		
		try {
			// First gun
			guns[0] = new Sprite(handler.getGuns(), 1, 1, 32, 32);
			guns[1] = new Sprite(handler.getGuns(), 1, 2, 32, 32);
		} catch(NullPointerException e) {
			StackTraceElement[] strace = e.getStackTrace();
			System.out.println(strace[0].toString());
		}
	}

	@Override
	public BufferedImage itemsGetBufferedImage() {

		for(int i=0; i<guns.length; i++) {
			spr = guns[i].getBufferedImage();
		}
		return this.spr;
	}
}
