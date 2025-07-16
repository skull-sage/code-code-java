package dsa;

import java.util.HashMap;
import java.util.Map;

public class GraphVisitor<T> {
    final AdjGraph<T> graph;
    public final Map<T, NodeVisit> visitMap;
    private boolean hasCyclicEdge;
    int visitTiming;

    public GraphVisitor(AdjGraph<T> graph) {
        this.graph = graph;
        this.visitTiming = 0;
        this.hasCyclicEdge = false;
        this.visitMap = new HashMap<>(graph.totalNodes());
        graph.forEachNode(node -> this.visitMap.put(node, new NodeVisit<>()));

    }

    private void visitNode(final T uNode, final NodeVisit<T> uVisit) {
        uVisit.dTime = ++visitTiming;

        graph.forEachAdjacent(uNode, node -> {
            NodeVisit<T> nodeVisit = visitMap.get(node);
            if (nodeVisit.isNotVisited()) {
                nodeVisit.parent = uNode;
                visitNode(node, nodeVisit);
            } else if (nodeVisit.isDiscovered()) {
                this.hasCyclicEdge = true;
            }
        });

        uVisit.fTime = ++visitTiming;

    }

    public final boolean hasCyclicEdge()
    {
        return hasCyclicEdge;
    }

    public static GraphVisitor visit(AdjGraph graph) {
        GraphVisitor visitor = new GraphVisitor(graph);

        graph.forEachNode(node -> {
            NodeVisit visit = (NodeVisit) visitor.visitMap.get(node);
            if (visit.isNotVisited())
                visitor.visitNode(node, visit);
        });

        return visitor;
    }


    /**/
    /**
     * if interval u.{dTime, fTime} is within v.{dTime, fTime}
     * then u is a descendants of v
     */
    public static final class NodeVisit<T> {
        protected T parent;
        protected int dTime; // discovery time
        protected int fTime; // finishing time


        public NodeVisit() {
            this.parent = null;
            this.dTime = 0;
            this.fTime = 0;
        }

        public boolean isNotVisited() {
            return this.dTime == 0;
        }
        public boolean isDiscovered() {
            return this.dTime > 0;
        }
        public boolean isVisited() {
            return this.fTime > 0;
        }

    }

    public static void main(String... args)
    {
        int[][] testData = {{1, 5}, {1, 2}, {2, 3}, {5, 3}, {3, 4}, {4, 6}};
        AdjGraph<Integer> simpleGraph = new AdjGraph<>();
        for(int[] edgeData : testData)
            simpleGraph.addEdge(edgeData[0], edgeData[1]);

        System.out.println(simpleGraph);

        GraphVisitor<Integer> visitor = GraphVisitor.visit(simpleGraph);
        //visitor.
    }

}
