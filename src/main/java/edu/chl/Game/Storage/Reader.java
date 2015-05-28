package edu.chl.Game.Storage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Reader {
	
	
	private final static String path = "Saves/";
	
	
	
	public static LinkedList<String> readFile(String fileName){
		String output;
		LinkedList<String> list = new LinkedList<String>();
		try{
			
			FileReader fileReader = new FileReader(path+fileName);
			
			BufferedReader buffReader = new BufferedReader(fileReader);
			
			while((output = buffReader.readLine()) != null){
				
				list.add(output);
				
			}
			
			buffReader.close();
		
		}catch(FileNotFoundException e){
			System.out.println("Not able to find your file : "+ fileName);
		}catch(IOException e){
			System.out.println("Not able to read file: "+ fileName);
		}
		return list;
	}
	
}
