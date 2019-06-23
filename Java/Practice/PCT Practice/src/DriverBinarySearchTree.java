import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * This is a utility code to test various operations on Binary Search Trees in
 * Java.
 * 
 * Assumption : All the key values in nodes are unique.
 * 
 * @author <a href="mailto:apoorv.semwal20@gmail.com">Apoorv Semwal</a>
 * @version 1.0
 * @since 16.Jan.2019
 */
public class DriverBinarySearchTree {

	private static final Scanner s = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("Enter the comma seperated sequence of Numbers in one line:");

		// Sample Sequence to test - 6,4,10,3,5,8,14,7,9
		String[] seq = s.nextLine().split(",");

		BinarySearchTree bst_tree = new BinarySearchTree();

		// Outer loop runs N times = length of input.
		for (int i = 0; i < seq.length; i++) {
			/**
			 * Adding new value to BST every time a new Number comes from sequence. Worst
			 * case Comparisons in each loop pass 1 + 2 + 3 + 4 + ----- i = O(n^2) - Sum of
			 * 1st n numbers - Skewed Tree.
			 * 
			 * Best case Comparisons in each loop pass 1 + 2 + 4 + 8 + ----- 2^i = O(log n)
			 * - Sum of GP - Balanced tree/Full/Complete Tree.
			 */
			prepareBst(bst_tree, Integer.parseInt(seq[i].trim()));
		}

		// Various Traversals
		System.out.println("*PreOrder Traversal*");
		preOrderTraversal(bst_tree.getRoot());

		System.out.println("\n*InOrder Traversal*");
		inOrderTraversal(bst_tree.getRoot());

		System.out.println("\n*PostOrder Traversal*");
		postOrderTraversal(bst_tree.getRoot());

		System.out.println("\n*LevelOrder Traversal*");
		levelOrderTraversalQueue(bst_tree.getRoot());

		System.out.println("\n");
		displayMenu();

		int choice = Integer.parseInt(s.nextLine());
		int inp;
		if (choice != 7) {

			// Loop goes on until user exists.
			while (choice != 7) {

				if (choice == 1) {
					System.out.println("Enter the element to add:\n");
					inp = Integer.parseInt(s.nextLine());
					prepareBst(bst_tree, inp);
					displayMenu();
					choice = Integer.parseInt(s.nextLine());

				} else if (choice == 2) {
					System.out.println("Enter the element to delete:\n");
					inp = Integer.parseInt(s.nextLine());
					deleteBst(bst_tree, inp);
					displayMenu();
					choice = Integer.parseInt(s.nextLine());

				} else if (choice == 3) {
					System.out.println("*LevelOrder Traversal*\n");
					int height = findBstHeight(bst_tree.getRoot());
					for (int i = 0; i < height; i++) {
						levelOrderTraversal(bst_tree.getRoot(), i);
					}
					displayMenu();
					choice = Integer.parseInt(s.nextLine());

				} else if (choice == 4) {
					int height = findBstHeight(bst_tree.getRoot());
					System.out.println("Height of the tree is : " + (height) + "\n");
					int lHeight = 0;
					int rHeight = 0;
					if (bst_tree.getRoot().getLchild() != null) {
						lHeight = findBstHeight(bst_tree.getRoot().getLchild());
						lHeight++;
					}
					if (bst_tree.getRoot().getRchild() != null) {
						rHeight = findBstHeight(bst_tree.getRoot().getRchild());
						rHeight++;
					}
					System.out.println("Dia of the tree is : " + (lHeight + rHeight) + "\n");
					displayMenu();
					choice = Integer.parseInt(s.nextLine());

				} else if (choice == 5) {
					System.out.println("Enter the level to print.(Root is at level 0):\n");
					inp = Integer.parseInt(s.nextLine());
					levelOrderTraversal(bst_tree.getRoot(), inp);
					displayMenu();
					choice = Integer.parseInt(s.nextLine());

				} else if (choice == 6) {
					System.out.println("Leaf Nodes are:\n");
					printLeaves(bst_tree.getRoot());
					displayMenu();
					choice = Integer.parseInt(s.nextLine());
				}
			}
		}

		s.close();
	}

	/**
	 * Display the user menu.
	 */
	private static void displayMenu() {
		System.out.println("\n1.Insert an element.");
		System.out.println("2.Delete an element.");
		System.out.println("3.View LevelOrder traversal.");
		System.out.println("4.Know the height of this tree.");
		System.out.println("5.Print members at a particular level.");
		System.out.println("6.Print all leaf nodes.");
		System.out.println("7.Exit");
		System.out.println("Enter your choice 1, 2, 3, 4, 5, 6 or 7\n");
	}

	/**
	 * Creates the BST keeping in mind its search property.
	 * 
	 * @param bst_tree Bst Tree created so far.
	 * @param val      Value to be inserted in Tree.
	 */
	private static void prepareBst(BinarySearchTree bst_tree, int val) {

		// If the given value is the first value create it as Root.
		if (bst_tree.getRoot() == null) {
			NodeBst node = new NodeBst(val);
			bst_tree.setRoot(node);
			System.out.println(val + " : Added successfully.\n");
		} else {
			/**
			 * Logic is find the parent of the new value to be inserted and insert it below
			 * that parent.
			 */
			NodeBst parent = searchParent(bst_tree.getRoot(), val);
			if (parent != null) {
				NodeBst child = new NodeBst(val);
				if (parent.getVal() > val && parent.getLchild() == null) {
					parent.setLchild(child);
					System.out.println(val + " : Added successfully.\n");
				} else if (parent.getVal() < val && parent.getRchild() == null) {
					parent.setRchild(child);
					System.out.println(val + " : Added successfully.\n");
				} else {
					System.out.println(val + " : Could not be added. Check Duplicate or invalid.\n");
				}
			} else {
				System.out.println(val + " : Could not be added. Check Duplicate or invalid.\n");
			}
		}
	}

	/**
	 * Finds the Parent Node for any given key value.
	 * 
	 * @param root Root of the Tree in which parent is to be searched.
	 * @param val  Key Value of the Node whose Parent is to be searched.
	 * @return Parent Node Object below which new Key value is to be inserted.
	 */
	private static NodeBst searchParent(NodeBst root, int val) {
		if (root.getVal() > val && root.getLchild() != null && root.getLchild().getVal() == val
				|| root.getVal() < val && root.getRchild() != null && root.getRchild().getVal() == val) {
			return root;
		} else if (root.getVal() > val && root.getLchild() != null) {
			return searchParent(root.getLchild(), val);
		} else if (root.getVal() < val && root.getRchild() != null) {
			return searchParent(root.getRchild(), val);
		} else {
			return root;
		}
	}

	/**
	 * Deletes a particular node from the Tree.
	 * 
	 * @param bst_tree Object containing the entire Tree.
	 * @param val      Key Value of the node to be deleted.
	 */
	private static void deleteBst(BinarySearchTree bst_tree, int val) {

		/**
		 * O(h) operation - that is the height of the tree. Worst case - O(n) - Skewed
		 * Tree Best case - O(log n) - Balanced tree/Full/Complete Tree.
		 */

		// Finding Parent of Node to be deleted for doing rearrangements.
		NodeBst parent = searchParent(bst_tree.getRoot(), val);

		if (parent != null) {

			NodeBst del_node = null;

			// Finding actual Node Object to be deleted - Check if its the right/left child.
			if (parent.getLchild() != null && parent.getLchild().getVal() == val) {
				del_node = parent.getLchild();
			} else if (parent.getRchild() != null && parent.getRchild().getVal() == val) {
				del_node = parent.getRchild();
			}

			// 3 Cases for deletion.

			// 1. Element to delete has no child.
			// Just remove linkage from parent.

			// 2. Element to delete has 1 child.
			// Link that child of deleted node to the parent of deleted node.

			// 3. Element to delete has 2 children.
			// Find Successor of the deleted node ( Largest value smaller than deleted node
			// )
			// Successor - Rightmost value on the left subtree of the node to be deleted.
			// That successor will only have a left child - as successor is the
			// already the rightmost value.

			boolean flag_del = false;
			if (del_node != null) {

				// Case 1:
				if (del_node.getLchild() == null && del_node.getRchild() == null) {
					if (parent.getLchild().getVal() == val) {
						parent.setLchild(null);
					} else {
						parent.setRchild(null);
					}
					System.out.println(val + " : Deleted\n");
					flag_del = true;
				}

				// Case 2:
				if (!flag_del) {
					if (parent.getLchild().getVal() == val && del_node.getLchild() != null
							&& del_node.getRchild() == null) {
						parent.setLchild(del_node.getLchild());
						System.out.println(val + " : Deleted\n");
						flag_del = true;
					} else if (parent.getLchild().getVal() == val && del_node.getRchild() != null
							&& del_node.getLchild() == null) {
						parent.setLchild(del_node.getRchild());
						System.out.println(val + " : Deleted\n");
						flag_del = true;
					} else if (parent.getRchild().getVal() == val && del_node.getRchild() != null
							&& del_node.getLchild() == null) {
						parent.setRchild(del_node.getRchild());
						System.out.println(val + " : Deleted\n");
						flag_del = true;
					} else if (parent.getRchild().getVal() == val && del_node.getRchild() != null
							&& del_node.getLchild() == null) {
						parent.setRchild(del_node.getRchild());
						System.out.println(val + " : Deleted\n");
						flag_del = true;
					}
				}

				// Case 3:
				/**
				 * Sequence of steps: 1. Link Parent of Node to be deleted to the Successor 2.
				 * Remove Linkage between successor and its old parent 3. Set Left Child of
				 * Successor as Left Child of the node to be deleted 4. Set Right Child of
				 * Successor as Right Child of the node to be deleted 5. Set Left-Right Children
				 * of the node to be deleted as Null.
				 */
				if (!flag_del) {
					if (del_node.getLchild() != null && del_node.getRchild() != null) {
						NodeBst succ = findSuccessor(del_node.getLchild());
						if (succ != null) {
							NodeBst succ_parent = searchParent(del_node, succ.getVal());
							if (parent.getLchild().getVal() == val) {
								parent.setLchild(succ);
								if (succ_parent.getRchild() != null
										&& succ_parent.getRchild().getVal() == succ.getVal()) {
									succ_parent.setRchild(null);
								}
								if (succ_parent.getLchild() != null
										&& succ_parent.getLchild().getVal() == succ.getVal()) {
									succ_parent.setLchild(null);
								}
								succ.setLchild(del_node.getLchild());
								succ.setRchild(del_node.getRchild());

								del_node.setLchild(null);
								del_node.setRchild(null);
								System.out.println(val + " : Deleted\n");
							} else {
								parent.setRchild(succ);
								if (succ_parent.getRchild() != null
										&& succ_parent.getRchild().getVal() == succ.getVal()) {
									succ_parent.setRchild(null);
								}
								if (succ_parent.getLchild() != null
										&& succ_parent.getLchild().getVal() == succ.getVal()) {
									succ_parent.setLchild(null);
								}
								succ.setLchild(del_node.getLchild());
								succ.setRchild(del_node.getRchild());
								del_node.setLchild(null);
								del_node.setRchild(null);
								System.out.println(val + " : Deleted\n");
							}
						}
					}
				}
			} else {
				System.out.println(val + " : Unable to delete. Check if its present in tree.");
			}
		} else {
			System.out.println(val + " : Unable to delete. Check if its present in tree.");
		}
	}

	/**
	 * Finds the Successor of any given node. Successor - Rightmost value on the
	 * left subtree of the node.
	 * 
	 * @param element Node whose Successor to be found
	 * @return Successor Node Object
	 */
	private static NodeBst findSuccessor(NodeBst element) {
		if (element.getRchild() != null) {
			return findSuccessor(element.getRchild());
		} else {
			return element;
		}
	}

	/**
	 * Finds the Height of the tree - Length of the longest path from Root to any
	 * leaf.
	 * 
	 * @param root Root node of the BST
	 * @return Height of the tree.
	 */
	private static int findBstHeight(NodeBst root) {
		if (root == null) {
			return 0;
		} else {
			/**
			 * Logic is like we recursively find the height of left and right subtree.
			 * Compare both of them and use the greater one for next level. When a leaf node
			 * comes both the below written lines will return 0. From there on value of
			 * height starts building up to root level.
			 */
			int left_h = findBstHeight(root.getLchild());
			int right_h = findBstHeight(root.getRchild());
			if (left_h < right_h) {
				return right_h + 1;
			} else {
				return left_h + 1;
			}
		}

	}

	/**
	 * Finds the Height of the tree - Length of the longest path from Root to any
	 * leaf.
	 * 
	 * @param root Root node of the BST
	 * @return Height of the tree.
	 */
	private static int findBstHeightIterative(NodeBst root) {
		int height = 0;
		Queue<NodeBst> q = new LinkedList<>();
		if (root != null) {
			q.add(root);
			while (true) {
				int num_of_elements = q.size();
				if (num_of_elements == 0) {
					break;
				}
				while (num_of_elements > 0) {
					NodeBst temp = q.peek();
					q.remove();
					if (temp.getLchild() != null) {
						q.add(temp.getLchild());
					}
					if (temp.getRchild() != null) {
						q.add(temp.getRchild());
					}
					num_of_elements--;
				}
				height++;
			}
		}
		return height - 1;
	}

	/**
	 * PreOrder Traversal - Root - Left - Right
	 * 
	 * @param root Root node of the BST
	 */
	private static void preOrderTraversal(NodeBst root) {
		if (root != null) {
			System.out.print(root.getVal() + " ");
			preOrderTraversal(root.getLchild());
			preOrderTraversal(root.getRchild());
		}
	}

	/**
	 * PostOrder Traversal - Left - Right - Root
	 * 
	 * @param root Root node of the BST
	 */
	private static void postOrderTraversal(NodeBst root) {
		if (root != null) {
			postOrderTraversal(root.getLchild());
			postOrderTraversal(root.getRchild());
			System.out.print(root.getVal() + " ");
		}
	}

	/**
	 * InOrder Traversal - Left - Root - Right
	 * 
	 * @param root Root node of the BST
	 */
	private static void inOrderTraversal(NodeBst root) {
		if (root != null) {
			inOrderTraversal(root.getLchild());
			System.out.print(root.getVal() + " ");
			inOrderTraversal(root.getRchild());
		}
	}

	/**
	 * LevelOrder Traversal - Print Nodes Level wise starting from Root at level 0.
	 * Recursive Solution - O(n^2) - if the tree is skewed - worst case. Requires a
	 * extra variable level - to stop the recursion
	 * 
	 * @param root  Root node of the BST
	 * @param level Represents elements from which level to print.
	 */
	private static void levelOrderTraversal(NodeBst root, int level) {
		if (root != null && level == 0) {
			System.out.print(root.getVal() + " ");
		} else {
			if (root.getLchild() != null) {
				levelOrderTraversal(root.getLchild(), level - 1);
			}
			if (root.getRchild() != null) {
				levelOrderTraversal(root.getRchild(), level - 1);
			}
		}
	}

	/**
	 * LevelOrder Traversal - Print Nodes Level wise starting from Root at level 0.
	 * Iterative Solution - O(n) Requires a Queue - Add element to queue. Pop it and
	 * put its children in the queue.
	 * 
	 * @param root Root node of the BST
	 */
	private static void levelOrderTraversalQueue(NodeBst root) {
		Queue<NodeBst> level_queue = new LinkedList<NodeBst>();
		level_queue.add(root);
		while (level_queue.size() > 0) {
			NodeBst node = level_queue.poll();
			System.out.print(node.getVal() + " ");
			if (node.getLchild() != null) {
				level_queue.add(node.getLchild());
			}
			if (node.getRchild() != null) {
				level_queue.add(node.getRchild());
			}
		}
	}

	/**
	 * Prints the leaf Nodes - Traverse the tree and print only when both the
	 * children are null.
	 * 
	 * @param root Root node of the BST
	 */
	private static void printLeaves(NodeBst root) {
		Queue<NodeBst> level_queue = new LinkedList<NodeBst>();
		level_queue.add(root);
		while (level_queue.size() > 0) {
			NodeBst node = level_queue.poll();
			if (node.getLchild() == null && node.getRchild() == null) {
				System.out.print(node.getVal() + " ");
			}
			if (node.getLchild() != null) {
				level_queue.add(node.getLchild());
			}
			if (node.getRchild() != null) {
				level_queue.add(node.getRchild());
			}
		}
	}
}

/**
 * Class representing the Binary Search Tree.
 * 
 * @author <a href="mailto:apoorv.semwal20@gmail.com">Apoorv Semwal</a>
 * @version 1.0
 * @since 16.Jan.2019
 */
class BinarySearchTree {
	private NodeBst root;

	public NodeBst getRoot() {
		return root;
	}

	public void setRoot(NodeBst root) {
		this.root = root;
	}
}

/**
 * Class representing the Node of a Binary Search Tree.
 * 
 * @author <a href="mailto:apoorv.semwal20@gmail.com">Apoorv Semwal</a>
 * @version 1.0
 * @since 16.Jan.2019
 */
class NodeBst {

	private int val;
	private NodeBst lchild;
	private NodeBst rchild;

	public NodeBst(int val) {
		this.val = val;
		this.lchild = null;
		this.rchild = null;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public NodeBst getLchild() {
		return lchild;
	}

	public void setLchild(NodeBst lchild) {
		this.lchild = lchild;
	}

	public NodeBst getRchild() {
		return rchild;
	}

	public void setRchild(NodeBst rchild) {
		this.rchild = rchild;
	}
}
