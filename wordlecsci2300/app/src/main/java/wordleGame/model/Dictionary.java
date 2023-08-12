package wordleGame.model;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Dictionary{
	
	private String fileName;
	ArrayList<String> words;

	public Dictionary(String fileName) throws IOException{
		
		try{
			File inputFile = new File(fileName);
			words = new ArrayList<String>();
			//reads the words from the files
			Scanner scanner = new Scanner(inputFile);
			while (scanner.hasNextLine()){
				String word = scanner.nextLine();
				words.add(word);
			}
		}
		
		catch(IOException error){
			System.out.println("ERROR " + error.getMessage());
		}
		
	}
	
	public ArrayList<String> getWords(){
		return words;
	}

}
