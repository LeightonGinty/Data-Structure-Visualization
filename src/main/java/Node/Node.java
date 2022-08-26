package Node;

import com.example.demo.Coordinate;

    public class Node {
        public int key;
        public Node left, right;

        public Coordinate position;
        public Node(int item, Coordinate position)
        {
            key = item;
            left = right = null;
            this.position = position;
        }
    }

