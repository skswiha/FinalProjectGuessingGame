package main;

import java.io.*;
import java.util.Scanner;

public class AnimalGuess {
	public static void main (String [] args) {
		//Scanner variable to read user input
		Scanner stan = new Scanner(System.in);
		//String to hold the path the user has entered
		String currentPath = "";
		//BufferedReader to hold the file to use for the tree
		BufferedReader in = null;
		//BufferedWriter to write any new information into the tree file with
		BufferedWriter out = null;
		//DecisionTree to put information into
		DecisionTree tree = null;
		//String to hold information the user enters
		String answer;
		try {
			in = new BufferedReader(new FileReader("AnimalTree.txt"));
			tree = new DecisionTree(in.readLine());
			tree.fillTree(in);
		} catch (IOException e) {
			System.out.println("File Not Found");
		}
		//Booleans to control the while loop monitor if the computer has guessed and if it was correct
		boolean correct = true;
		boolean playing = true;
		boolean guessed = false;
		System.out.println("Think of an animal.\nI'll try to guess it.");
		//Loop to have the computer guess while the game is playing
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
		//If/else to do different things if the computer is right or wrong
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
	
	/** Static method
	 * Asks the user if they would like to play again
	 * If the user would like to play again, restarts the main method */
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
