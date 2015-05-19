package edu.chl.Game.model.gameobject.entity.player;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.model.physics.ProjectileDetection;
import edu.chl.Game.view.graphics.Sprite;
import edu.chl.Game.view.graphics.SpriteSheet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

/**
 *
 * @author Rasmus
 */

public class Pistol extends Entity {

	private int centerX = getWidth() / 2;
	private int centerY = getHeight() / 2;
	private double angle;
	private final GameCursor gc;
	private final Entity en;

	private BufferedImage image;

	public Pistol(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler, GameCursor gc, Entity en) {
		super(x, y, width, height, solid, id, handler);
		this.gc = handler.getGameCursor();
		try {
			image = ImageIO.read(getClass().getResource("/we00.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(height);

		this.en = en;

	}

	@Override
	public void render(Graphics g) {
		if(gc != null){
		g.setColor(Color.BLUE);
		((Graphics2D) g).rotate(angle, en.getX() + en.getWidth() / 2, en.getY()
				+ en.getHeight() / 2);
		g.drawImage(image, this.centerX - image.getWidth() / 12, this.centerY
				- image.getHeight() / 6 + 5, image.getWidth() / 3,
				image.getHeight() / 3, null);
		((Graphics2D) g).rotate(-angle, en.getX() + en.getWidth() / 2,
				en.getY() + en.getHeight() / 2);
		}
	}

	@Override
	public void update() {
		this.centerX = en.getX() + en.getWidth() / 2;
		this.centerY = en.getY() + en.getHeight() / 2;
		if(gc != null){
			angle = Math.atan2(centerY - gc.getY(), centerX - gc.getX()) - Math.PI;
		}

	}

	public void fire() {
		
	}

	public void shoot() {
		
		Bullet b = new Bullet(this.centerX, this.centerY, 10, 10, true,
				Id.bullet, getHandler(), 10, this.angle, (image.getWidth() / 6)+30,
				0);
		getHandler().addEntity(b);
		//if (en.getWeaponProperties().readyToFire()) {
			//fire();
			//en.getWeaponProperties().activateCooldown();
		//}
	}

}
