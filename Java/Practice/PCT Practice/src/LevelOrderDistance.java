/**
 * Creating a Binary Tree from string representation. 
 * Printing a level order traversal.
 * Finding all nodes at particular distance from a given node. (Uses BFS)
 *
 * @author Apoorv Semwal
 * 
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class LevelOrderDistance {

	private static final Scanner s = new Scanner(System.in);

	public static void main(String[] args) {	

		NodeLevel root = null;		
		
		Stack<NodeLevel> build_tree = new Stack<>(); 

		String tree_string = s.nextLine().trim();

		for (int i = 0; i < tree_string.length(); i++){

			char c = tree_string.charAt(i);        

			if (c == ')') {
				root = build_tree.pop();
			}
			else if (c != ')' && c != '(') {
				int j = i + 1;
				while(tree_string.charAt(j) != ')' && tree_string.charAt(j) != '(') {
					j++;
				}
				NodeLevel node = new NodeLevel(tree_string.substring(i, j));
				if (!build_tree.isEmpty()) {
					root = build_tree.peek();
					if (root.getLchild() == null) {
						root.setLchild(node);
					}else {
						root.setRchild(node);
					}
				}
				build_tree.push(node);
			}
		}
		levelOrderTraversal(root);

		String node_find_distance = s.nextLine().trim();
		int distance = Integer.parseInt(s.nextLine().trim());
		List<String> neighbours = new ArrayList<>();
		neighbours = findNodesAtDistance(root,node_find_distance,distance);
	}

	private static void levelOrderTraversal(NodeLevel root) {
		
		Queue<NodeLevel> tree_level = new LinkedList();
		tree_level.add(root);
		
		while (!tree_level.isEmpty()) {
			System.out.print(tree_level.remove());
			
		}
	}

	private static List<String> findNodesAtDistance(NodeLevel root, String find_node, int distance) {
		return null;
	}
	
}

class NodeLevel {

	private String val;
	private NodeLevel lchild;
	private NodeLevel rchild;
	private int level;
	
	public NodeLevel(String val) {
		this.val = val;
		this.lchild = null;
		this.rchild = null;
		this.level  = 0;
	}

	public String getVal() {
		return val;
	}

	public NodeLevel getLchild() {
		return lchild;
	}

	public void setLchild(NodeLevel lchild) {
		this.lchild = lchild;
	}

	public NodeLevel getRchild() {
		return rchild;
	}

	public void setRchild(NodeLevel rchild) {
		this.rchild = rchild;
	}
	
}
