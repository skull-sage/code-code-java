package leetcode.graph;

import java.util.*;
import java.util.function.BiFunction;

public class ConnectableServers
{


    record WeightedEdge(int node, int weight){};

static class Graph{

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
        adjNodeMap.compute(v,mappingCompute).add(new WeightedEdge(u, weight));

    }

    public List<WeightedEdge> getAdj(Integer node)
    {
        return this.adjNodeMap.get(node);
    }

    public int vertxCount()
    {
        return this.adjNodeMap.keySet().size();
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



    public static int dfsReachableCount(Graph graph, Set<Integer> visited, Integer u, int pathWeight, int signal)
    {

        int reachable = 0;
        visited.add(u);

        for(WeightedEdge edge : graph.getAdj(u))
        {
            if(visited.contains(edge.node) == false)
            {
                reachable = reachable + dfsReachableCount(graph, visited, edge.node, pathWeight + edge.weight, signal);
            }
        }

        if(pathWeight  % signal == 0)
            reachable =   reachable + 1;

        return reachable;
    }
    public static int findSignalReachable(Graph graph, int src, int signal)
    {

        int totalConnectable = 0;
        int lastReached = 0;
        Set<Integer> visited = new HashSet<>();

        visited.add(src);

        for (WeightedEdge edge : graph.adjNodeMap.get(src))
        {
            int reachable = dfsReachableCount(graph, visited, edge.node, edge.weight, signal);
            totalConnectable = totalConnectable + lastReached * reachable;
            lastReached = lastReached + reachable;
        }

        return totalConnectable;

    }

    public static int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {

        Graph graph = new Graph();

        for(int[] edge: edges)
        {
            graph.addEdge(edge[0], edge[1], edge[2]);
        }

        int[] reachableServer = new int[graph.vertxCount()];

        for(int idx=0; idx < reachableServer.length; idx++)
        {
            int count = findSignalReachable(graph, idx, signalSpeed);
            reachableServer[idx] = count;

        }

        return reachableServer;
    }

    public static void main(String... args)
    {
        int[][] edges = {{0,6,3},{6,5,3},{0,3,1},{3,2,7},{3,1,6},{3,4,2}};
        int signal = 3;

        int[] result = countPairsOfConnectableServers(edges, signal);

        for(int count: result)
            System.out.printf("%d ", count);
    }
}
