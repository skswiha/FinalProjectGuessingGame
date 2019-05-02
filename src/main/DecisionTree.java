package main;

import java.io.*;
import java.util.Scanner;

public class DecisionTree extends BinaryTree<String> {
	
	/** Constructor for DecisionTree
	 * just a reference to the constructor for BinaryTree
	 * but a DecisionTree can only be a String */
	public DecisionTree(String data) {
		super(data);
	}
	
	/** Follows a path in the tree
	 * 
	 * @param path to follow
	 * @return the content of the node at the end of the path  */
	public String followPath(String text){
		int location = 0;
		BinaryTree<String> nowAt = this;
		while(nowAt != null && location < text.length() ) {
			if(text.charAt(location) == 'Y') {
				nowAt = nowAt.getLeft();
			}
			else if(text.charAt(location) == 'N') {
				nowAt = nowAt.getRight();
			}
			location++;
		}
		if (nowAt != null){
			return nowAt.getData();
		}
		else {
			return "I got it wrong. Please help me to learn.";
		}
	}
	
	/** Follows a path in the tree
	 * 
	 * @param path to follow
	 * @return the node at the end of the path */
	public BinaryTree<String> follow(String text){
		int location = 0;
		BinaryTree<String> nowAt = this;
		while(nowAt != null && location < text.length() ) {
			if(text.charAt(location) == 'Y') {
				nowAt = nowAt.getLeft();
			}
			else if(text.charAt(location) == 'N') {
				nowAt = nowAt.getRight();
			}
			location++;
		}
		return nowAt;
	}
	
	/** Inserts a line from a file into the tree
	 * 
	 * @param the path to the place the line should be inserted
	 * @param the line to be inserted*/
	public void insertLineFromFile(String yns, String question) {
		BinaryTree<String> current = this;
		current = follow(yns.substring(0, yns.length() - 1));
		if (yns.charAt(yns.length()-1) == 'Y') {
			current.setLeft(new BinaryTree<String>(question));
		}
		else if (yns.charAt(yns.length()-1) == 'N') {
			current.setRight(new BinaryTree<String>(question));		
		}
		
	}

	/** Fills a tree with information from a file
	 * 
	 * @param file to fill the tree with */
	public void fillTree(BufferedReader in) throws IOException {
		String[] words;
		String line = "";
		for (int i = 0; i < 100; ++i) {
			line = in.readLine();
			if (line == null) break;
			words = line.split(" ");
			insertLineFromFile(words[0], line);
		}
	}
	
	/**Adds new questions and answers to the tree
	 * 
	 * @param tree to put information into
	 * @param path to the new information
	 * @param BufferedWriter to use to add information */
	public void learn(DecisionTree tree, String currentPath, BufferedWriter out) throws IOException {
		Scanner stan = new Scanner(System.in);
		String animal, question, yesorno;
		currentPath = currentPath.substring(0, currentPath.length()-1);
		System.out.println("What was your animal?");
		animal = stan.nextLine();
		System.out.println("Type a yes or no question that would distinguish between a " + animal + " and a " + tree.followPath(currentPath).split(" ")[1]);
		question = stan.nextLine();
		System.out.println("Would you answer yes to this question for the " + animal + "?");
		yesorno = stan.nextLine().toLowerCase();
		out.append("\n" + currentPath + " " + question);
		if (yesorno.contains("yes")) {
			out.append("\n" + currentPath + "N " + tree.followPath(currentPath).split(" ")[1]);
			out.append("\n" + currentPath + "Y " + animal);
		}
		else {
			out.append("\n" + currentPath + "Y " + tree.followPath(currentPath).split(" ")[1]);
			out.append("\n" + currentPath + "N " + animal);
			}
	}
	
} 
