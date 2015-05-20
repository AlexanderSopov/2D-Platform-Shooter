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
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Rasmus
 */
public class P2 extends CharacterDecorator{
    
     private double angle;
     private final GameCursor gc;
     private Entity en;
     private GameHandler handler;
   
    
    public P2(Character character,Entity en,  GameCursor cursor) {
        super(character);
        this.en = en;
        this.gc = cursor;
        System.out.println("P2 Created");
        
    }
    


     @Override
    public void render(Graphics g) {
        
        super.render(g);
        g.setColor(Color.RED);
        ((Graphics2D)g).rotate(angle, en.getX(), en.getY());
        
        g.fillRect(en.getX(), en.getY()-5, 50, 10); 
        ((Graphics2D)g).rotate(-angle, en.getX(), en.getY());
        System.out.println("P2 Render");
    }
    
    
    @Override
    public void update(){
        
        super.update();
        //setX(en.getX()+ en.getVelX());
        //setY(en.getY() + en.getVelY());
        angle = Math.atan2(en.getY() - gc.getY(), en.getX() - gc.getX()) - Math.PI ;
        //System.out.println("P2 Update");
    }

        
    public void shoot() {
		Bullet b = new Bullet( en.getX(), en.getY(), 10, 10, true, Id.bullet, gc.getHandler(), 10, this.angle, 50,0);
		gc.getHandler().addEntity(b);
    }



  
    
}

