package edu.chl.Game.model.gameobject.item;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Iterator;
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

/**
 * 
 * Inventory is containing a Hashmap of items the player 
 * picked up. It can also equip and discard the Items in the PlayerOutfit.
 * 
 * @author Rasmus
 *
 */
public class Inventory implements Storable {
	
	//itemMap will contain items
	private HashMap<String, Item> itemMap;
	
	private PlayerOutfit playerOutfit;
	
	
	/**
	 * Class constructor set PlayerOutfit and intialize itemMap.
	 * In the end load saved inventory.
	 * 
	 * @param playerOutfit a suite for rendering 
	 * items on an character in the game
	 */
	public Inventory(PlayerOutfit playerOutfit) {
		this.playerOutfit = playerOutfit;
		itemMap = new HashMap<String, Item>();
		load();
	}

	/**
	 * Class constructor without parameter to lower amount of dependencies.
	 * This constructor is not used yet in the game.
	 */
	public Inventory() {
		this.playerOutfit = new PlayerOutfit();
		itemMap = new HashMap<String, Item>();
		load();
	}
	
	
	
	// --- Add and Delete ---
	
	
	/**
	 * 
	 * Add Item to the itemMap if not already exists
	 * 
	 * @param item
	 */
	public void addItem(Item item) {
		if (item != null) {
			
			if (!isItemExsisting(item.getNAME())) {
				
				item.switchState(State.inventory);
				
				this.itemMap.put(item.getNAME(), item);
				
			}
			
			//Save to Inventory textfile
			save();
		}

	}
	
	
	
	/**
	 * 
	 * Delete Item from the itemMap and save to textfile  
	 * 
	 * @param item
	 */
	public void addDelete(Item item) {
		
		if (item != null) {
			
			this.itemMap.remove(item.getNAME());
			save();
			
		}
		
	}
	
	
	
	
	// --- Equip methods ---
	
	
	/**
	 * Equip a chosen item
	 * 
	 * @param item object to pickup and wear in playerOutput
	 */
	public void equipItem(Item item) {

		if (item != null) {
			
			this.playerOutfit.equipeItem(item);
			
		}
	}
	
	
	/**
	 * 
	 * Equip by item name
	 * 
	 * @param str the name of the Item
	 */
	public void equipItem(String str) {
			
			Item item = this.itemMap.get(str);

			if(item == null){
				
				this.playerOutfit.equipeItem(item);
				
			}
				
	}

	
	/**
	 * 
	 * Equip Item by a numbered char input.
	 * 
	 * In normal case it should be an int.
	 * 
	 * @param c
	 */
	public void equipItem(char c) {
		int counter = 1;
		Iterator itr = this.itemMap.entrySet().iterator();

		while (itr.hasNext()) {
			Map.Entry<String, Item> pair = (Map.Entry<String, Item>) itr.next();
			if (counter == Integer.parseInt(c + "")) {
				equipItem(pair.getKey());
			}
			counter++;

		}
	}
	
	// --- Discard methods ---
	
	
	/**
	 * Removing the item from a playerOutfit
	 * 
	 * @param item
	 */
	public void discardItem(Item item) {
		this.playerOutfit.discardItem(item);

	}

	/**
	 * Removing the item from a playerOutfit with a string input
	 * 
	 * @param str
	 */
	public void discardItem(String str) {

		Item item = this.itemMap.get(str);
		this.playerOutfit.discardItem(item);
	}
	
	
	// --- Check methods ---
	
	
	/**
	 * @param str name of the item
	 * @return true if item is equipped else return false
	 */
	public boolean isItemequipped(String str) {
		if (str != null) {
			return this.playerOutfit.isItemequipped(getItem(str));
		}
		return false;
	}
	
	
	/**
	 * 
	 * @param str name of item
	 * @return true if a certain item exist in the itemMap 
	 */
	public boolean isItemExsisting(String str) {
		
		Iterator itr = this.itemMap.entrySet().iterator();
		
		//running through the itemMap
		while (itr.hasNext()) {
			
			Map.Entry pair = (Map.Entry) itr.next();
			
			// is item in the list matching the name
			if (pair.getKey().equals(str)) {
				return true;
			}
		}
		return false;
	}

	
	// --- Save And Load ---
	
	@Override
	public void save() {

		Iterator itr = this.itemMap.entrySet().iterator();
		
		//restore the textfile
		Writer.blankFile("Inventory.txt");
		
		//running through the itemMap
		while (itr.hasNext()) {
			
			Map.Entry<String, Item> pair = (Map.Entry<String, Item>) itr.next();
			Item item = pair.getValue();
			
			//Write the name of the item to the textfile
			Writer.writeToFile("Inventory.txt", item.getNAME());

		}

	}

	@Override
	public void load() {
		
		//running through the list from the textfile
		for (String str : Reader.readFile("Inventory.txt")) {
			
			//get Item with help from the itemFacroty
			Item item = ItemFactory.createItem(str);

			addItem(item);

		}
	}
	
	// --- Setters And Getters ---
	
	public Item getItem(String name) {
		return (Item) this.itemMap.get(name);
	}

	public HashMap<String, Item> getInventoryList() {
		return this.itemMap;
	}


}
