package main;

import java.io.*;
import java.util.Scanner;

public class DecisionTree extends BinaryTree<String> {
	
	public DecisionTree(String data) {
		super(data);
	}
	
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
	
	public void insertLineFromFile(String yns, String question) {
		// find node based on yns in loop
		//insert question
		BinaryTree<String> current = this;
		current = follow(yns.substring(0, yns.length() - 1));
		if (yns.charAt(yns.length()-1) == 'Y') {
			current.setLeft(new BinaryTree<String>(question));
		}
		else if (yns.charAt(yns.length()-1) == 'N') {
			current.setRight(new BinaryTree<String>(question));		
		}
		
	}

	
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
