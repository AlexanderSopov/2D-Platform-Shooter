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
public class P2 extends CharacterDecorator{
    
     private double angle;
     private final GameCursor gc;
  
   
    
    public P2(Character character, GameCursor gc) {
        super(character);
       
        this.gc = gc;
         System.out.println("P2 Created");
    }
    


     @Override
    public void render(Graphics g) {
        System.out.println("P2 Render");
        super.render(g);
        g.setColor(Color.RED);
        ((Graphics2D)g).rotate(angle, getX(), getY());
        
        g.fillRect(getX(), getY()-5, 50, 10); 
        ((Graphics2D)g).rotate(-angle, getX(), getY());
    }
    
    
    @Override
    public void update(){
        System.out.println("P2 Update");
        super.update();
        setX(getX()+ getVelX());
        setY(getY() + getVelY());
        angle = Math.atan2(getY() - gc.getY(), getX() - gc.getX()) - Math.PI ;
    }

        
    public void shoot() {
	Bullet b = new Bullet( getX(), getY(), 10, 10, true, Id.bullet, gc.getHandler(), 10, this.angle);
	gc.getHandler().addEntity(b);
    }



  
    
}

