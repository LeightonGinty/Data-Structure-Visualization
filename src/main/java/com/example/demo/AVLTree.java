package com.example.demo;

import Node.Node;

public class AVLTree extends customBinarySearchTree {

    public AVLTree(Board board, int circleSize) {
        super(board, circleSize);
    }
    public void insert(int key){
        root = insertRec(root, key);
        root = rebalanceTree(root,key);
        TreeManager.adjustCoords(root, root.position, 1);
    }
    private Node rebalanceTree(Node root, int key){
        int leftHeight = getHeight(root.left, 0,0);
        int rightHeight = getHeight(root.right, 0 ,0);
        if(Math.abs((leftHeight - rightHeight)) > 1){

            if(root.left != null && root.left.left != null){
                //Left left case
                root = rotateLeft(root,key);
            }
            else if(root.left != null && root.left.right != null){
                root = rotateLeft(root,key);
                root = rotateRight(root,key);
                //left right case
            }
            else if(root.right != null && root.right.left != null){
                root = rotateRight(root,key);
                root = rotateLeft(root,key);
                //right left case
            }
            else if(root.right != null && root.right.right != null){
                //right right case
                root = rotateRight(root,key);
            }
            else {
                if ((leftHeight > rightHeight)) rebalanceTree(root.left,key);
                else rebalanceTree(root.right,key);
            }
        }
        //We have a balanced tree
        return root;
    }
    private Node rotateLeft(Node root, int key){
        Node leftRight = root.left.right;

        root.left.right = root;
        root.left.right.left = leftRight;

        return root;
    }
    private Node rotateRight(Node root, int key){
        Node rightLeft = root.right.left;

        root.right.left = root;
        root.right.left.right = rightLeft;
        return root;
    }
}
