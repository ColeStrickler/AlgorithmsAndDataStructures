import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.PriorityQueue;

/**
 * Your implementation of various graph traversal algorithms.
 */
public class GraphAlgorithms {


    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {
        List<Vertex<T>> visitedSet = new ArrayList<> ();
        ArrayDeque<Vertex<T>> queue = new ArrayDeque<>();
        if (visitedSet.contains(start) == false) {
            visitedSet.add(start);
        }
        queue.add(start);
        while(!queue.isEmpty()) {
            Vertex<T> v = queue.remove();
            Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();
            List<VertexDistance<T>> vertList = adjList.get(v);
            for(int i = 0; i < vertList.size(); i++) {
                Vertex<T> curr = vertList.get(i).getVertex();
                if (visitedSet.contains(curr) == false) {
                    visitedSet.add(curr);
                    queue.add(curr);
                }
            }

        }
        return visitedSet;


    }


    private static <T> void dfsRecurse(Vertex<T> cv, Graph<T> graph, List<Vertex<T>> vs) {
        boolean tryAdd = vs.add(cv); // return true if it does not exist in the set, else false
        if (tryAdd) {
            Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();
            List<VertexDistance<T>> vertList = adjList.get(cv);
            for(int i = 0; i < vertList.size(); i++) {
                Vertex<T> curr = vertList.get(i).getVertex();
                if (vs.contains(curr) == false) {
                    dfsRecurse(curr, graph, vs);
                }
            }
            return;


        }
    }

    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        List<Vertex<T>> visitedSet = new ArrayList<> ();
        dfsRecurse(start, graph, visitedSet);
        return visitedSet;
    }

    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        List<Vertex<T>> visitedSetU = new ArrayList<> ();
        List<Vertex<T>> visitedSetV = new ArrayList<> (); // added second set
        Set<Edge<T>> mstSet = new HashSet<>();
        PriorityQueue<Edge<T>> pq = new PriorityQueue<>();

        List<Edge<T>> edgeList = new ArrayList<>();

        Set<Edge<T>> tmp = graph.getEdges();
        Set<Vertex<T>> vertSet = graph.getVertices();

        for (Edge<T> i : tmp) {
            edgeList.add(i);
        }






        for (int i = 0; i < edgeList.size(); i++) {
            if (edgeList.get(i).getU().equals(start)) {
                pq.add(edgeList.get(i));
                mstSet.add(edgeList.get(i));
            }
        }

        visitedSetU.add(start);

        while (!pq.isEmpty()) {

            Edge<T> curr = pq.poll();
            if (visitedSetV.contains(curr.getV()) == false) {
                visitedSetV.add(curr.getV());
                mstSet.add(curr);

                for (int i = 0; i< edgeList.size(); i++) {
                    if (edgeList.get(i).getU().equals(curr.getV())) { //&& visitedSet.contains(edgeList.get(i).getV()) == false) {
                        pq.add(edgeList.get(i));
                    }
                }

            }

            if (visitedSetU.contains(curr.getU()) == false) {
                visitedSetU.add(curr.getU());
                mstSet.add(curr);

                for (int i = 0; i< edgeList.size(); i++) {
                    if (edgeList.get(i).getV().equals(curr.getU())) { //&& visitedSet.contains(edgeList.get(i).getV()) == false) {
                        pq.add(edgeList.get(i));
                    }
                }

            }

        }



      
            return mstSet;
        
       

    }



}