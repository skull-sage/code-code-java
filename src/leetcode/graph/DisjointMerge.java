package leetcode.graph;

public class DisjointMerge
{
    static class Node
    {
        Node parent;
        int rank;
        int val;

        public Node(int val) {
            this.val = val;
            this.parent = this;
            this.rank = 0;
        }
    }

    public static Node findSet(Node x)
    {
        if(x.parent != x)
            x.parent = findSet(x.parent);

        return x.parent;
    }

    public static void link(Node x, Node y)
    {
        if(x.rank > y.rank)
            y.parent = x;
        else {
            x.parent = y;
            if(x.rank == y.rank)
                y.rank = y.rank + 1;
        }
    }

    public static void union(Node x, Node y)
    {
        Node pX = findSet(x);
        Node pY = findSet(y);
        link(pX, pY);
    }
}
