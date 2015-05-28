package edu.chl.Game.Storage;

import java.util.LinkedList;
import java.util.List;

import edu.chl.Game.model.gameobject.item.Hat;
import edu.chl.Game.model.gameobject.item.Item;
import edu.chl.Game.model.gameobject.item.W1;

public class InventoryStorage implements Storage{
	
	
	
	String path = "Inventory";

	@Override
	public void save(List list) {
		for(Object o : list){
			if(o instanceof Item){
				Item item = (Item)o;
			 Writer.writeToFile(path, item.getNAME()+";");
			}
		}
		
		
	}

	@Override
	public LinkedList load() {
		
		LinkedList<Item> itemList = new LinkedList<Item>();
		
		for(String  str : Reader.readFile(path)){
			
			
			
		}
		
		return null;
	}

	@Override
	public Object getObject(String str) {
		return str;
		/*
		switch(str){
		case "W1":
			return new W1();
		case "Hat":
			return new Hat();
		}
		
		return null;*/
	}
	
	
}
