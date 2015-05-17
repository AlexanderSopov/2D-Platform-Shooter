/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.Game.model.gameobject;

import edu.chl.Game.model.gameobject.entity.player.Player;

/**
 *
 * @author Rasmus
 */
public class PlayerOutfit implements Character{
    
    ItemMap itemMap;
    Player player;
    
    PlayerOutfit(Player player){
        this.itemMap = new ItemMap();
        this.player = player;
    }

   
    @Override
    public void eqipeItem(Item item) {
        
    }

    
    @Override
    public void discardItem(Item item) {
       
    }

    
    
   public double getTotalHealth() {
    	return 0.0;
    }

    
    @Override
    public double getTotalArmor() {
        return 0.0;
    }
    
}
