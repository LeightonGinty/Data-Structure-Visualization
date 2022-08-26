package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import Node.Node;

public class customBinarySearchTree extends Tree{
    customBinarySearchTree(Board board, int circleSize) {
        super(board, circleSize);
    }

    customBinarySearchTree(Board board) {
        super(board);
    }
    void insert(int key) {
        root = insertRec(root, key);
        TreeManager.adjustCoords(root, new Coordinate(boardWidth/2,circleSize), 1);
    }
    void remove(int key) {
//            root = insertRec(root,key);
        root = removeRec(root,key);
        TreeManager.adjustCoords(root,root.position, 1);
    }
    Node removeRec(Node root, int key){
        if (root == null){
            return root;
        }

        if (key < root.key){
            root.left = removeRec(root.left,key);
        } else if (key > root.key) {
            root.right = removeRec(root.right, key);
        }
        else {
            if (root.left == null){
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.key = minNode(root.right);

            root.right = removeRec(root.right, root.key);

        }
        return root;
    }
    Node deleteRec(Node root, int key){
        if(root.left.key == key){
            root.left = null;
        }
        else if (root.right.key == key){
            root.right = null;
        }
        else{
            if (root.key < key){
                deleteRec(root.left, key);
            } else if (root.key > key) {
                deleteRec(root.right, key);
            }
        }
        return root;
    }
    int minNode(Node root){
        int minv = root.key;
        while (root.left != null){
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }

    /* A recursive function to
       insert a new key in BST */
    Node insertRec(Node root, int key)
    {

    /* If the tree is empty,
       return a new node */
        if (root == null) {
            root = new Node(key,new Coordinate(boardWidth,boardHeight));
            return root;
        }

        /* Otherwise, recur down the tree */
        else if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);


        /* return the (unchanged) node pointer */
        return root;
    }
}
