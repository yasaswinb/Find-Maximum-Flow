/*
w1714910
2018162
Yasaswin Bandara
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Graph {
    private int verticesCount;
    private int[][] graph;

    public int getVerticesCount() {
        return verticesCount;
    }

    public void setVerticesCount(int verticesCount) {
        this.verticesCount = verticesCount;
    }

    public int[][] getGraph() {
        return graph;
    }

    public void setGraph(int[][] graph) {
        this.graph = graph;
    }

    public Graph() {
    }

    //initialize all weights to zero because when we are adding weights sometimes all the weights are not been assigned
    //-in residual graph
    public Graph(int verticesCount) {
        this.verticesCount = verticesCount;
        graph = new int[verticesCount][verticesCount];
        for (int i = 0; i < verticesCount; i++) {
            for (int j = 0; j < verticesCount; j++) {
                graph[i][j] = 0;
            }
        }
    }

    //printing the adjacent graph
    public void printGraph() {
        for (int i = 0; i < verticesCount; i++) {
            for (int j = 0; j < verticesCount; j++) {
                System.out.print("[" + graph[i][j] + "]" + " ");
            }
            System.out.println("  ");
        }
    }

    //adding edges
    public void addEdge(int vertex1, int vertex2, int weight) {
        graph[vertex1][vertex2] = weight;
    }

    //adding new edges
    public void addNewEdge(int vertex1, int vertex2, int weight) {
        //this will check there is an existing edge
        if (!isConnected(vertex1, vertex2)) {
            graph[vertex1][vertex2] = weight;
            System.out.println("Edge added successfully\n");
        }
        //if there is an existing edge this will be prompted
        else {
            System.out.println("Already " + vertex1 + " to " + vertex2 + " vertex is assigned try again\n");
        }
    }

    //adding generated edges
    public void generateEdges(int vertexCount, int edgesCount, int source, int sink) {
        Random random = new Random();
        int i;
        int j;
        if ((edgesCount <= (vertexCount * (vertexCount - 1))) && (vertexCount > 1)) {
            int count = 1;
            MaxFlow maxFlow = new MaxFlow();
            Graph genGraph = new Graph(vertexCount);
            while ((count < (edgesCount))) {
                while (maxFlow.findMaxFlow(genGraph, source, sink) < 0) {
                    i = (int) (Math.random() * (vertexCount - 2) + 1);
                    j = (int) (Math.random() * (vertexCount - 2) + 1);
                    if ((i != sink) && (source != j) && (j != sink) && (i != source)) {
                        graph[source][i] = (int) (Math.random() * 60 + 1);
                        genGraph.getGraph()[i][j] = getGraph()[source][i];
                        graph[sink][j] = (int) (Math.random() * 60 + 1);
                        genGraph.getGraph()[i][j] = getGraph()[sink][j];
                        count++;
                    }
                }
                i = random.nextInt(vertexCount);
                j = random.nextInt(vertexCount);
                while ((i != j) && (!isConnected(i, j))) {
                    graph[i][j] = (int) (Math.random() * 60 + 1);
                    genGraph.getGraph()[i][j] = getGraph()[i][j];
                    count++;
                }
            }
        } else {
            System.out.println("Please enter edges correctly");
            System.exit(-1);
        }
    }

    //removing edge
    public void removeEdge(int vertex1, int vertex2) {
        //if there is an existing edge this will executed
        if (isConnected(vertex1, vertex2)) {
            graph[vertex1][vertex2] = 0;
            System.out.println("Edge deleted successfully\n");
        }
        //if there is no existing edge this will be prompted
        else {
            System.out.println("This edge does not exist try again\n");
        }
    }

    //modifying edge
    public void modifyEdge(int vertex1, int vertex2, int weight) {
        //this will be executed if there is an existing edge
        if (isConnected(vertex1, vertex2)) {
            graph[vertex1][vertex2] = weight;
            System.out.println("Edge modified successfully\n");
        }
        //if there is no existing edge this will be prompted
        else {
            System.out.println("Entered edge does not exist try again\n");
        }
    }

    //this will return true when particular vertices are connected
    public boolean isConnected(int vertex1, int vertex2) {
        if (graph[vertex1][vertex2] != 0) {
            return true;
        }
        return false;
    }

    //this will keep track of all adjacent vertices of a particular vertex
    public List<Integer> adjacentVertices(int vertex) {
        //this arrayList will contain adjacent vertices/vertex of a visiting vertex
        List<Integer> adjacentVertices = new ArrayList<Integer>();
        for (int i = 0; i < verticesCount; i++) {
            if (isConnected(vertex, i)) {
                adjacentVertices.add(i);
            }
        }
        return adjacentVertices;
    }

    //this method will used to read the four data sets
    public void dataset(String fileName) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));

            MaxFlow maxFlow = new MaxFlow();

            String fStr = reader.readLine();
            String[] fLine = fStr.split(" ");
            int verticesCount = Integer.parseInt(fLine[0]);
            int source = Integer.parseInt(fLine[1]);
            int sink = Integer.parseInt(fLine[2]);

            Graph graph = new Graph(verticesCount);
            String str;
            while ((str = reader.readLine()) != null) {
                String[] grp = str.split(" ");
                int vertex1 = Integer.parseInt(grp[0]);
                int vertex2 = Integer.parseInt(grp[1]);
                int weight = Integer.parseInt(grp[2]);
                graph.addEdge(vertex1, vertex2, weight);
            }
            double start = (double) System.currentTimeMillis();
            System.out.println("Maximum flow to path from " + source + " to " + sink + " is " + maxFlow.findMaxFlow(graph, source, sink));
            double finish = (double) System.currentTimeMillis();
            System.out.println("Elapsed time = " + ((finish - start)) + " milliseconds");
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
