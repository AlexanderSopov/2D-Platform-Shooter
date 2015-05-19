/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.Game.model.gameobject.entity.player;

import java.awt.Point;

import edu.chl.Game.model.gameobject.entity.items.Item;
import edu.chl.Game.model.gameobject.entity.items.Item.State;
import edu.chl.Game.model.gameobject.entity.items.Item.Type;



/*
/**
 *
 * @author Rasmus
 */
public class PlayerOutfit {
    
	private Player player;
	private final Point head;
	private final Point hands;
	private final Point feet;
	private Point costom;
	
    PlayerOutfit(Player player){
        
        this.player = player;
        this.head = new Point(player.getCenterX(), player.getY());
        this.hands = new Point(player.getCenterX(), player.getCenterX());
        this.feet = new Point(player.getX(), player.getY() + player.getHeight());
    }

   
    public void eqipeItem(Item item) {
    	item.switchState(State.equipped);        
    }

    
    public void discardItem(Item item) {
    	item.switchState(State.inventory);
    }
    
    public void placeOnPlayer(Item item){
    	Type type = item.getType();
    	if(type == Type.HAT){
    		setPosition(item,this.head);
    	}else if(type == Type.WEAPON){
    		setPosition(item,this.hands);
    	}else if(type == Type.LIFE){
    		setPosition(item,this.costom);
    	}else if(type == Type.SHOES){
    		setPosition(item,this.feet);
    	}else if(type == Type.SPECIAL){
    		setPosition(item,this.costom);
    		
    	}
    }
    
    private void setPosition(Item item, Point p){
    	item.setX(p.x);
    	item.setY(p.y);
    }
    

}
