/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.Game.model.gameobject.entity.items;

import edu.chl.Game.model.gameobject.entity.Entity;
import edu.chl.Game.view.graphics.SpriteSheet;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Rasmus
 */
public class Item extends CharacterDecorator{
    
    private SpriteSheet itemSheet;
    private BufferedImage bf;
    private Entity entity;
    private int x,y,width ,height;
    
    

    private Item(Character character) {
        super(character);
         bf = itemSheet.getSprite(0, 0, width, height);
         this.entity = (Entity) this.character;
         this.x = entity.getX() + (entity.getWidth()/2);
         this.y = entity.getY() + (entity.getHeight()/2);
         this.width = 0;
         this.height = 0;
         
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
        
    }
    
    @Override
    public void eqipeItem(edu.chl.Game.model.gameobject.Item item) {
        super.eqipeItem(item);
        //this.character.eqipeItem();
    }

    @Override
    public void discardItem(edu.chl.Game.model.gameobject.Item item) {
        super.discardItem(item);
        //this.character.discardItem(item);
    }

    @Override
    public double getHealth() {
        return this.character.getHealth() + this.character.getHealth();
    }

    @Override
    public double getArmor() {
        return super.getArmor() + this.character.getArmor();
    }
    
}
