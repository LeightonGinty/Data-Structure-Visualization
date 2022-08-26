package com.example.demo;

import Node.Node;

import java.util.ArrayList;
import java.util.List;
//This class stores common data and utility functions that any tree will need. Trees can extend these functions to build a working tree.
public class Tree {
    Node root;
    public int boardWidth;
    public int boardHeight;
    Tree() { root = null; }

    public int circleSize = TreeManager.circleSize;
    private int HORIZONTAL_GAP = TreeManager.HORIZONTAL_GAP;
    private int VERTICAL_GAP = TreeManager.VERTICAL_GAP;

    Tree(Board board, int circleSize) {
        this.boardWidth = board.getWidth();
        this.boardHeight = board.getHeight();
        this.circleSize = circleSize;
        HORIZONTAL_GAP = circleSize*2;
        VERTICAL_GAP = circleSize*2;
    }

    Tree(Board board) {
        this.boardWidth = board.getWidth();
        this.boardHeight = board.getHeight();
    }

    public int getCount(Node root){
        if (root == null){
            return 0;
        }
        return 1 + getCount(root.left) + getCount(root.right);
    }
    public List<Node> toList(){
        List<Node> currentList = new ArrayList<Node>();
        return getList(currentList, root);
    }

    List<Node> getList(List<Node> currentList, Node root){
        if (root != null){
            currentList.add(root);
            getList(currentList,root.left);
            getList(currentList,root.right);
        }
        return currentList;
    }
    int getHeight(Node root, int currentHeight, int largestHeight){

        if(currentHeight > largestHeight){
            largestHeight = currentHeight;
        }

        if(root.left != null){
            currentHeight = getHeight(root.left, currentHeight++, largestHeight);
        }
        else if(root.right != null){
            currentHeight = getHeight(root.right, currentHeight++, largestHeight);
        }

        return currentHeight--;
    }
}
