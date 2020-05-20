/*
w1714910
2018162
Yasaswin Bandara
Source: https://steemit.com/programming/@drifter1/programming-java-graph-maximum-flow-algorithm-ford-fulkerson#:~:text=The%20main%20algorithm%20is%20the,from%20source%20to%20vertex%20and
 */

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {

    //using breadth-first search we can find possible paths to sink
    public boolean bfs(Graph residualGraph, int source, int sink, int[] parent) {
        //this array will store true for visited vertices
        boolean[] visited = new boolean[residualGraph.getVerticesCount()];

        //here all the vertices will be marked as unvisited first
        for (int i = 0; i < residualGraph.getVerticesCount(); i++) {
            visited[i] = false;
        }

        //This queue will used to traverse the vertices
        Queue<Integer> queue = new LinkedList<Integer>();

        //putting starting/source vertex in the queue
        queue.add(source);
        //source vertex is marked as visited
        visited[source] = true;
        //because we don't have a parent for the first node we initialize -1 for first node
        parent[source] = -1;

        //searching for possible paths while queue is greater than zero
        while (!queue.isEmpty()) {
            //dequeue the front value and initializing element of the queue to v (queue will be updated through
            //-the loop and queue is FIFO)
            int v = queue.poll();
            //in this for loop we visit vertex v and visiting it's adjacent unvisited vertices
            for (Integer vElement : residualGraph.adjacentVertices(v)) {
                //in residualGraph when adjacent vertex has a weight and it's not marked as visited that particular node
                //-will be added to queue
                if ((residualGraph.getGraph()[v][vElement] > 0) && (!visited[vElement])) {
                    //unvisited adjacent vertices will be added to the queue and marked as visited
                    queue.add(vElement);
                    visited[vElement] = true;
                    //parent value of that added vertex will be saved to parent array
                    parent[vElement] = v;
                }
            }
        }
        //return true/false after queue is empty and if path has approached to sink it will return true if not it will
        //-return false by the way until it return true the while loop in MaxFlow will be executed
        return visited[sink];
    }
}
