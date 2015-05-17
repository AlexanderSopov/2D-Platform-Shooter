

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.Game.model.gameobject.entity.items;

import edu.chl.Game.model.gameobject.GameObject;
import edu.chl.Game.model.gameobject.Item;
import edu.chl.Game.model.gameobject.entity.Entity;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Rasmus
 */
public class CharacterDecorator  implements Character, Observer {
    
    //All items should implement itemdecorator
    
    private Entity entity;

    public int getX() {
        return this.entity.getX() - (this.entity.getWidth()/2);
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.entity.getY() - (this.entity.getHeight()/2);
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public int getVelX() {
        return this.entity.getVelX();
    }

    public int getVelY() {
        return this.entity.getVelY();
    }

    private int x,y,width ,height,velX,velY;
    protected Character character;
    
    /**
     *
     * @param character
     */
 
    
    public CharacterDecorator(Character character){
        System.out.println("CharacterDecorator Created");
        //Write try and catch
        this.character = character;
        
        this.entity = (Entity) this.character;
        
    }
    
     @Override
    public void update(Observable o, Object arg) {
        try {
            
            Graphics g = (Graphics) arg;
            render(g);
            update();
            
            } catch (IllegalArgumentException e) {
                    System.out.println("oops!");
            }
    }
    
 
    
       @Override
    public void render(Graphics g) {
             System.out.println("CharacterDecorator redred");
             
                this.character.render(g);
             
        
    }

    @Override
    public void update() {
        System.out.println("CharacterDecorator updated");
        this.character.update();
    }
    

    @Override
    public double getHealth() {
        return this.character.getHealth();
    }

    @Override
    public double getArmor() {
        return this.character.getArmor();
    }

   


}
