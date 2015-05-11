package edu.chl.Game.model.gameobject.entity.player;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.model.physics.ProjectileDetection;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

/**
 *
 * @author Rasmus
 */
public class Pistol extends Entity{
    
     private int centerX = getWidth() / 2;
     private int  centerY = getHeight() / 2;
     private double angle;
     private GameCursor gc;
     private Entity en;
     private ProjectileDetection pd;
     
     private LinkedList<Bullet> bulletList = new LinkedList<Bullet>();
    
    public Pistol(int x, int y, int width, int height, boolean solid, Id id, GameHandler handler, GameCursor gc, Entity en) {
        super(x, y, width, height, solid, id, handler);
        this.gc = gc;
        this.centerX = x + width/2;
        this.centerY = y + height/2;
        this.en = en;
        this.pd = new ProjectileDetection(this);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        ((Graphics2D)g).rotate(angle, centerX, centerY);
        
        g.fillRect(centerX, centerY-5, 50, 10); 
        ((Graphics2D)g).rotate(-angle, centerX, centerY);

        
        for (Bullet b : getBulletList()) {
			b.render(g);
		}
    }

    @Override
    public void update() {
        this.centerX = en.getX() + en.getWidth()/2 + en.getVelX();
        this.centerY = en.getY() + en.getHeight()/2 + en.getVelY();
        angle = Math.atan2(centerY - gc.getY(), centerX - gc.getX()) - Math.PI ;
        
        for (Bullet b : getBulletList()) {
			b.update();
        }
        
        pd.hitTarget();
    }
        
        public void shoot() {
		Bullet b = new Bullet( this.centerX, this.centerY , 10, 10, true, Id.bullet, getHandler(), gc.getX(), gc.getY(), 10, this.angle);
		addBullet(b);
	}
    
    	public void addBullet(Bullet b) {
		bulletList.add(b);
	}

	public void removeBullet(Bullet b) {
		bulletList.remove(b);
	}

	public LinkedList<Bullet> getBulletList() {
		return bulletList;
	}
}
