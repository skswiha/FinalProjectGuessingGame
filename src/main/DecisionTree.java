package main;

import java.io.*;

public class DecisionTree extends BinaryTree<String> {
	
	public DecisionTree(String data) {
		super(data);
	}
	
	public String followPath(String text){
		boolean inPath = true;
		int location = 0;
		BinaryTree<String> nowAt = this;
		while(inPath == true) {
			if(location < text.length() && text.charAt(location) == 'Y') {
				nowAt = this.getLeft();
			}
			else if(location < text.length() && text.charAt(location) == 'N') {
				nowAt = this.getRight();
			}
			else {
				inPath = false;
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
	
	public void fillTree(BufferedReader in) throws IOException {
		String line = "";
		BinaryTree<String> current = this;
		int countLeft = 0;
		int countRight = 0;
		for (int i = 0; i < 12; ++i) {
			line = in.readLine();
			if (line != null && line.charAt(countLeft) == 'Y') {
				current.setLeft(new BinaryTree<String>(line));
				countLeft++;
			}
			else if (line != null && line.charAt(countRight) == 'N') {
				current.setRight(new BinaryTree<String>(line));
				countRight++;
				current = current.getLeft();
			}
			
		}
	}
}
