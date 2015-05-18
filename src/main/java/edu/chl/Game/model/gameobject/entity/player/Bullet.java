package edu.chl.Game.model.gameobject.entity.player;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.model.physics.ProjectileDetection;
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
    private ProjectileDetection pd;
    private int damageValue;
    

    public Bullet(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler, int speed, double angle, int offX, int offY) {
        super(x, y, width, height, solid, id, handler);
                
                this.angle = angle;
		this.speed = speed;
                this.centerX = getX() ;
                this.centerY = getY() ;
                this.motionX = getX() + offX;
                this.motionY = getY() + offY;
                rotatedX = (int)(Math.cos(angle) * (this.motionX - centerX) - Math.sin(angle) * (this.motionY-centerY) + centerX);
                rotatedY = (int)(Math.sin(angle) * (this.motionX - centerX) + Math.cos(angle) * (this.motionY-centerY) + centerY);
                this.damageValue = handler.getPlayer().getUnitValues().getAttackDamage();
                this.pd = new ProjectileDetection(this, handler);
               
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
        
        pd.hitTarget();
    }
    

	public int getSpeed() {
		return speed;
	}
        
}
