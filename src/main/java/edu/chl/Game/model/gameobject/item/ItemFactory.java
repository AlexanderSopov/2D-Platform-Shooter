package edu.chl.Game.model.gameobject.item;

/**
 * 
 * ItemFactory creates Item with a String parameter.
 * 
 * This solution makes it possible to
 * 
 * @author Rasmus
 *
 */
public class ItemFactory {
	
	public static Item createItem(String itemName){
		
		if(itemName == null){
			
			return null;
			
		}else if(itemName.equalsIgnoreCase("W1")){
			
			return new W1();
			
		}else if(itemName.equalsIgnoreCase("Hat")){
			
			return new Hat();
			
		}
		
		return null;
		
	}
	
}
