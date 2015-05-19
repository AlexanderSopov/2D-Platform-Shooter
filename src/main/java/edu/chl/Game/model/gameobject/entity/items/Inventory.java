package edu.chl.Game.model.gameobject.entity.items;

import edu.chl.Game.model.gameobject.entity.player.PlayerOutfit;

public class Inventory {
	
	 private ItemMap itemMap;
	 private PlayerOutfit playerOutfit;
	
	public Inventory(PlayerOutfit playerOutfit ) {
		this.playerOutfit = playerOutfit;
		itemMap = new ItemMap();
	}
	
	 public void addItem(Item item) {
	      this.itemMap.put(item.getNAME(), item);
	 }
	 
	 public void addDelete(Item item) {
		 this.itemMap.remove(item.getNAME());
	 }
	
    public void eqipeItem(Item item) {
        this.playerOutfit.eqipeItem(item);
    }

    public void discardItem(Item item) {
       this.playerOutfit.discardItem(item);
    }
    
    public Item getItem(String name){
    	return (Item) this.itemMap.get(name);
    }
}
