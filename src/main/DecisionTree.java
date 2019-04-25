package main;

public class DecisionTree extends BinaryTree<String> {
	
	public DecisionTree(BinaryTree<String> tree, String file) {
		super(tree);
	}
	
	public String followPath(String text){
		boolean inPath = true;
		int location = 0;
		BinaryTree<String> nowAt = this;
		while(inPath == true) {
			if(text.charAt(location) == 'Y') {
				nowAt = this.getLeft();
			}
			else if(text.charAt(location) == 'N') {
				nowAt = this.getRight();
			}
			else {
				inPath = false;
			}
			location++;
		}
		
		return nowAt.getData();
	}
}
