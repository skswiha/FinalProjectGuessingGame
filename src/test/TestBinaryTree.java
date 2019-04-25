package test;

import org.junit.Test;

import main.BinaryTree;

public class TestBinaryTree {
	BinaryTree<Double> t1, t2, t3, t4;
	
	@Test
	public void TestTree(){
		t1 = new BinaryTree<Double>(4.0);
		t1.setLeft(new BinaryTree<Double>(3.5));
		t1.setRight(new BinaryTree<Double>(5.5));
		t1.getLeft().setLeft(new BinaryTree<Double>(1.25));
		t1.getLeft().setRight(new BinaryTree<Double>(3.75));
		t1.getRight().setLeft(new BinaryTree<Double>(4.75));
		t1.getRight().setRight(new BinaryTree<Double>(8.5));
		t1.getRight().getRight().setLeft(new BinaryTree<Double>(7.0));
		t1.getRight().getRight().setRight(new BinaryTree<Double>(13.0));
		t1.print();
		
		t2 = new BinaryTree<Double>(t1);
		t2.setData(-1.0);
		t2.print();
		
		t3 = t1;
		t3.setData(6.25);
		t1.print();
		
		t4 = new BinaryTree<Double>(0.0);
		t4.setLeft(t1);
		t4.setRight(t2);
		t1.print();
		t1.getLeft().setData(5.0);
		t4.print();
		
		
	}
}
