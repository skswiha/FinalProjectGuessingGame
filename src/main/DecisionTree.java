package main;

import java.io.*;

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
		for (int i = 0; i < 12; ++i) {
			line = in.readLine();
			if (line == null) break;
			words = line.split(" ");
			insertLineFromFile(words[0], line);
		}
	}
}
