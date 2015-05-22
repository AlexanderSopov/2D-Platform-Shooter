package edu.chl.Game.model.gameobject.entity.items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.model.gameobject.entity.player.Bullet;
import edu.chl.Game.model.gameobject.entity.player.GameCursor;

public class W1 extends Item{
	
		private Entity en;
		private GameCursor gc;
		private double angle;
		private BufferedImage image;
		private int centerX = getWidth() / 2;
		private int centerY = getHeight() / 2;
		private GameHandler gh;
		

	public W1(int x, int y, int width, int height, Id id,  GameHandler handeler) {
		super(x, y, width, height, id, handeler);
		
		try {
			image = ImageIO.read(getClass().getResource("/we00.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gh = handeler;
		this.en = handeler.getPlayer();
        this.gc = handeler.getGameCursor();
        this.angle = 0.0;
	}

	@Override
	public void equippedRender(Graphics g) {
		  
		if (gc != null && en != null) {
			g.setColor(Color.BLUE);
			//g.drawRect(getX(), getY(), 10, 64);
			((Graphics2D) g).rotate(angle, en.getX() + en.getWidth() / 2, en.getY() + en.getHeight() / 2);
			
			g.drawImage(image, this.centerX - image.getWidth() / 12, this.centerY - image.getHeight() / 6 + 5, image.getWidth() / 3, image.getHeight() / 3, null);
			
			((Graphics2D) g).rotate(-angle, en.getX() + en.getWidth() / 2, en.getY() + en.getHeight() / 2);
		}
		
	}
	
	@Override
	public void equippedUpdate() {
		if(en != null){
			this.centerX = en.getX() + en.getWidth() / 2;
			this.centerY = en.getY() + en.getHeight() / 2;
			if (gc != null) {
				angle = Math.atan2(centerY - gc.getY(), centerX - gc.getX())
						- Math.PI;
			}
		}else{
			this.en = gh.getPlayer();
	        this.gc = gh.getGameCursor();
		}
		
	}

	@Override
	public void effect() {
		
		Bullet b = new Bullet(this.centerX, this.centerY, 10, 10, true, Id.bullet, getHandler(), 10, this.angle, (image.getWidth() / 6)+30,0);
		getHandler().addEntity(b);
		
	}

	@Override
	public double getHealth() {
		
		return 100;
	}

	@Override
	public double getArmor() {
		
		return 100;
	}


	@Override
	public String getInfo() {
		
		return "NAN";
	}


	@Override
	public Type getType() {
		
		return Type.WEAPON;
	}



	@Override
	public String getPath() {
		
		return "/we00.png";
	}



}
