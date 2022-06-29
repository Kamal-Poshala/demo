package trees;

public class AVLt<K extends Comparable<K>,V> {
	Node<K,V> root;
	int height(Node<K,V> n) {
		if(n==null)
			return 0;
		return n.height;
	}
	int max(int a, int b) {
	    return (a > b) ? a : b;
	  }

	Node<K,V> rightRotate(Node<K,V> y) {
	    Node<K,V> x = y.left;
	    Node<K,V> T2 = x.right;
	    x.right = y;
	    y.left = T2;
	    y.height = max(height(y.left), height(y.right)) + 1;
	    x.height = max(height(x.left), height(x.right)) + 1;
	    return x;
	  }

	  Node<K,V> leftRotate(Node<K,V> x) {
	    Node<K,V> y = x.right;
	    Node<K,V> T2 = y.left;
	    y.left = x;
	    x.right = T2;
	    x.height = max(height(x.left), height(x.right)) + 1;
	    y.height = max(height(y.left), height(y.right)) + 1;
	    return y;
	  }
	int getBalanceFactor(Node<K,V> N) {
	    if (N == null)
	      return 0;
	    return height(N.left) - height(N.right);
	  }
	 // Insert a node
	  Node<K,V> insertNode(Node<K,V> node, K key) {

	    // Find the position and insert the node
	    if (node == null) {
			
			return (new Node(key,"string"));
		}
	    if (key.compareTo(node.key)<0)
	      node.left = insertNode(node.left, key);
	    else if (key.compareTo(node.key)>0)
	      node.right = insertNode(node.right, key);
	    else
	      return node;

	    // Update the balance factor of each node
	    // And, balance the tree
	    node.height = 1 + max(height(node.left), height(node.right));
	    int balanceFactor = getBalanceFactor(node);
	    if (balanceFactor > 1) {
	      if (key.compareTo(node.left.key)<0) {
	        return rightRotate(node);
	      } else if (key.compareTo(node.left.key)>0) {
	        node.left = leftRotate(node.left);
	        return rightRotate(node);
	      }
	    }
	    if (balanceFactor < -1) {
	      if (key.compareTo(node.right.key)>0) {
	        return leftRotate(node);
	      } else if (key.compareTo(node.right.key)<0) {
	        node.right = rightRotate(node.right);
	        return leftRotate(node);
	      }
	    }
	    return node;
	  }

	  
	
	  Node<K,V> nodeWithMimumValue(Node<K,V> node) {
		    Node<K,V> current = node;
		    while (current.left != null)
		      current = current.left;
		    return current;
		  }
	  void preOrder(Node<K,V> node) {
		    if (node != null) {
		      System.out.print(node.key + " ");
		      preOrder(node.left);
		      preOrder(node.right);
		    }
		  }

		  // Delete a node
		  Node<K,V> deleteNode(Node<K,V> root, K key) {

		    // Find the node to be deleted and remove it
		    if (root == null)
		      return root;
		    if (key.compareTo(root.key)<0)
		      root.left = deleteNode(root.left, key);
		    else if (key.compareTo(root.key)>0)
		      root.right = deleteNode(root.right, key);
		    else {
		      if ((root.left == null) || (root.right == null)) {
		        Node<K,V> temp = null;
		        if (temp == root.left)
		          temp = root.right;
		        else
		          temp = root.left;
		        if (temp == null) {
		          temp = root;
		          root = null;
		        } else
		          root = temp;
		      } else {
		        Node<K,V> temp = nodeWithMimumValue(root.right);
		        root.key = temp.key;
		        root.right = deleteNode(root.right, temp.key);
		      }
		    }
		    if (root == null)
		      return root;

		    // Update the balance factor of each node and balance the tree
		    root.height = max(height(root.left), height(root.right)) + 1;
		    int balanceFactor = getBalanceFactor(root);
		    if (balanceFactor > 1) {
		      if (getBalanceFactor(root.left) >= 0) {
		        return rightRotate(root);
		      } else {
		        root.left = leftRotate(root.left);
		        return rightRotate(root);
		      }
		    }
		    if (balanceFactor < -1) {
		      if (getBalanceFactor(root.right) <= 0) {
		        return leftRotate(root);
		      } else {
		        root.right = rightRotate(root.right);
		        return leftRotate(root);
		      }
		    }
		    return root;
		  }
		  //only for specific usage
		public void printTree(Node<K, V> root2, String string, boolean b) {
			// TODO Auto-generated method stub
			if (root2 != null) {
			      System.out.print(string);
			      if (b) {
			        System.out.print("R----");
			        string += "   ";
			      } else {
			        System.out.print("L----");
			        string += "|  ";
			      }
			      System.out.println(root2.key);
			      printTree(root2.left, string, false);
			      printTree(root2.right, string, true);
			    }
			
		}
}
