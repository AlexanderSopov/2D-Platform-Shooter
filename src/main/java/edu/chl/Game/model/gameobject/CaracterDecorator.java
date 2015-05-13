

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.Game.model.gameobject;

import java.awt.Graphics;

/**
 *
 * @author Rasmus
 */
public class CaracterDecorator  implements Character  {
    
    //All items should implement itemdecorator
    
    protected Character character;
    
    public CaracterDecorator(Character character){
        this.character = character;
    }
    
       @Override
    public void render(Graphics g) {
        this.character.render(g);
    }

    @Override
    public void update() {
        this.character.update();
    }
    
    @Override
    public void eqipeItem(Item item) {
        this.character.eqipeItem(item);
    }

    @Override
    public void discardItem(Item item) {
        this.character.discardItem(item);
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
