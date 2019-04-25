package main;

import java.io.*;

public class AnimalGuess {
	public static void main (String [] args) {
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader("AnimalTree.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		}
		boolean playing = true;
		System.out.println("Think of an animal.\nI'll try to guess it.");
		while (playing == true){
		}
		
	}
	
	public void classify() {
	}
}
