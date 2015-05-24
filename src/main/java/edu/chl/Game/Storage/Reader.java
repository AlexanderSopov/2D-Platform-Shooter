package edu.chl.Game.Storage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
	
	String output;
	String path = "Saves/";
	
	
	public Reader( String fileName) throws IOException{
		
			
		
		try{
		
			FileReader fileReader = new FileReader(fileName);
			
			BufferedReader buffReader = new BufferedReader(fileReader);
			
			while((output = buffReader.readLine()) != null){
				
				System.out.println(output);
				
			}
			
			buffReader.close();
		
		}catch(FileNotFoundException e){
			System.out.println("Not able to find your file : "+ fileName);
		}catch(IOException e){
			System.out.println("Not able to read file: "+ fileName);
		}
		
	}
	
}
