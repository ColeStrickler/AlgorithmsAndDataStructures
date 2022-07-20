import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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
}