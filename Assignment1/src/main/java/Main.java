
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // Using only one instance of binary search tree in this program
    public static BinarySearchTree binaryTree = new BinarySearchTree();
    // Using only one instance of array list for all printing operations
    public static ArrayList<Integer> myList = new ArrayList<>();

    public static void main(String[] args) {

        // Must ensure the menu always comes up regardless of operation selected.
        while (true) {

            System.out.println("Please enter a number from 1 - 7 from the menu below.");
            System.out.println();
            System.out.println("1: Create A Binary Search Tree");
            System.out.println("2: Add A Node");
            System.out.println("3: Delete A Node");
            System.out.println("4: Print Nodes By InOrder");
            System.out.println("5: Print Nodes By PreOrder");
            System.out.println("6: Print Nodes By PostOrder");
            System.out.println("7: Exit Program");

            try {
                // Storing user's input
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();

                // Create the binary search tree
                if (choice == 1) {
                    int[] bstData = { 1, 2, 3, 4, 5, 6, 7 };

                    // Checking the binary search tree doesn't yet exist.
                    if (binaryTree.root == null) {
                        // Adding each value of the array into the binary tree.
                        for (int bstDatum : bstData) {
                            binaryTree.insert(bstDatum);
                        }
                        // The user needs to know that the binary tree has been created
                        System.out.println("A binary search tree has been created.");
                    }
                    else {
                        // The user needs to be told that the binary tree has already been created.
                        System.out.println("The binary search tree has already been created.");
                    }
                }

                if (choice <= 6 && choice >= 2) {
                    // Check if the BST already exists before moving on.
                    if (binaryTree.root != null) {
                        // The array myList needs to be empty before we put anything into it.
                        myList.clear();
                        // Add a node to the BST
                        if (choice == 2) {
                                System.out.println("Enter an integer that you would like to add? ");
                                scanner.nextLine(); // Consume newline
                                int keyInt = scanner.nextInt();
                                // Add the integer into the binary search tree.
                                binaryTree.insert(keyInt);
                        }
                        // Delete a node in the binary search tree
                        if (choice == 3) {
                                System.out.println("Enter the number of the node you would like to delete. ");
                                scanner.nextLine(); // Consume newline
                                int keyInt = scanner.nextInt();
                                // Searching that the value exists in the binary search tree.
                                if (binaryTree.find(keyInt) != null) {
                                    // If the value is found, then it will be deleted.
                                    binaryTree.deleteThisNode(binaryTree.root, keyInt);
                                }
                                else {
                                    System.out.println("Node not found.");
                                }
                        }
                        // Print the BST Inorder
                        if (choice == 4) {
                            // The PrintInOrder method is called, and myList is updated and returned.
                            binaryTree.printInOrder(binaryTree.root, myList);
                            System.out.println("Nodes printed in order: " + myList);
                        }
                        // Print the BST in Preorder
                        if (choice == 5) {
                            // The printPreOrder method is called, and myList is updated and returned.
                            binaryTree.printPreOrder(binaryTree.root, myList);
                            System.out.println( "Nodes printed in preorder: " + myList);
                        }
                        // Print the BST in Postorder
                        if (choice == 6) {
                            // The printPostOrder method is called, and myList is updated and returned.
                            binaryTree.printPostOrder(binaryTree.root, myList);
                            System.out.println("Nodes printed in post order: " + myList);
                        }
                    }
                    else {
                        System.out.println("You must create the binary search tree first.");
                    }
                }
                // Exit the program
                if (choice == 7) {
                    System.out.println("Ending Program.");
                    System.exit(0);
                }

                // Handle invalid menu selections
                if (choice > 7 || choice < 1) {
                    System.out.println("Please only enter a number from 1 through 7.");
                }

              // Handle any characters entered other than whole numbers
            } catch (Exception e) {
                System.out.println("Only whole numbers are accepted for menu selection.");
            }
        }
    }
}


