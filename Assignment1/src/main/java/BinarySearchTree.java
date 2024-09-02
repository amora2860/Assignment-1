import java.util.ArrayList;

public class BinarySearchTree {
    // Initializing the binary search tree by setting the root to point at no other nodes.
    public BinarySearchTree() {
        root = null;
    }

    // This defines what a node contains and what the names are for the connecting nodes.
    class Node {
        int data;
        Node leftChild;
        Node rightChild;
        int height;

        // Initial creation of the node and setting values to null.
        Node(int data) {
            this.data = data;
            leftChild = rightChild = null;
            height = 1;
        }
    }

    // Initializing the root node.
    Node root;
    //
    static int getHeight(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }


    static Node rightRotation(Node node) {
        Node n1 = node.leftChild;
        Node n2 = n1.rightChild;

        // Rotate nodes
        n1.rightChild = node;
        node.leftChild = n2;

        //
        node.height = 1 + Math.max(getHeight(node.leftChild),
                getHeight(node.rightChild));
        n1.height = 1 + Math.max(getHeight(n1.leftChild),
                getHeight(n1.rightChild));

        // Return updated root
        return n1;
    }

    //
    static Node leftRotation(Node node) {
        Node n1 = node.rightChild;
        Node n2 = n1.leftChild;

        // Rotate nodes
        n1.leftChild = node;
        node.rightChild = n2;

        //
        node.height = 1 + Math.max(getHeight(node.leftChild),
                getHeight(node.rightChild));
        n1.height = 1 + Math.max(getHeight(n1.leftChild),
                getHeight(n1.rightChild));

        // Return updated root
        return n1;
    }



    // This method calls for the insertNode method. It saves the returned node to
    // the root.
    public void insert(int data) {
        root = insertNode(root, data);
    }

    // This method recursively navigates the binary search tree when it finds the
    // proper place to insert the node.
    public Node insertNode(Node node, int data) {
        // If the starting node is null then the node is created.
        if (node == null) {
            node = new Node(data);
            return node;
        }
        // If the key to be added is less than or equal to then go to the left child and
        // keep searching recursively.
        if (data <= node.data) {
            node.leftChild = insertNode(node.leftChild, data);

        }
        // If the key to be added is greater than the node then go to the right child and keep searching recursively.
        else if (data > node.data) {
            node.rightChild = insertNode(node.rightChild, data);

        } else {
            return node;
        }

        return balanceTree(node, data);

    }

    static int getBalance(Node node) {
        if (node == null)
            return 0;
        return getHeight(node.leftChild) - getHeight(node.rightChild);
    }

    // need a function called balance tree
    public Node balanceTree(Node node, int data){
        // Update height of this ancestor node
        node.height = 1 + Math.max(getHeight(node.leftChild),
                getHeight(node.rightChild));

        // Get the balance factor of this node
        int balance = getBalance(node);



        // Left Left Case
        if (balance > 1 && data < node.leftChild.data)
            return rightRotation(node);

        // Right Right Case
        if (balance < -1 && data > node.rightChild.data)
            return leftRotation(node);

        // Left Right Case
        if (balance > 1 && data > node.leftChild.data) {
            node.leftChild = leftRotation(node.leftChild);
            return rightRotation(node);
        }

        // Right Left Case
        if (balance < -1 && data < node.rightChild.data) {
            node.rightChild = rightRotation(node.rightChild);
            return leftRotation(node);
        }

        // Return the (unchanged) node pointer
        return node;
    }
    // This method applies the root from the binary search tree to the findThisNode along with the value we are trying
    // to find.
    public Node find(int data) {

        Node node = findThisNode(root, data);

        return node;
    }

    public Node findThisNode(Node node, int data) {
        // If the node is not found, return null so the user can be informed.
        if (node == null) {
            return null;
        }

        // If the key equals the node then return the node.
        if (data == node.data) {

            return node;
        }
        // If the key is less than the node's data, search the left child recursively.
        else if (data <= node.data) {
            return findThisNode(node.leftChild, data);
        }
        // If the key is greater than the node's data, search the right child
        // recursively.
        else if (data > node.data) {
            return findThisNode(node.rightChild, data);
        }
        return node;
    }

    // This method updates the root of the binary search tree.
    // This is performed by calling deletedNode() and passing in the root compiled in deletedNode.
    public void deleteThisNode(Node node, int data) {
        root = deletedNode(node, data);
    }

    // This method recursively searches the binary search tree for the node to be deleted.
    public Node deletedNode(Node node, int data) {

        // If the key is greater than the root then go to the right child until the key is no longer greater than the root.
        if (node.data < data) {
            node.rightChild = deletedNode(node.rightChild, data);
        }
        // If the key is less than the root then go to the left child until the key is no longer less than the root.
        else if (node.data > data) {
            node.leftChild = deletedNode(node.leftChild, data);
        }
        // At this point the root.data equals the key.
        else {

            // If the root has no left child then return the root's right child so the node is removed.
            if (node.leftChild == null) {
                return node.rightChild;
            }
            // If the current node has no right child then return the left child so the node is removed.
            if (node.rightChild == null) {
                return node.leftChild;
            }

            // We are looking for the smallest value linked to the right child of the node to be deleted.
            Node successor = findSuccessor(node.rightChild);

            // Updating the root with the successor.
            node.data = successor.data;

            // The successor needs to be deleted from the right child of the root.
            node.rightChild = deletedNode(node.rightChild, successor.data);

            return node;
        }

        return balanceTree(node, data);

    }

    // Since the findSuccessor already provided the right child of the node to be deleted we only need to find the last left child linked.
    public Node findSuccessor(Node node) {

        while (node.leftChild != null) {
            node = node.leftChild;
        }
        return node;
    }

    // Printing inorder is having the node's values printed from smallest to largest. (ie 1,2,3.. etc.)
    public ArrayList<Integer> printInOrder(Node node, ArrayList<Integer> list) {
        if (node != null) {
            // Navigate to the last node in the left subtree.
            printInOrder(node.leftChild, list);
            // Add the node to the list.
            list.add(node.data);
            // Navigate to the last node in the right subtree.
            printInOrder(node.rightChild, list);
        }
        return list;
    }

    // Printing preorder means printing out the root first followed by working down the left subtree
    // until the last one is reached. Then traveling down the right subtree.
    // Trim from the root down to the leaves from left to right.
    public ArrayList<Integer> printPreOrder(Node node, ArrayList<Integer> list) {
        if (node != null) {
            // Add the node to the list.
            list.add(node.data);
            // Navigate to the last node in the left subtree.
            printPreOrder(node.leftChild, list);
            // Navigate to the last node in the right subtree.
            printPreOrder(node.rightChild, list);

        }
        return list;
    }

    // Printing in post-order means out the last node on the left subtree, then the node on the right subtree.
    // The process involves trimming the leaves from left to right until you reach the root.
    // The root is the last node printed.
    public ArrayList<Integer> printPostOrder(Node node, ArrayList<Integer> list) {
        if (node != null) {
            // Navigate to the last node in the left subtree.
            printPostOrder(node.leftChild, list);
            // Navigate to the last node in the right subtree.
            printPostOrder(node.rightChild, list);
            // Add the node to the list.
            list.add(node.data);

        }
        return list;
    }



}
