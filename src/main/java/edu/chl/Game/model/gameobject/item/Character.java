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
   
	
   public void render(Graphics g);
   
   public void update();
    
   public double getHealth();
   
   public double getArmor();
   
   /**
    * effect will activate the special ability which is defined in each class
    */
   public void effect();
    
}
