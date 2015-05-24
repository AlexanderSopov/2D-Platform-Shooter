package edu.chl.Game.model.gameobject.entity.items;

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
	
	private BufferedImage image;
	private Entity en;
	private Camera cam;
	private int scale;
	private int offX,offY;

	public Hat(int x, int y, int width, int height, Id id, GameHandler handeler) {
		super(x, y, width, height, id, handeler);
		try {
			image = ImageIO.read(getClass().getResource("/hat0.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.en = handeler.getPlayer();
		this.cam = handeler.getCamera();
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
		
		
		g.setColor(Color.BLUE);
		
		if(en.getEntityState().getFacingDirection() == FacingDirection.FacingRight){
			//g.fillRect(getX() + offX, getY() + offY, image.getWidth()/this.scale, image.getHeight()/this.scale);
			g.drawImage(image, getX()+ offX, getY() + offY , image.getWidth()/this.scale, image.getHeight()/this.scale, null);
		}else{
			//g.fillRect(getX() +2, getY() + offY, image.getWidth()/this.scale, image.getHeight()/this.scale);
			g.drawImage(getFlippedImage(image), getX() , getY() + offY, image.getWidth()/this.scale, image.getHeight()/this.scale, null);
		}
	}

	@Override
	public void equippedUpdate() {
		// TODO Auto-generated method stub
		
		setX(en.getX());
		setY(en.getY());
		//setX(getX() + cam.getX() );
		//setY(getY() + cam.getY());
	}

	@Override
	public void effect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return Type.HAT;
	}

	@Override
	public double getHealth() {
		// TODO Auto-generated method stub
		return 1000;
	}

	@Override
	public double getArmor() {
		// TODO Auto-generated method stub
		return 1000;
	}
	
	   public static BufferedImage getFlippedImage(BufferedImage bi) {
	        BufferedImage flipped = new BufferedImage(
	                bi.getWidth(),
	                bi.getHeight(),
	                bi.getType());
	        AffineTransform tran = AffineTransform.getTranslateInstance(bi.getWidth(), 0);
	        AffineTransform flip = AffineTransform.getScaleInstance(-1d, 1d);
	        tran.concatenate(flip);

	        Graphics2D g = flipped.createGraphics();
	        g.setTransform(tran);
	        g.drawImage(bi, 0, 0, null);
	        g.dispose();

	        return flipped;
	    }

}
