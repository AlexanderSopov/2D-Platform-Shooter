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
    private float xVelocity, yVelocity;
    private int centerX, centerY;
    private double angle;
    private AffineTransform at;
    

    public Bullet(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler,int targetPosX, int targetPosY, int speed, double angle) {
        super(x, y, width, height, solid, id, handler);
        
                at = new AffineTransform();
                
                this.angle = angle;
                
		this.setTargetPosX(targetPosX);
                
		this.setTargetPosY(targetPosY);
		this.speed = speed;
                this.speed = 1;
                this.centerX = getX() ;
                this.centerY = getY() ;
               
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
       // at = at.getRotateInstance(angle, centerX, centerY);
        
        ((Graphics2D)g).rotate(angle, centerX, centerY);
	g.fillOval(getX()-(getWidth()/2)+50,this.centerY-(getHeight()/2), getWidth(), getHeight());
        g.setColor(Color.red);
        g.drawString(this.getY()+"", this.getX(), this.centerY-(getHeight()/2));
        
        ((Graphics2D)g).rotate(-angle, centerX, centerY);

	
    }

    @Override
    public void update() {
        
       
        
        this.xVelocity = speed;
	setX(getX() + (int)xVelocity);
	//setX((int)((getX() - this.centerX) * Math.cos(this.angle) - (getY() - this.centerY) * Math.sin(this.angle) + this.centerX));
        setY((int)((getY() - this.centerY) * Math.cos(this.angle) + (getX() - this.centerX) * Math.sin(this.angle) + this.centerY));
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
