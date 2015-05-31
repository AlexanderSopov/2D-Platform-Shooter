package edu.chl.Game.storage;


/**
 * 
 * Storable objects that need to save and load object
 * 
 * @author Rasmus
 *
 */
public interface Storable {
	
	/**
	 * Save the content in to a text file
	 */
	public void save();
	
	/**
	 * Load content and handle it to the class
	 */
	public void load();
	
	
}
