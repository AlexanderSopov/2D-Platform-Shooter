/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.Game.model.gameobject.item;


import java.awt.Graphics;

/**
 * Character is a interface for the items 
 * that is wearable and it's character.
 *
 * @author Rasmus
 */
public interface Character{
   
	/**
	 * rendering on the screen
	 * @param g
	 */
   public void render(Graphics g);
   
   /**
    * Update the object
    */
   public void update();
    
   /** 
    * @return amount of health
    */
   public double getHealth();
   
   
   /**
    * @return the armor value
    */
   public double getArmor();
   
   /**
    * effect will activate the special ability which is defined in each class
    */
   public void effect();
    
}
