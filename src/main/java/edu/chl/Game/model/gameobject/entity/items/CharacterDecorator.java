

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.Game.model.gameobject.entity.items;


import edu.chl.Game.model.gameobject.entity.Entity;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Rasmus
 */
public class CharacterDecorator implements Character, Observer{
    
    //All items should implement itemdecorator
    
    

 
    protected Character character;
    
    /**
     *
     * @param character
     */
 
    
    public CharacterDecorator(Character character){
        System.out.println("CharacterDecorator Created");
        //Write try and catch
        this.character = character;
        
       
        
    }
    
  
    
 
    
       @Override
    public void render(Graphics g) {
             System.out.println("CharacterDecorator redred");
             	System.out.println("Character: "+ this.character);
             	if(this.character != null){
             		this.character.render(g);
             
             	}
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
   


}
