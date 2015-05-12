/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.Game.model.gameobject;

/**
 *
 * @author Rasmus
 */
public class OutfitDecorator  implements Outfit  {
    
    //All items should implement itemdecorator
    
    protected Outfit outfit;
    
    public OutfitDecorator(Outfit outfit){
        this.outfit = outfit;
    }
    
    @Override
    public void eqipeItem(Item item) {
        this.outfit.eqipeItem(item);
    }

    @Override
    public void discardItem(Item item) {
        this.outfit.discardItem(item);
    }

    @Override
    public double getTotalHealth() {
        return this.outfit.getTotalHealth();
    }

    @Override
    public double getTotalArmor() {
        return this.outfit.getTotalArmor();
    }
    
}
