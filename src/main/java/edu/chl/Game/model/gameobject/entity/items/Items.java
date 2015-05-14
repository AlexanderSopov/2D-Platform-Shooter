package edu.chl.Game.model.gameobject.entity.items;

import java.util.LinkedList;

import edu.chl.Game.controller.GameHandler;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.Entity;


/**
 * NOTE THIS CLASS IS NOT COMPLETE.
 * 
 */
public abstract class Items extends Entity {
	
	private boolean animateItem = true;
	private LinkedList<Items> itemsList = new LinkedList<Items>();
	
	public Items(int x, int y, int width, int height, boolean solid, Id id,
			GameHandler handler) {
		super(x, y, width, height, solid, id, handler);
		
	}
	
	public void addItems(Items i) {
		itemsList.add(i);
	}
	public void removeItems(Items i) {
		itemsList.remove(i);
	}
	public void clearItems() {
		itemsList.clear();
	}
	public LinkedList<Items> getItemsList(Items i) {
		return this.itemsList;
	}
	public void setAnimateItem(boolean a) {
		animateItem = a;
	}
	public boolean getAnimateItem() {
		return this.animateItem;
	}
	//public abstract BufferedImage itemsGetBufferedImage();
}
