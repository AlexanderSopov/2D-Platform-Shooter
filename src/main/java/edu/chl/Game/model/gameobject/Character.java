/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.Game.model.gameobject;

import edu.chl.Game.model.gameobject.entity.Entity;
import java.awt.Graphics;

/**
 *
 * @author Rasmus
 */
public interface Character{
    
   public void eqipeItem(Item item);

   public void discardItem(Item item);
    
   public double getTotalHealth();
   
   public double getTotalArmor();
    
}
