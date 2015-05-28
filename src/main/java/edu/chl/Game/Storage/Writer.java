package edu.chl.Game.Storage;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Writer {

	
	
	public static void writeToFile(String fileName, String message){
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(fileName,true);
		
			BufferedWriter buffWriter = new BufferedWriter(fileWriter);
			
			
			buffWriter.newLine();
	        buffWriter.write(message);
	        
	        
	        buffWriter.close();
        
		} catch(FileNotFoundException e){
			System.out.println("Not able to find your file : "+ fileName);
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
