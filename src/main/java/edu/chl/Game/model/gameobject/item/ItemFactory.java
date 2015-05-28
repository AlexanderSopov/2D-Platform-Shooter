package edu.chl.Game.model.gameobject.item;

public class ItemFactory {
	
	public static Item createItem(String itemName){
		
		if(itemName == null){
			
			return null;
			
		}else if(itemName.equalsIgnoreCase("W1")){
			
			return new W1();
			
		}else if(itemName.equalsIgnoreCase("Hat")){
			
			return new Hat();
			
		}else if(itemName.equalsIgnoreCase("Nothing")){
			
			return new Nothing();
			
		}
		
		return null;
		
	}
	
}
