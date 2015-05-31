/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.Game.model.gameobject.entity.player;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import edu.chl.Game.model.gameobject.item.Character;
import edu.chl.Game.model.gameobject.item.Item;
import edu.chl.Game.model.gameobject.item.Item.State;
import edu.chl.Game.model.gameobject.item.Item.Type;



/*
/**
 *
 * @author Rasmus
 */
public class PlayerOutfit implements Character{
    
	private Player player;
	private LinkedList<Item> equippedItems;
	
    public PlayerOutfit(Player player){
        
        this.player = player;
        this.equippedItems = new LinkedList<Item>();
        
    }
    
    public PlayerOutfit(){
        
        this.equippedItems = new LinkedList<Item>();
        
    }

   
    public synchronized void equipeItem(Item item) {
    	if(!isItemequipped(item)){
    		if(player != null){
    			item.setHandler(player.getHandler());
    		}
	    	this.equippedItems.add(item);
	    	item.switchState(State.equipped);
    	}else{
    		this.discardItem(item);
    	}
    }

    
    public synchronized void discardItem(Item item) {
    	this.equippedItems.remove(item);
    	item.switchState(State.inventory);
    }
    

	@Override
	public synchronized void render(Graphics g) {
		
		Iterator<Item> itr = this.equippedItems.iterator();
    	while(itr.hasNext()){
    		Item item = (Item) itr.next();
    		item.render(g);
    	
    	}
    	
    	
	}


	@Override
	public synchronized void update() {
		
		Iterator<Item> itr = this.equippedItems.iterator();
    	while(itr.hasNext()){
    		Item item = (Item) itr.next();
    		item.update();
    		
    	}
		
	}
	
	@Override
    public synchronized void effect(){
    	Iterator<Item> itr = this.equippedItems.iterator();
    	while(itr.hasNext()){
    		Item item = (Item) itr.next();
    		item.effect();
    		
    	}
    }


     
	 public boolean isItemequipped(Item item){
		 Iterator<Item> itr = this.equippedItems.iterator();
    	 while(itr.hasNext()){
    		Item item2 = (Item)itr.next();
    		
    		if(item.getNAME().equals(item2.getNAME())){
    			return true;
    		}
    	 }
    	 return false;
	 }


	public LinkedList<Item> getEquippedItems() {
		return equippedItems;
	}


	public void setEquippedItems(LinkedList<Item> equippedItems) {
		this.equippedItems = equippedItems;
	}
	
    public void placeOnPlayer(Item item){
    	 
    }
	
	@Override
	public double getHealth() {
		double d = player.getHealth();
		for(Item it : this.equippedItems){
    		d += it.getHealth();
    	}
		return d;
	}


	@Override
	public double getArmor() {
		double d = player.getArmor();
		for(Item it : this.equippedItems){
    		d += it.getArmor();
    	}
		return d;
	}

}
