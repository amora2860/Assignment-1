
// This program provides a user interface that allows the user to create a binary search tree.
// The user can insert and delete nodes on the tree.
// Lastly the user can print out the tree in several different orders. Inorder, Preorder and Postorder.

import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class Main {

    // The binaryTree is initialized here so it can be used in all methods and that only one binary tree is created.
    public static BinarySearchTree binaryTree = new BinarySearchTree();
    // Having a single shareable array list. Makes printing the binary tree to the users more simple.
    public static ArrayList<Integer> myList = new ArrayList<Integer>();

    public static void main(String[] args) {
        // Creating the instance with labeling for the frame and each button.
        JFrame myFrame = new JFrame("Assignment #1");
        JButton button1Create = new JButton("1: Create A Binary Search Tree");
        JButton button2Add = new JButton("2: Add A Node");
        JButton button3Delete = new JButton("3: Delete A Node");
        JButton button4Inorder = new JButton("4: Print Nodes By InOrder");
        JButton button5Preorder = new JButton("5: Print Nodes By PreOrder");
        JButton button6Postorder = new JButton("6: Print Nodes By PostOrder");
        JButton button7Exit = new JButton("7: Exit Program");
        JLabel labelChoose = new JLabel("Please select a button.");

        // Specifying the size of the frame.
        myFrame.setSize(500, 500);

        // Ensuring that absolute positioning is used for the buttons.
        myFrame.setLayout(null);
        myFrame.setVisible(true);

        // Setting the absolute positions for all of the buttons.
        button1Create.setBounds(100, 50, 250, 40);
        button2Add.setBounds(100, 100, 170, 40);
        button3Delete.setBounds(100, 150, 170, 40);
        button4Inorder.setBounds(100, 200, 240, 40);
        button5Preorder.setBounds(100, 250, 240, 40);
        button6Postorder.setBounds(100, 300, 240, 40);
        button7Exit.setBounds(100, 350, 170, 40);
        labelChoose.setBounds(100, 10, 200, 40);

        // Adding each button to the frame.
        myFrame.add(button1Create);
        myFrame.add(button2Add);
        myFrame.add(button3Delete);
        myFrame.add(button4Inorder);
        myFrame.add(button5Preorder);
        myFrame.add(button6Postorder);
        myFrame.add(button7Exit);
        myFrame.add(labelChoose);

        // Close program if X in the top right corner of the frame is clicked on.
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // This button creates the initial binary tree based off of the values in the data[] array.
        // An action listener is needed to ensure that when the button is clicked the code will be executed.
        button1Create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int bstData[] = { 1, 2, 3, 4, 5, 6, 7 };

                // Checking the binary search tree doesn't already exist.
                if (binaryTree.root == null) {
                    // Adding each value of the array into the binary tree.
                    for (int i = 0; i < bstData.length; i++) {
                        binaryTree.insert(bstData[i]);
                    }
                    // The user needs to know that the binary tree has been created. So they do not click the button again.
                    JOptionPane.showMessageDialog(null, "A binary search tree has been created.");
                }
                else {
                    // If the user clicks the button after the binary tree is created. They will be told that the binary tree has already been created.
                    JOptionPane.showMessageDialog(null, "A binary search tree already exists.");
                }
            }
        });
        // This button adds a node to the binary tree.
        button2Add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ensuring a binary tree is already created.
                if (binaryTree.root != null) {
                    // When the user closes the text box by clicking cancel or the X. The key will be null.
                    // We can only check if the value is null if the input is turned into a string.
                    String keyValue = JOptionPane.showInputDialog("Enter an integer that you would like to add? ");
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
                            JOptionPane.showMessageDialog(null, "The entered value must be an integer.");
                        }
                    }
                }
                else {
                    // The user needs to know if the binary search tree does not exist.
                    JOptionPane.showMessageDialog(null, "You must create the binary search tree first.");
                }
            }
        });
        // This button deletes a node from the binary tree.
        button3Delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (binaryTree.root != null) {

                    String keyValue = JOptionPane.showInputDialog("Enter a number to be found: ");

                    if (keyValue != null) {
                        try {
                            int keyInt = Integer.parseInt(keyValue);
                            // Searching that the value exists in the binary search tree.
                            if (binaryTree.find(keyInt) != null) {
                                // If the value is found then it will be deleted.
                                binaryTree.deleteThisNode(binaryTree.root, keyInt);
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "Node not found.");
                            }
                        }
                        catch (Exception ect) {
                            // The user must be informed that they entered the wrong type.
                            JOptionPane.showMessageDialog(null, "The entered value must be an integer.");
                        }
                    }
                }
                else {
                    // The user needs to know if the binary search tree does not exist
                    JOptionPane.showMessageDialog(null, "You must create the binary search tree first.");
                }
            }
        });
        // This button prints the binary tree inorder.
        button4Inorder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // The array myList needs to be empty before we put anything into it.
                myList.clear();
                if (binaryTree.root != null) {
                    // The PrintInOrder method is called and myList is updated and returned.
                    binaryTree.printInOrder(binaryTree.root, myList);
                    JOptionPane.showMessageDialog(null, "Nodes printed in order: " + myList);
                }
                else {
                    JOptionPane.showMessageDialog(null, "You must create the binary search tree first.");
                }
            }
        });
        // This button prints the binary tree in preorder.
        button5Preorder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myList.clear();
                if (binaryTree.root != null) {
                    // The printPreOrder method is called and myList is updated and returned.
                    binaryTree.printPreOrder(binaryTree.root, myList);
                    JOptionPane.showMessageDialog(null, "Nodes printed in preorder: " + myList);
                }
                else {
                    JOptionPane.showMessageDialog(null, "You must create the binary search tree first.");
                }
            }
        });
        // This button prints the binary tree in post order.
        button6Postorder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                myList.clear();

                if (binaryTree.root != null) {
                    // The printPostOrder method is called and myList is updated and returned.
                    binaryTree.printPostOrder(binaryTree.root, myList);

                    JOptionPane.showMessageDialog(null, "Nodes printed in post order: " + myList);

                }
                else {
                    JOptionPane.showMessageDialog(null, "You must create the binary search tree first.");
                }

            }
        });
        // This button exits the program when clicked.
        button7Exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }

}
