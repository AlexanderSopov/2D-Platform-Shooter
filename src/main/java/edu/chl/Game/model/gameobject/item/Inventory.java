package edu.chl.Game.model.gameobject.item;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import edu.chl.Game.model.gameobject.entity.player.Player;
import edu.chl.Game.model.gameobject.entity.player.PlayerOutfit;
import edu.chl.Game.model.gameobject.item.Item.State;
import edu.chl.Game.view.Camera;
import edu.chl.Game.view.Frame;

public class Inventory {
	
	 private HashMap<String,Item> itemMap;
	 private PlayerOutfit playerOutfit;
	 
	
	public Inventory(PlayerOutfit playerOutfit ) {
		this.playerOutfit = playerOutfit;
		itemMap = new HashMap<String,Item>();
	}
	
	public void render(Graphics g,Player player){
		g.setColor(Color.red);
		int counter = 0;
		Iterator itr = this.itemMap.entrySet().iterator();
		Camera cam = player.getHandler().getCamera();
   	 while(itr.hasNext()){
   		Map.Entry pair = (Map.Entry)itr.next();
   		
   		g.drawString(counter +1 + ":" + pair.getKey(), player.getX() + (counter * 64) , player.getY()- Frame.HEIGHT/2);
   		counter ++;
   		
   		
   	 }
   	 
   	
	}
	
	 public void addItem(Item item) {
		 
		 item.switchState(State.inventory);
	      this.itemMap.put(item.getNAME(), item);
	      item.remove();
	      
	 }
	 
	 public void addDelete(Item item) {
		 this.itemMap.remove(item.getNAME());
	 }
	
    public void eqipeItem(Item item) {
        this.playerOutfit.eqipeItem(item);
    }
    
    public void eqipeItem(String str) {
    	Item item =this.itemMap.get(str);
        this.playerOutfit.eqipeItem(item);
    }
    
    public void eqipeItem(char c) {
    	int counter = 0;
		Iterator itr = this.itemMap.entrySet().iterator();
		
   	 while(itr.hasNext()){
   		Map.Entry<String,Item> pair = (Map.Entry<String,Item>)itr.next();
   		if(counter +1 ==  Integer.parseInt(c + "")){
   			eqipeItem(pair.getKey());
   		}
   		counter ++;
   		
   		
   	 }
    }

    public void discardItem(Item item) {
       this.playerOutfit.discardItem(item);
    }
    
    public void discardItem(String str) {
    	Item item =this.itemMap.get(str);
        this.playerOutfit.discardItem(item);
    }
    
    
    
    
    public Item getItem(String name){
    	return (Item) this.itemMap.get(name);
    }
    
    public HashMap<String,Item> getInventoryList(){
    	return this.itemMap;
    }
    
    public void setInventoryList(HashMap<String,Item> map){
    	this.itemMap = map;
    }
    
    public void systemWriteAll(HashMap hm){
    	Iterator itr = hm.entrySet().iterator();
    	 while(itr.hasNext()){
    		Map.Entry pair = (Map.Entry)itr.next();
    		
    		System.out.println("Item in inventory:" + pair.getKey());
    		itr.remove();
    	 }
    	
    }
    
    public boolean isItemequipped(String str){
    	
    	return !this.playerOutfit.isItemequipped(getItem(str));
    	
    }
    
    public boolean isItemExsisting(String str){
    	Iterator itr = this.itemMap.entrySet().iterator();
    	 while(itr.hasNext()){
    		Map.Entry pair = (Map.Entry)itr.next();
    		
    		if(pair.getKey().equals( str )){
    			return true;
    		}
    	 }
    	 return false;
    }
}
