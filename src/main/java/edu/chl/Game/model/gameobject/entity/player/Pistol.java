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
     private final GameCursor gc;
     private final Entity en;
     
     
    
    public Pistol(int x, int y, int width, int height, boolean solid, Id id, GameHandler handler, GameCursor gc, Entity en) {
        super(x, y, width, height, solid, id, handler);
        this.gc = gc;
        this.centerX = x + width/2;
        this.centerY = y + height/2;
        this.en = en;
        
    }

    @Override
    public void render(Graphics g) {
        
        g.setColor(Color.BLUE);
        ((Graphics2D)g).rotate(angle, centerX, centerY);
        
        g.fillRect(centerX-25, centerY-5, 50, 10); 
        ((Graphics2D)g).rotate(-angle, centerX, centerY);
        
    }

    @Override
    public void update() {
        this.centerX = en.getX() + en.getWidth()/2 + en.getVelX();
        this.centerY = en.getY() + en.getHeight()/2;
        angle = Math.atan2(centerY - gc.getY(), centerX - gc.getX()) - Math.PI ;
        
    }
        
    public void shoot() {
	Bullet b = new Bullet( this.centerX, this.centerY , 10, 10, true, Id.bullet, getHandler(), 10, this.angle);
	getHandler().addEntity(b);
    }
    
}
