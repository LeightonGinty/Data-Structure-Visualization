package com.example.demo;

import Node.Node;

public abstract class TreeManager {
    public static int circleSize = 50;
    public static int HORIZONTAL_GAP = circleSize*2;
    public static int VERTICAL_GAP = circleSize*2;

    public static int getCount(Node root){
        if (root == null){
            return 0;
        }
        return 1 + getCount(root.left) + getCount(root.right);
    }

    static void adjustCoords(Node root, Coordinate rootPosition, int level){
        int leftX,leftY, rightX,rightY;
        root.position = new Coordinate(rootPosition.x, rootPosition.y);
        if (root != null) {
            level = level + 1;

            if (root.left != null){
                //get amount of padding needed by finding all nodes beneath to the right.
                int nodesToRight = TreeManager.getCount(root.left.right);

                leftX = rootPosition.x - HORIZONTAL_GAP*(nodesToRight+1);
                leftY = (level*VERTICAL_GAP);

                Coordinate leftCoord = new Coordinate(leftX,leftY);
                adjustCoords(root.left, leftCoord,level);

            }
            if (root.right != null){
                //get amount of padding needed by finding all nodes beneath to the left.
                int nodesToLeft = TreeManager.getCount(root.right.left);

                rightX = rootPosition.x + HORIZONTAL_GAP*(nodesToLeft+1);
                rightY = (level*VERTICAL_GAP);

                Coordinate rightCoord = new Coordinate(rightX,rightY);
/*
                    root.position = rightCoord;
*/
                adjustCoords(root.right, rightCoord, level);
            }
        }
//            level = level-1;
    }
}
