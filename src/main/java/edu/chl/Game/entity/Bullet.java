package edu.chl.Game.entity;

import edu.chl.Game.handler.GameHandler;
import edu.chl.Game.object.Id;
import edu.chl.Game.view.Frame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Rasmus
 */
public class Bullet extends Entity{
    
    private int targetPosX,targetPosY, speed;
    private float xVelocity, yVelocity;
    private int centerX, centerY;
    double angle;
    

    public Bullet(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler,int targetPosX, int targetPosY, int speed, double angle) {
        super(x, y, width, height, solid, id, handler);
        
                
                this.angle = angle;
                
		this.setTargetPosX(targetPosX);
                
		this.setTargetPosY(targetPosY);
		this.speed = speed;
                
                this.centerX = getX() ;
                this.centerY = getY() ;
               
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        ((Graphics2D)g).rotate(angle, centerX, centerY);
        g.fillOval(getX()-(getWidth()/2)+50,getY()-(getHeight()/2), getWidth(), getHeight());
        ((Graphics2D)g).rotate(-angle, centerX, centerY);
    }

    @Override
    public void update() {
        
        this.xVelocity = speed;
	setX(getX() + (int)xVelocity);
	
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
