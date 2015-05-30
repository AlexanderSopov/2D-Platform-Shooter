package edu.chl.Game.model.gameobject.item;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import edu.chl.Game.model.gameobject.entity.EntityState;
import edu.chl.Game.model.gameobject.entity.FacingDirection;
import edu.chl.Game.model.gameobject.entity.player.Player;
import edu.chl.Game.model.gameobject.entity.player.PlayerOutfit;
import edu.chl.Game.model.gameobject.item.Item.State;
import edu.chl.Game.storage.Reader;
import edu.chl.Game.storage.Storable;
import edu.chl.Game.storage.Writer;
import edu.chl.Game.view.Camera;
import edu.chl.Game.view.Frame;

public class Inventory implements Storable{
	
	 private HashMap<String,Item> itemMap;
	 private PlayerOutfit playerOutfit;
	 
	 
	public Inventory(PlayerOutfit playerOutfit ) {
		this.playerOutfit = playerOutfit;
		itemMap = new HashMap<String,Item>();
		load();
	}
	
	public Inventory() {
		this.playerOutfit = new PlayerOutfit();
		itemMap = new HashMap<String,Item>();
		load();
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
   		EntityState e = new EntityState(FacingDirection.FacingLeft);
   		e.setFacingDirection(null);
   	 }
   	 
   	
	}
	
	 public void addItem(Item item) {
		 if(item != null ){
			 if(!isItemExsisting(item.getNAME())){
				  item.switchState(State.inventory);
			      this.itemMap.put(item.getNAME(), item);
			 }
			 
		 }
		 
		 save();
	      
	 }
	 
	 public void addDelete(Item item) {
		 if(item != null){
			 this.itemMap.remove(item.getNAME());
		 }
		 save();
	 }
	
    public void equipeItem(Item item) {
    	
    	if(item != null){
    		this.playerOutfit.equipeItem(item);
    	}
    }
    
    public void equipeItem(String str) {
    	if(str != null){
	    	Item item =this.itemMap.get(str);
	    	
	        this.playerOutfit.equipeItem(item);
    	}
    }
    
    public void eqipeItem(char c) {
    	int counter = 0;
		Iterator itr = this.itemMap.entrySet().iterator();
		
   	 while(itr.hasNext()){
   		Map.Entry<String,Item> pair = (Map.Entry<String,Item>)itr.next();
   		if(counter +1 ==  Integer.parseInt(c + "")){
   			equipeItem(pair.getKey());
   		}
   		counter ++;
   		
   	 }
    }

    public void discardItem(Item item) {
       this.playerOutfit.discardItem(item);
       
    }
    
    public void discardItem(String str) {
    	
    	Item item = this.itemMap.get(str);
        this.playerOutfit.discardItem(item);
    }
    
    
    
    public Item getItem(String name){
    	return (Item) this.itemMap.get(name);
    }
    
    public HashMap<String,Item> getInventoryList(){
    	return this.itemMap;
    }
    
    
    
    public boolean isItemequipped(String str){
    	if(str != null ){
    		return this.playerOutfit.isItemequipped(getItem(str));
    	}
    	return false;
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

	@Override
	public void save() {
		
		Iterator itr = this.itemMap.entrySet().iterator();
		Writer.blankFile("Inventory.txt");
	   	 while(itr.hasNext()){
	   		Map.Entry<String, Item> pair = (Map.Entry<String, Item>)itr.next();
	   		
	   		Item item = pair.getValue();
			 Writer.writeToFile("Inventory.txt", item.getNAME());
	   	
	   	 }
		
	}

	@Override
	public void load() {
		
		for(String  str : Reader.readFile("Inventory.txt")){
			
			Item item =  ItemFactory.createItem(str);
			
			addItem(item);
			
		}
	}
	

}
