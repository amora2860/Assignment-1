
// This program provides a user interface that allows the user to create a binary search tree.
// The user can insert and delete nodes on the tree.
// Lastly the user can print out the tree in several different orders. Inorder, Preorder and Postorder.

import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // The binaryTree is initialized here so it can be used in all methods and that only one binary tree is created.
    public static BinarySearchTree binaryTree = new BinarySearchTree();
    // Having a single shareable array list. Makes printing the binary tree to the users more simple.
    public static ArrayList<Integer> myList = new ArrayList<Integer>();

    public static void main(String[] args) {

        // Must ensure the menu always comes up after option 1 or 2 is selected
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

                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();

                // Display the text of state names
                if (choice == 1) {
                    int bstData[] = { 1, 2, 3, 4, 5, 6, 7 };
                    //int bstData[] = { 4, 2, 6, 1, 3, 5, 7 };

                    // Checking the binary search tree doesn't already exist.
                    if (binaryTree.root == null) {
                        // Adding each value of the array into the binary tree.
                        for (int i = 0; i < bstData.length; i++) {
                            binaryTree.insert(bstData[i]);
                        }
                        // The user needs to know that the binary tree has been created. So they do not click the button again.
                        System.out.println("A binary search tree has been created.");
                    }
                    else {
                        // If the user clicks the button after the binary tree is created. They will be told that the binary tree has already been created.
                        System.out.println("A binary search tree already exists.");
                    }
                }
                // Search for a pattern in the text
                if (choice == 2) {
                    // Ensuring a binary tree is already created.
                    if (binaryTree.root != null) {
                        // When the user closes the text box by clicking cancel or the X. The key will be null.
                        // We can only check if the value is null if the input is turned into a string.
                        System.out.println("Enter an integer that you would like to add? ");
                        scanner.nextLine(); // Consume newline
                        String keyValue = scanner.nextLine();
                        // As long as the user has entered something then the value can be executed on.
                        if (keyValue != null) {
                            // If the value is anything other than an integer the catch block will inform the user of the error.
                            try {
                                // Turn the string into an integer.
                                int keyInt = Integer.parseInt(keyValue);
                                // Add the integer into the binary search tree.
                                binaryTree.insert(keyInt);

                            }
                            catch (Exception ect) {
                                // The user must be informed that they entered the wrong type.
                                System.out.println("The entered value must be an integer.");
                            }
                        }
                    }
                    else {
                        // The user needs to know if the binary search tree does not exist.
                        System.out.println("You must create the binary search tree first.");
                    }
                }

                if (choice == 3) {
                    if (binaryTree.root != null) {

                        System.out.println("Enter a number to be found: ");
                        scanner.nextLine(); // Consume newline
                        String keyValue = scanner.nextLine();

                        if (keyValue != null) {
                            try {
                                int keyInt = Integer.parseInt(keyValue);
                                // Searching that the value exists in the binary search tree.
                                if (binaryTree.find(keyInt) != null) {
                                    // If the value is found then it will be deleted.
                                    binaryTree.deleteThisNode(binaryTree.root, keyInt);
                                }
                                else {
                                    System.out.println("Node not found.");
                                }
                            }
                            catch (Exception ect) {
                                // The user must be informed that they entered the wrong type.
                                System.out.println("The entered value must be an integer.");
                            }
                        }
                    }
                    else {
                        // The user needs to know if the binary search tree does not exist
                        System.out.println("You must create the binary search tree first.");
                    }
                }

                if (choice == 4) {
                    // The array myList needs to be empty before we put anything into it.
                    myList.clear();
                    if (binaryTree.root != null) {
                        // The PrintInOrder method is called and myList is updated and returned.
                        binaryTree.printInOrder(binaryTree.root, myList);
                        System.out.println("Nodes printed in order: " + myList);
                    }
                    else {
                        System.out.println("You must create the binary search tree first.");
                    }
                }

                if (choice == 5) {
                    myList.clear();
                    if (binaryTree.root != null) {
                        // The printPreOrder method is called and myList is updated and returned.
                        binaryTree.printPreOrder(binaryTree.root, myList);
                        System.out.println( "Nodes printed in preorder: " + myList);
                    }
                    else {
                        System.out.println("You must create the binary search tree first.");
                    }
                }

                if (choice == 6) {
                    myList.clear();

                    if (binaryTree.root != null) {
                        // The printPostOrder method is called and myList is updated and returned.
                        binaryTree.printPostOrder(binaryTree.root, myList);

                        System.out.println("Nodes printed in post order: " + myList);

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

                // Handle invalid menu selection
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


