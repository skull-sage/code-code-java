package leetcode.graph;

import java.util.*;

public class MinHeightTree
{



    static class Node
    {
        int val;
        boolean visited;
        int distance;

        Node parent;

        List<Node> adjList;

        public Node(int val) {
            this.val = val;
            this.visited = false;
            this.adjList = new ArrayList<>();
        }

        public void addAdj(Node node)
        {
            adjList.add(node);
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(){
            visited = true;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return val == node.val;
        }

        @Override
        public int hashCode() {
            return Objects.hash(val);
        }

        @Override
        public String toString() {
            return val+".dist="+ distance;
        }
    }

    record  PairMax(Node vMax, int distMax){}

    public static PairMax findVMax(Iterator<Node> nodeIt, Node u)
    {
        Node vMax = null;
        int distMax = 0;

        nodeIt.forEachRemaining(node -> {
            node.distance = 0;
            node.visited = false;
            node.parent = node;
        });


        List<Node> bfsQue = new ArrayList<>();
        bfsQue.add(u);
        u.visited = true;

        for(int idx=0; idx < bfsQue.size(); idx++)
        {
            Node cur = bfsQue.get(idx);
            for(Node v: cur.adjList)
            {
                if(!v.isVisited()){
                    v.distance = cur.distance + 1;
                    v.parent = cur;
                    if(distMax < v.distance)
                    {
                        distMax = v.distance;
                        vMax = v;
                    }

                    v.visited = true;
                    bfsQue.add(v);
                }
            }

        }


        return new PairMax(vMax, distMax);
    }

    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {


        if(n == 1)
            return Arrays.asList(0);



        Map<Integer, Node> nodeMap = new HashMap<>(n);

        for(int idx=0; idx < n; idx++)
            nodeMap.put(idx, new Node(idx));

        for(int[] edge : edges)
        {
            Node u = nodeMap.get(edge[0]);
            Node v = nodeMap.get(edge[1]);
            u.addAdj(v);
            v.addAdj(u);
        }

        Node randomV = nodeMap.get(0);
        PairMax pair = findVMax(nodeMap.values().iterator(), randomV);

        PairMax finalPair = findVMax(nodeMap.values().iterator(), pair.vMax);

        List<Node> path = new ArrayList<>();
        Node st = finalPair.vMax;

        while (st.parent != st)
        {
            path.add(st);
            st = st.parent;
        }

        path.add(st);

        int idx = path.size() / 2;

        if(path.size() % 2 == 0)
        {
            return Arrays.asList(path.get(idx-1).val, path.get(idx).val);
        }
        else {
            return Arrays.asList(path.get(idx).val);
        }

    }

    public static void main(String... args)
    {
        /*int n = 15;
        int[][] edges = {{2, 1}, {3, 1}, {1, 4}, {4, 5}, {4, 9}, {4, 8}, {0, 14},
                {4, 9}, {0, 9}, {5, 6}, {5, 7}, {8, 10}, {10, 11}, {12, 10}, {13, 12}};*/

        int n = 6;
        int[][] edges = {{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};
        System.out.println(findMinHeightTrees(n, edges));


    }
}
