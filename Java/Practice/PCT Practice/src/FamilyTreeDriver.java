import java.util.Scanner;

/**
 * Family Tree Relationship
 * 
 * @author Apoorv Semwal
 * 
 *         Input Specification: 1. The first line contains an integer n (0 < n <
 *         100) followed by ‘n’ data sets. 2. Each data set consists of two
 *         strings, separated by space. The first string indicates the parent of
 *         the second string. 3. Each string in the data set won't exceed more
 *         than 25 characters. Strings are NOT case sensitive. 4. An integer m
 *         (0 < m < 100) indicating number of relations for the family. 5. The
 *         following ‘m’ lines describe the relation in the family (child,
 *         parent, sibling, ancestor, descendant).
 * 
 *         Assumptions: 1. No name appears more than once in the family tree. 2.
 *         Each parent can have at most 2 children. 3. If a person has only one
 *         child then the child will be the left child of that node in the
 *         family tree. 4. The first string in the data set will be the root
 *         element. 5. Except for the root, nodes can only be added to the tree
 *         if the parent is already present in the tree.
 * 
 *         Output Specification: For each relation in the data set, your program
 *         should output T or F indicating whether the relation is true or false
 *         respectively.
 * 
 */

/**
Sample Input
8
Motilal Jawahar
Jawahar Indira
Motilal Kamala
Indira Sanjay
Sanjay Varun
Indira Rajiv
Rajiv Priyanka
Rajiv Rahul
6
Motilal child Jawahar
Varun descendant Indira
Priyanka sibling Varun
Sanjay child Indira
Sanjay ancestor Varun
Kamala ancestor Rahul


Sample Output
F T T F T T 
Motilal Jawahar Kamala Sanjay Rajiv Rahul Varun Indira
**/

public class FamilyTreeDriver {

	private static final Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		int tree_links = Integer.parseInt(s.nextLine().trim());
		FamilyTree f_tree = new FamilyTree();

		// Preparing the tree.
		for (int i = 0; i < tree_links; i++) {
			String link_val = s.nextLine().trim();
			String[] relation = link_val.split(" ");

			String parent_val = relation[0].trim();
			String child_val = relation[1].trim();
			if (f_tree.getRoot() != null) {
				prepareTree(f_tree.getRoot(), parent_val, child_val);
			} else {
				// Adding root as the first element with its left child.
				f_tree.setRoot(new Node(parent_val));
				f_tree.getRoot().setLchild(new Node(child_val));
			}
		}

		// Checking relations.
		int tree_relations = Integer.parseInt(s.nextLine());
		for (int i = 0; i < tree_relations; i++) {
			String relation_value = s.nextLine();
			String[] relation = relation_value.split(" ");
			if (checkRelation(f_tree.getRoot(), relation[1], relation[0], relation[2])) {
				System.out.print("T" + " ");
			} else {
				System.out.print("F" + " ");
			}
		}

		System.out.println();
		// Preorder traversal.
		preOrderTraversal(f_tree.getRoot());
		
		s.close();
	}

	private static boolean checkRelation(Node root, String rel, String mem1, String mem2) {

		boolean rel_flag = false;

		// Find the parent and check if other member is its right or left child
		if (rel.equalsIgnoreCase("child")) {
			Node parent = searchTreeParent(root, mem2);
			if (parent != null && (parent.getLchild() != null && parent.getLchild().getName().equalsIgnoreCase(mem1)
					|| parent.getRchild() != null && parent.getRchild().getName().equalsIgnoreCase(mem1))) {
				rel_flag = true;
			}

		}

		// Member2 becomes the Parent becomes and using it as the
		// root find the descendant Member1.
		else if (rel.equalsIgnoreCase("descendant")) {
			Node parent = searchTreeParent(root, mem2);
			if (parent != null) {

				Node des = searchTreeParent(parent, mem1);
				if (des != null) {
					rel_flag = true;
				}
			}
		}

		// Find Parent of both the members and check if same
		else if (rel.equalsIgnoreCase("sibling")) {
			Node parent1 = searchTreeParent(root, mem1);
			Node parent2 = searchTreeParent(root, mem2);
			if (parent1 != null && parent2 != null && parent1.getName().equalsIgnoreCase(parent2.getName())) {
				rel_flag = true;
			}
		}

		// Member1 becomes the Parent becomes and using it as the
		// root find the descendant Member2.
		else if (rel.equalsIgnoreCase("ancestor")) {
			Node parent = searchTreeParent(root, mem1);
			if (parent != null) {
				Node des = searchTreeParent(parent, mem2);
				if (des != null) {
					rel_flag = true;
				}
			}
		}

		return rel_flag;
	}

	private static void prepareTree(Node root, String parent_val, String child_val) {
		Node parent = null;
		parent = searchTreeParent(root, parent_val);
		if (parent == null) {
			System.out.println("Error Creating Tree.");
		} else if (parent.getLchild() == null) {
			parent.setLchild(new Node(child_val));
		} else {
			parent.setRchild(new Node(child_val));
		}
	}

	private static Node searchTreeParent(Node root, String member) {
		if (root != null && root.getLchild() != null && root.getLchild().getName().equalsIgnoreCase(member)) {
			return root;
		} else if (root != null && root.getLchild() != null && !(root.getLchild().getName().equalsIgnoreCase(member))) {
			return searchTreeParent(root.getLchild(), member);
		} else if (root != null && root.getRchild() != null && root.getRchild().getName().equalsIgnoreCase(member)) {
			return root;
		} else if (root != null && root.getRchild() != null && !(root.getRchild().getName().equalsIgnoreCase(member))) {
			return searchTreeParent(root.getRchild(), member);
		} else {
			return root;
		}
	}

	private static void preOrderTraversal(Node root) {
		if (root == null) {
			return;
		} else {
			System.out.print(root.getName() + " ");
			preOrderTraversal(root.getLchild());
			preOrderTraversal(root.getRchild());
		}
	}

}

class FamilyTree {

	private Node root;

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}
}

class Node {

	private String name;
	private Node lchild;
	private Node rchild;

	public Node(String name) {
		this.name = name;
		this.lchild = null;
		this.rchild = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Node getLchild() {
		return lchild;
	}

	public void setLchild(Node lchild) {
		this.lchild = lchild;
	}

	public Node getRchild() {
		return rchild;
	}

	public void setRchild(Node rchild) {
		this.rchild = rchild;
	}
}