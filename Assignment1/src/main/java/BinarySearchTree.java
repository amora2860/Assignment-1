import java.util.ArrayList;

public class BinarySearchTree {
    // Initializing the binary search trees root
    public BinarySearchTree() {
        root = null;
    }

    // Defining what attributes a node will have
    class Node {
        int data;
        Node leftChild;
        Node rightChild;
        int height;

        // Properties of a new node
        Node(int data) {
            this.data = data;
            leftChild = rightChild = null;
            height = 1;
        }
    }

    // Creating the root node.
    Node root;

    // Retrieving the stored height of a node.
    static int getHeight(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    // Calls for insertNode and updates root
    public void insert(int data) {
        root = insertNode(root, data);
    }

    // This method recursively navigates the binary search tree to find the proper place to insert the new node.
    public Node insertNode(Node node, int data) {
        // If the starting node is null, then the new node is created.
        if (node == null) {
            node = new Node(data);
            return node;
        }
        // If data to be added is less than or equal to node.data then go to the left child and
        // keep searching recursively.
        if (data <= node.data) {
            node.leftChild = insertNode(node.leftChild, data);

        }
        // If data to be added is greater than to node.data then go to the right child and
        // keep searching recursively.
        else if (data > node.data) {
            node.rightChild = insertNode(node.rightChild, data);

        } else {
            return node;
        }
        // Check to see if the tree needs to be balanced
        return balanceTree(node, data);

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

        // If data equals node then return the node.
        if (data == node.data) {

            return node;
        }
        // If the data is less than the node's data, search the left child recursively.
        else if (data <= node.data) {
            return findThisNode(node.leftChild, data);
        }
        // If the data is greater than the node's data, search the right child
        // recursively.
        else if (data > node.data) {
            return findThisNode(node.rightChild, data);
        }
        return node;
    }


    // After deletedNode has finished its work root is updated.
    public void deleteThisNode(Node node, int data) {
        root = deletedNode(node, data);
    }

    // This method recursively searches the binary search tree for the node to be deleted.
    public Node deletedNode(Node node, int data) {

        // If the data is greater than the root, then go to the right child until the data is no longer greater than the root.
        if (node.data < data) {
            node.rightChild = deletedNode(node.rightChild, data);
        }
        // If the data is less than the root, then go to the left child until the data is no longer less than the root.
        else if (node.data > data) {
            node.leftChild = deletedNode(node.leftChild, data);
        }
        // At this point, the root.data equals the data.
        else {

            // If the root has no left child, then return the root's right child so the node is removed.
            if (node.leftChild == null) {
                return node.rightChild;
            }
            // If the current node has no right child, then return the left child so the node is removed.
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
        // Check to see if the tree needs to be balanced
        return balanceTree(node, data);

    }

    // Return the smallest value linked to node
    public Node findSuccessor(Node node) {

        while (node.leftChild != null) {
            node = node.leftChild;
        }
        return node;
    }

    // Returns the balance of a particular node to check if we have any imbalance
    static int getBalance(Node node) {
        if (node == null)
            return 0;
        return getHeight(node.rightChild) - getHeight(node.leftChild);
    }

    // When checking the balance of a node and performing the rotations to resolve the imbalance if it exists.
    // There are four cases of rotation that can occur.

    // Single right rotation: The balance is less than -1, meaning the left subtree is too long and the value of data is
    // less than nodes left child. Perform a right rotation on node.

    // Right rotation, then a left rotation: The balance is greater than 1, meaning the right subtree is too long and the value of data is
    // greater than the root's right child. Perform a right rotation on nodes right child. Set node.right child equal to that.
    // Then perform a left rotation on node.

    // Single left rotation: The balance is greater than 1, meaning the right subtree is too long and the value of data is
    // greater than the nodes right child. Perform a left rotation on node.

    // Left rotation, then a right rotation: The balance is less than -1 meaning the left subtree is too long and the value of data is
    // greater than the nodes left child. Perform a left rotation on nodes left child. Set node.left child equal to that.
    // Then perform a right rotation on node.


    public Node balanceTree(Node node, int data) {
        // Update height of the node
        node.height = 1 + Math.max(getHeight(node.leftChild),
                getHeight(node.rightChild));

        // Get the balance of this node
        int balance = getBalance(node);

        // Single right rotation
        if (balance < -1 && data < node.leftChild.data) {
            return rightRotation(node);
        }

        // Right rotation then a left rotation
        if (balance > 1 && data < node.rightChild.data) {
            node.rightChild = rightRotation(node.rightChild);
            return leftRotation(node);
        }

        // Single left rotation
        if (balance > 1 && data > node.rightChild.data) {
            return leftRotation(node);
        }

        // Left rotation then a right rotation
        if (balance < -1 && data > node.leftChild.data) {
            node.leftChild = leftRotation(node.leftChild);
            return rightRotation(node);
        }

        // Return node
        return node;
    }

    // Perform a right rotation on the binary search tree at a specific node.
    static Node rightRotation(Node node) {
        Node n1 = node.leftChild;
        Node n2 = n1.rightChild;

        // Reassigning node links aka rotating the nodes
        n1.rightChild = node;
        node.leftChild = n2;

        // Updating node to the new height after rotation
        node.height = 1 + Math.max(getHeight(node.leftChild),
                getHeight(node.rightChild));
        // Updating n1 to the new height after rotation
        n1.height = 1 + Math.max(getHeight(n1.leftChild),
                getHeight(n1.rightChild));

        // Return the updated node
        return n1;
    }

    // Perform a left rotation on the binary search tree at a specific node.
    static Node leftRotation(Node node) {
        Node n1 = node.rightChild;
        Node n2 = n1.leftChild;

        // Reassigning node links aka rotating the nodes
        n1.leftChild = node;
        node.rightChild = n2;

        // Updating node to the new height after rotation
        node.height = 1 + Math.max(getHeight(node.leftChild),
                getHeight(node.rightChild));
        // Updating n1 to the new height after rotation
        n1.height = 1 + Math.max(getHeight(n1.leftChild),
                getHeight(n1.rightChild));

        // Return the updated node
        return n1;
    }

    // Printing inorder is having the node's values printed from smallest to largest. (i.e., 1,2,3.. etc.)
    public void printInOrder(Node node, ArrayList<Integer> list) {
        if (node != null) {
            // Navigate to the last node in the left subtree.
            printInOrder(node.leftChild, list);
            // Add the node to the list.
            list.add(node.data);
            // Navigate to the last node in the right subtree.
            printInOrder(node.rightChild, list);
        }
    }

    // Printing preorder means printing out the root first followed by working down the left subtree
    // until the last one is reached. Then traveling down the right subtree.
    // Trim from the root down to the leaves from left to right.
    public void printPreOrder(Node node, ArrayList<Integer> list) {
        if (node != null) {
            // Add the node to the list.
            list.add(node.data);
            // Navigate to the last node in the left subtree.
            printPreOrder(node.leftChild, list);
            // Navigate to the last node in the right subtree.
            printPreOrder(node.rightChild, list);

        }
    }

    // Printing in post-order means out the last node on the left subtree, then the node on the right subtree.
    // The process involves trimming the leaves from left to right until you reach the root.
    // The root is the last node printed.
    public void printPostOrder(Node node, ArrayList<Integer> list) {
        if (node != null) {
            // Navigate to the last node in the left subtree.
            printPostOrder(node.leftChild, list);
            // Navigate to the last node in the right subtree.
            printPostOrder(node.rightChild, list);
            // Add the node to the list.
            list.add(node.data);

        }
    }

}
