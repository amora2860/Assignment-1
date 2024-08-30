import java.util.ArrayList;

public class BinarySearchTree {
    // Initializing the binary search tree by setting the root to point at no other
    // nodes.
    public BinarySearchTree() {
        root = null;
    }

    // This defines what a node contains and what the names are for the connecting
    // nodes.
    class Node {
        int data;
        Node leftChild;
        Node rightChild;

        // Initial creation of the node and setting values to null.
        Node(int data) {
            this.data = data;
            leftChild = rightChild = null;
        }
    }

    // Initializing the root node.
    Node root;

    // This method calls for the insertNode method. It saves the returned node to
    // the root.
    public void insert(int key) {
        root = insertNode(root, key);
    }

    // This method recursively navigates the binary search tree when it finds the
    // proper place to insert the node.
    public Node insertNode(Node node, int key) {
        // If the starting node is null then the node is created.
        if (node == null) {
            node = new Node(key);
            return node;
        }
        // If the key to be added is less than or equal to then go to the left child and
        // keep searching recursively.
        if (key <= node.data) {
            node.leftChild = insertNode(node.leftChild, key);

        }
        // If the key to be added is greater than the node then go to the right child
        // and keep searching recursively.
        else if (key > node.data) {
            node.rightChild = insertNode(node.rightChild, key);
        }
        return node;
    }


    // Printing inorder is having the nodes values printed from smallest to largest. (ie 1,2,3.. etc.)
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

    // Printing in post order means out the last node on the left subtree, then the node on the right subtree.
    // The process involves trimming the leaves from left to right until you reach the root.
    // The root is the last node.
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

    // This method applies the root from the binary search tree to the findThisNode
    // along with the value we are trying to find.
    public Node find(int key) {

        Node node = findThisNode(root, key);

        return node;
    }

    public Node findThisNode(Node node, int key) {
        // If the node is not found, return null so the user can be informed.
        if (node == null) {
            return null;
        }

        // If the key equals the node then return the node.
        if (key == node.data) {

            return node;
        }
        // If the key is less than the node's data, search the left child recursively.
        else if (key <= node.data) {
            return findThisNode(node.leftChild, key);
        }
        // If the key is greater than the node's data, search the right child
        // recursively.
        else if (key > node.data) {
            return findThisNode(node.rightChild, key);
        }
        return node;
    }

    // This method updates the root of the binary search tree.
    // This is performed by calling deletedNode() and passing in the root compiled
    // in deletedNode.
    public void deleteThisNode(Node node, int key) {
        root = deletedNode(root, key);
    }

    // This method recursively searches the binary search tree for the node to be
    // deleted.
    public Node deletedNode(Node root, int key) {

        // If the key is greater than the root then go to the right child until the key
        // is no longer greater than the root.
        if (root.data < key) {
            root.rightChild = deletedNode(root.rightChild, key);
        }
        // If the key is less than the root then go to the left child until the key is
        // no longer less than the root.
        else if (root.data > key) {
            root.leftChild = deletedNode(root.leftChild, key);
        }
        // At this point the root.data equals the key.
        else {

            // If the root has no left child then return the root's right child so the node
            // is removed.
            if (root.leftChild == null) {
                return root.rightChild;
            }
            // If the current node has no right child then return the left child so the node
            // is removed.
            if (root.rightChild == null) {
                return root.leftChild;
            }

            // We are looking for the smallest value that is linked to the right child of
            // the node to be deleted.
            Node successor = findSuccessor(root.rightChild);

            // Updating the root with the successor.
            root.data = successor.data;

            // The successor needs to be deleted from the right child of the root.
            root.rightChild = deletedNode(root.rightChild, successor.data);

            return root;
        }
        return root;

    }

    // Since the findSuccessor already provided the right child of the node to be
    // deleted we only need to find the last left child linked.
    public Node findSuccessor(Node root) {

        while (root.leftChild != null) {
            root = root.leftChild;
        }
        return root;
    }

}
