package edu.chl.Game.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.chl.Game.entity.Entity;
import edu.chl.Game.handler.GameHandler;

public abstract class Weapon extends Item{
	
	private Entity en;
 	private Boolean followEntity;
 	
 	private int bulletSpeed;
 	private int limit;
 	private int bulletSize;
 	private Color bulletColor;
 	private BufferedImage bulletImage;
 	
	
	Weapon(int x, int y, int width, int height, Id id, GameHandler handeler,String name,
			String info, BufferedImage buffImage) {
		super(x, y, width, height, id, handeler, name,info, buffImage);
		followEntity = false;
	}
	
	Weapon(Entity en, int width, int height, Id id, GameHandler handeler,String name,
			String info, BufferedImage buffImage) {
		super(1/*en.getX()*/, 1/*en.getY()*/, width, height, id, handeler,name, info, buffImage);
		this.en = this.en;
		followEntity = true;
	}
	
	@Override
	public void render(Graphics g){
		
		g.drawImage(getBuffImage(), getX(), getY(), null);
		
	}
	
	@Override
	public void update(){
		if(followEntity){
			//this.setX(en.getX());
			//this.setY(en.getY());
		}
	}

	@Override
	public abstract void effect();
	
	
	
	

}
