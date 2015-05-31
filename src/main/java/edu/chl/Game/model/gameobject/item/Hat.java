package edu.chl.Game.model.gameobject.item;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.model.gameobject.entity.FacingDirection;
import edu.chl.Game.view.Camera;

public class Hat extends Item{
	
	private BufferedImage image, filippedImage;
	private Entity en;
	private int scale;
	private int offX,offY;

	public Hat(int x, int y, int width, int height, Id id, GameHandler handeler) {
		super(x, y, width, height, id, handeler);
		
		image = this.getBufferedImage();
		filippedImage = this.getFlippedBufferedImage();

		this.en = handeler.getPlayer();
		this.scale = 5;
		this.offX = -100/this.scale;
		this.offY = -125/ this.scale;
	}
	
	public Hat(){
		super();

		image = this.getBufferedImage();
		filippedImage = this.getFlippedBufferedImage();
		
		this.scale = 5;
		this.offX = -100/this.scale;
		this.offY = -125/ this.scale;
	}

	@Override
	public String getInfo() {
		
		return "Soo Fab!";
	}

	@Override
	public String getPath() {
		
		return "/hat0.png";
	}

	@Override
	public void equippedRender(Graphics g) {
		
		if(en != null){
			if(en.getEntityState().getFacingDirection() == FacingDirection.FacingRight){
				
				g.drawImage(image, getX()+ offX, getY() + offY , image.getWidth()/this.scale, image.getHeight()/this.scale, null);
			
			}else{
				
				g.drawImage(filippedImage, getX() , getY() + offY, image.getWidth()/this.scale, image.getHeight()/this.scale, null);
			
			}
		}
	}

	@Override
	public void equippedUpdate() {
		
		if(en != null){
			setX(en.getX());
			setY(en.getY());
		}else{
			en  = getHandler().getPlayer();
		}

	}

	@Override
	public void effect() {
		
		
	}

	@Override
	public Type getType() {
		
		return Type.HAT;
		
	}

	@Override
	public double getHealth() {
		
		return 1000;
		
	}

	@Override
	public double getArmor() {
		
		return 1000;
		
	}
	


}
