package main;

import java.io.*;
import java.util.Scanner;

public class AnimalGuess {
	public static void main (String [] args) {
		Scanner stan = new Scanner(System.in);
		String currentPath = "";
		BufferedReader in = null;
		BufferedWriter out = null;
		DecisionTree tree = null;
		String answer;
		try {
			in = new BufferedReader(new FileReader("AnimalTree.txt"));
			tree = new DecisionTree(in.readLine());
			tree.fillTree(in);
		} catch (IOException e) {
			System.out.println("File Not Found");
		}
		boolean correct = true;
		boolean playing = true;
		boolean guessed = false;
		System.out.println("Think of an animal.\nI'll try to guess it.");
		while (playing == true){
			if (tree.followPath(currentPath).contains(".")) {
				playing = false;
				correct = false;
				System.out.println(tree.followPath(currentPath));
				break;
			}
			else if(!tree.followPath(currentPath).contains("?")) {
				System.out.println("Is your animal a " + tree.followPath(currentPath).split(" ")[1] + "?");
				guessed = true;
			}
			else{
				System.out.println(tree.followPath(currentPath));
			}
			answer = stan.next();
			if (answer.toLowerCase().contains("yes")) {
				if (guessed == true) {
					correct = true;
					break;
				}
				else {
					currentPath += "Y";
				}
			}
			else if (answer.toLowerCase().contains("no")) {
				currentPath += "N";
			}
			else {
				System.out.println("Please respond with yes or no.");
			}
		}
		if (correct) {
			System.out.println("I guessed it!");
		}
		else {
			try {
				out = new BufferedWriter(new FileWriter("AnimalTree.txt", true));
				tree.learn(tree, currentPath, out);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		playAgain();
		stan.close();
	}
	
	public static void playAgain() {
		Scanner stan = new Scanner(System.in);
		System.out.println("Play Again?");
		String answer = stan.next().toLowerCase();
		if (answer.contains("yes")) {
			main(new String[0]);
		}
		stan.close();
	}
	
}
