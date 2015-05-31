package edu.chl.Game.storage;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Reader read from files
 * 
 * @author Rasmus
 *
 */
public class Reader{
	
	//Default path
	private final static String path = "Saves/";
	
	
	/**
	 * 
	 * @param fileName the name of the file
	 * @return list of every row in the text dokument
	 */
	public static LinkedList<String> readFile(String fileName){
		String output;
		LinkedList<String> list = new LinkedList<String>();
		try{
			
			//Open the textfile with a buffered reader
			FileReader fileReader = new FileReader(path+fileName);
			BufferedReader buffReader = new BufferedReader(fileReader);
			
			// go through every line in textfile
			while((output = buffReader.readLine()) != null){
				
				// add line to the list
				list.add(output);
				
			}
			
			//close the reader
			buffReader.close();
		
		}catch(FileNotFoundException e){
			
			System.out.println("Not able to find your file : "+ fileName);
			
		}catch(IOException e){
			
			System.out.println("Not able to read file: "+ fileName);
			
		}
		return list;
	}
	
}
