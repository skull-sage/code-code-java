package leetcode.graph;



import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

class AdjGraph<T>
{
    private Map<T, List<T>> adjNodeMap;

    AdjGraph()
    {
        adjNodeMap = new HashMap<>();
    }

    public void addEdge(T uNode, T vNode)
    {
        adjNodeMap.compute(uNode,  AdjGraph::computeAdjList).add(vNode);
        adjNodeMap.compute(vNode,  AdjGraph::computeAdjList);
    }

    private static <T> List<T> computeAdjList(T node, List<T> adjList)
    {
        if(adjList == null)
            return new ArrayList<>();
        return adjList;
    }

    public int totalNodes()
    {
        return adjNodeMap.size();
    }
    public  void forEachNode(Consumer<T> nodeConsumer)
    {
        adjNodeMap.keySet().forEach(nodeConsumer);
    }
    public void forEachAdjacent(T u, Consumer<T> nodeConsumer)
    {
        adjNodeMap.get(u).forEach(nodeConsumer);
    }
    public void forEach(BiConsumer<T, List<T>> kvConsumer) {adjNodeMap.forEach(kvConsumer);}

    @Override
    public String toString() {
        return adjNodeMap.toString();
    }
}
class GraphVisit<T> {

    final AdjGraph<T> graph;
    public final Map<T, NodeVisit> visitMap;
    private boolean hasCyclicEdge;
    int visitTiming;

    public GraphVisit(AdjGraph<T> graph) {
        this.graph = graph;
        this.visitTiming = 0;
        this.hasCyclicEdge = false;
        this.visitMap = new HashMap<>(graph.totalNodes());
        this.graph.forEachNode(node -> this.visitMap.put(node, new NodeVisit()));
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

    public static GraphVisit experiment(AdjGraph graph) {
        GraphVisit graphVisit = new GraphVisit(graph);

        graph.forEachNode(node -> {
            NodeVisit visit = (NodeVisit) graphVisit.visitMap.get(node);
            if (visit.isNotVisited())
                graphVisit.visitNode(node, visit);
        });

        return graphVisit;
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


        public NodeVisit()
        {
            this.parent = null;
            this.dTime = 0;
            this.fTime = 0;
        }
        public int dTime(){return dTime;}
        public int fTime(){return fTime;}
        public boolean isNotVisited() {
            return this.dTime == 0;
        }
        public boolean isDiscovered() {
            return this.fTime == 0 && this.dTime > 0;
        }
        public boolean isVisited() {return this.fTime > 0;}
    }

}


public class CourseSchedule
{


    public static int[] findOrder(int numCourses, int[][] prerequisites) {



        AdjGraph<Integer> graph = new AdjGraph<>();
        for(int[] edge : prerequisites){
            graph.addEdge(edge[0], edge[1]);
        }


        GraphVisit visit = GraphVisit.experiment(graph);
        if(visit.hasCyclicEdge())
            return new int[]{};

        List<Map.Entry<Integer, GraphVisit.NodeVisit>> list = new ArrayList<>(visit.visitMap.entrySet());
        Comparator<GraphVisit.NodeVisit> comparator = Comparator.comparing(v -> v.fTime);
        list.sort(Map.Entry.comparingByValue(comparator));

        int result[] = new int[numCourses];

        int idx = 0;
        for(int j =0; j < numCourses; j++)
        {
            if(visit.visitMap.containsKey(j) == false)
            {
                result[idx] = j;
                idx++;
            }
        }

        for(Map.Entry<Integer, ?> entry : list)
        {
           result[idx++] = entry.getKey();

        }
        return result;

    }
    public static void main(String... args)
    {
        int nodeCount = 4;

     //   int[][] graph = {{1, 0}};
        int[][] graph = {{0, 1}, {0, 2}, {1, 2}, {2, 3}};

        int[] result = findOrder(nodeCount, graph);

        for(int r: result)
            System.out.printf("%d, ", r);

    }
}
