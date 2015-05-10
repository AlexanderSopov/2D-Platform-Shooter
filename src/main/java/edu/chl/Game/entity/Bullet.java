package edu.chl.Game.entity;

import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.Id;
import edu.chl.Game.view.Frame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author Rasmus
 */
public class Bullet extends Entity{
    
    private int targetPosX,targetPosY, speed;
    private int centerX, centerY;
    private double angle;
    private int rotatedX,rotatedY;
    private int motionX, motionY;
    

    public Bullet(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler,int targetPosX, int targetPosY, int speed, double angle) {
        super(x, y, width, height, solid, id, handler);
                
                this.angle = angle;
                
		this.setTargetPosX(targetPosX);
		this.setTargetPosY(targetPosY);
                
		this.speed = speed;
                this.centerX = getX() ;
                this.centerY = getY() ;
                this.motionX = getX()+50;
                this.motionY = getY();
               
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
	g.fillOval(rotatedX -(getWidth()/2),rotatedY-(getHeight()/2), getWidth(), getHeight());
        

    }

    @Override
    public void update() {
        this.motionX = this.motionX + this.speed;
       rotatedX = (int)(Math.cos(angle) * (this.motionX - centerX) - Math.sin(angle) * (this.motionY-centerY) + centerX);
       rotatedY = (int)(Math.sin(angle) * (this.motionX - centerX) + Math.cos(angle) * (this.motionY-centerY) + centerY);
        
	setX((int) rotatedX);
        setY((int)rotatedY);
    }
    
    	public int getTargetPosX() {
		return targetPosX;
	}

	private void setTargetPosX(int targetPosX) {
		this.targetPosX = targetPosX;
	}

	public int getTargetPosY() {
		return targetPosY;
	}

	private void setTargetPosY(int targetPosY) {
		this.targetPosY = targetPosY;
	}

	public int getSpeed() {
		return speed;
	}
        
}
