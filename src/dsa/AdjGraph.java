package dsa;

import java.util.*;
import java.util.function.Consumer;


public class AdjGraph<T>
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

    @Override
    public String toString() {
        return adjNodeMap.toString();
    }
}


