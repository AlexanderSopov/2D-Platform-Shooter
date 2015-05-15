/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.Game.model.gameobject.entity.items;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.view.graphics.SpriteSheet;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Rasmus
 */
public abstract class Item extends CharacterDecorator{
    
    private SpriteSheet itemSheet;
    private BufferedImage bf;
    private Entity entity;
    private int x,y,width ,height,velX,velY;
    
    

    public Item(Character character) {
        super(character);
         bf = itemSheet.getSprite(0, 0, width, height);
         this.entity = (Entity) this.character;
         this.x = entity.getX() + (entity.getWidth()/2);
         this.y = entity.getY() + (entity.getHeight()/2);
         this.width = 0;
         this.height = 0;
         this.velX = this.entity.getVelX();
         this.velY = this.entity.getVelY();
         
        //itemSheet = new SpriteSheet("/path")
    }
    
    
    @Override
    public void render(Graphics g){
        super.render(g);
        this.iconRender(g);
    }
    
    public void iconRender(Graphics g){
        
        g.drawImage(bf, this.x , this.y, width, height, null);
    }

    @Override
    public void update() {
        super.update();
        this.updateItem();
    }
    
    public abstract void updateItem();
    

    @Override
    public double getHealth() {
        return super.getHealth() + this.character.getHealth();
    }

    @Override
    public double getArmor() {
        return super.getArmor() + this.character.getArmor();
    }
    
    	public int getVelX() {

		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int vely) {
		this.velY = vely;
	}
    
    
}
