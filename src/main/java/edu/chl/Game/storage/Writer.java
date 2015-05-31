package edu.chl.Game.storage;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
/**
 * 
 * Writer write to files
 * 
 * @author Rasmus
 *
 */
public class Writer {
	
	//Default path
	private final static String defaultPath = "Saves/";
	
	
	/**
	 * 
	 * @param fileName name of the testfile
	 * @param message massage u want to be written
	 */
	public static void writeToFile(String fileName, String message){
		FileWriter fileWriter;
		try {
			
			// Open buffredwriter
			fileWriter = new FileWriter(defaultPath+fileName,true);
			BufferedWriter buffWriter = new BufferedWriter(fileWriter);
			
			//write the message in the textfile
	        buffWriter.write(message);
	        buffWriter.newLine();
	        buffWriter.close();
        
		} catch(FileNotFoundException e){
			System.out.println("Not able to find your file : "+ fileName);
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// restore the textfile to blank
	public static void blankFile(String fileName){
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(defaultPath+fileName);
		
			BufferedWriter buffWriter = new BufferedWriter(fileWriter);
	        
	        buffWriter.close();
        
		} catch(FileNotFoundException e){
			System.out.println("Not able to find your file : "+ fileName);
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
