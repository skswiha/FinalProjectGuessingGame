package main;

import java.io.*;
import java.util.Scanner;

public class AnimalGuess {
	public static void main (String [] args) {
		Scanner stan = new Scanner(System.in);
		String answer;
		String currentPath = "";
		BufferedReader in = null;
		DecisionTree tree = null;
		try {
			in = new BufferedReader(new FileReader("AnimalTree.txt"));
			tree = new DecisionTree(in.readLine());
			tree.fillTree(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean playing = true;
		System.out.println("Think of an animal.\nI'll try to guess it.");
		while (playing == true){
			System.out.println(tree.followPath(currentPath));
			answer = stan.nextLine().toLowerCase();
			if (answer.contains("yes")) {
				currentPath += "Y";
			}
			else if (answer.contains("no")) {
				currentPath += "N";
			}
			else {
				System.out.println("Please respond with yes or no.");
			}
			System.out.println(currentPath);
		}
		
	}
	
	public void classify() {
	}
}
