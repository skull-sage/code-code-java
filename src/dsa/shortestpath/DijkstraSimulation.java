package dsa.shortestpath;

import java.util.*;
import java.util.function.BiFunction;


class VisitingNode
{
    final int node;
    final int swd;
    final VisitingNode parent;

    VisitingNode(int node, int swd)
    {
        this.node = node;
        this.swd = swd;
        this.parent = this;
    }

    VisitingNode(int node, int swd, VisitingNode parent)
    {
        this.node = node;
        this.swd = swd;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return String.format("{%d:swd-%d}", this.node, this.swd);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitingNode that = (VisitingNode) o;
        return node == that.node;
    }

    @Override
    public int hashCode() {
        return Objects.hash(node);
    }
}

record WeightedEdge(int node, int weight){};

class Graph{


    Map<Integer, List<WeightedEdge>> adjNodeMap;
    BiFunction<Integer, List<WeightedEdge>, List<WeightedEdge>> mappingCompute = (k, list) ->{
        return list == null ? new ArrayList<>() : list;
    };

    public Graph( ) {
        this.adjNodeMap = new HashMap<>();
    }

    public void addEdge(Integer u, Integer v, int weight)
    {
        adjNodeMap.compute(u, mappingCompute).add(new WeightedEdge(v, weight));
        adjNodeMap.compute(v,mappingCompute);

    }

    @Override
    public String toString() {
        String result = "";
        for (Map.Entry<Integer, List<WeightedEdge>> entry: adjNodeMap.entrySet()){
            result = result + String.format("node:%d adjacent: %s\n", entry.getKey(), entry.getValue().toString());
        };

        return result;
    }
}



public class DijkstraSimulation
{

    public static PriorityQueue<VisitingNode> init(Graph graph, int srcNode)
    {
        VisitingNode visitingSrc = new VisitingNode(srcNode, 0);

        Comparator<VisitingNode> priorComp = Comparator.comparing(vn -> vn.swd);
        PriorityQueue<VisitingNode> queue = new PriorityQueue<>(priorComp);
        queue.add(visitingSrc);

        graph.adjNodeMap.keySet().forEach(node -> {
            if(node.equals(srcNode) == false)
                queue.add(new VisitingNode(node, Integer.MAX_VALUE));
        });

        return queue;
    }

    public static void runDijkstra(Graph graph, int srcNode)
    {
        Map<Integer, Integer> swdMap = new HashMap<>();
        graph.adjNodeMap.keySet().forEach(node -> swdMap.put(node, Integer.MAX_VALUE));
        swdMap.put(srcNode, 0);
        PriorityQueue<VisitingNode> pque = init(graph, srcNode);

        int nodeCount = graph.adjNodeMap.keySet().size();
        Set<VisitingNode> resultSet = new HashSet<>();


        while (resultSet.size() < nodeCount)
        {
            // there might be duplicates of selected node due to relaxing distance ops
            while (resultSet.contains(pque.peek()))
                pque.poll();

            VisitingNode u = pque.poll();
            resultSet.add(u);

            for(WeightedEdge vEdge: graph.adjNodeMap.get(u.node))
            {

                // Relax all v of set-adjacent(u)
                // src ~-> v
                int vSwd = swdMap.get(vEdge.node());
                int checkingWeight = u.swd + vEdge.weight();

                if(vSwd > checkingWeight)
                {
                    pque.add(new VisitingNode(vEdge.node(), checkingWeight, u));
                    swdMap.put(vEdge.node(), checkingWeight);
                }


            }

        }

        System.out.println(resultSet);

    }

    public  static void main(String... args){
      //  int n = 10;
        Graph graph = new Graph();
        graph.addEdge(0, 1, 3);
        graph.addEdge(0, 2, 1);
        graph.addEdge(0, 3, 1);
        graph.addEdge(2, 1, 1);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 5, 2);
        graph.addEdge(2, 5, 1);
        //graph.addEdge(2, 4, 1);



        runDijkstra(graph, 0);

    }




}
