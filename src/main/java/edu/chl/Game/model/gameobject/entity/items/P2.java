package edu.chl.Game.model.gameobject.entity.items;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.model.gameobject.entity.player.Bullet;
import edu.chl.Game.model.gameobject.entity.player.GameCursor;
import edu.chl.Game.model.physics.ProjectileDetection;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

/**
 *
 * @author Rasmus
 */
public class P2 extends Item{
    
     private int centerX;
     private int  centerY;
     private double angle;
     private final GameCursor gc;
     
     
     
   
    
    public P2(Character character, GameCursor gc) {
        super(character);
        this.gc = gc;
    }

    @Override
    public void render(Graphics g) {
        
        g.setColor(Color.BLUE);
        ((Graphics2D)g).rotate(angle, centerX, centerY);
        
        g.fillRect(centerX, centerY-5, 50, 10); 
        ((Graphics2D)g).rotate(-angle, centerX, centerY);
        
    }


        
    public void shoot() {
	Bullet b = new Bullet( this.centerX, this.centerY , 10, 10, true, Id.bullet, getHandler(), 10, this.angle);
	getHandler().addEntity(b);
    }

    @Override
    public void updateItem() {
        this.centerX = en.getX() + en.getWidth()/2 + en.getVelX();
        this.centerY = en.getY() + en.getHeight()/2 + en.getVelY();
        angle = Math.atan2(centerY - gc.getY(), centerX - gc.getX()) - Math.PI ;
    }
    
}

