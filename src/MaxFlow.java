/*
w1714910
2018162
Yasaswin Bandara
Source: https://steemit.com/programming/@drifter1/programming-java-graph-maximum-flow-algorithm-ford-fulkerson#:~:text=The%20main%20algorithm%20is%20the,from%20source%20to%20vertex%20and
 */
public class MaxFlow {
    //using Ford Fulkerson Algorithm finding the maximum flow
    public Double findMaxFlow(Graph graph, int source, int sink) {

        int verticesCount = graph.getVerticesCount();
        //setting the residual graph equal to parent graph
        Graph residualGraph = new Graph(verticesCount);//residual graph weights will be assigned to 0
        for (int i = 0; i < verticesCount; i++) {
            for (int j = 0; j < verticesCount; j++) {
                residualGraph.getGraph()[i][j] = graph.getGraph()[i][j];
            }
        }
        //parent[] array size will be initialized and in Breadth First Search it store the parent nodes of the visiting
        //vertices in this array
        int[] parent = new int[verticesCount];
        //maximum flow will be initialized to zero at the beginning and it will keep track about updating max flow
        double maximumFlow = 0;

        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();

        //this will loop till there is a path from source to sink it means as long as bfs method returns true this
        //loop will be executed (it means this will be executed to each traversal)
        while (breadthFirstSearch.bfs(residualGraph, source, sink, parent)) {
            //this will assign a maximum number to flowCapacity to initialize the variable because of this overflow will
            //-not happen
            double flowCapacity = Double.MAX_VALUE;

            //this will find the maximum weight that can be carried through the path allocated by bfs(or in parent[])
            //t will be equal to sink
            //this loop will be executed only when t is not equal to source because it's impossible
            //then here we initialize the parent vertex of vertex t to variable t from parent array
            for (int t = sink; t != source; t = parent[t]) {
                //here we initialize the parent vertex of vertex t to variable s from parent array
                int s = parent[t];
                //here this function will capture the minimum capacity to flowCapacity
                flowCapacity = Math.min(flowCapacity, residualGraph.getGraph()[s][t]);

                /*
                from this initializations with variable s and t we synchronise the path connectivity using parent
                array first we set sink vertex's parent value from the parent array to variable s then we initialize
                that value to a variable t when it loops next time t will be used as an element value of parent array
                that element value will contain the parent vertex which connect to the sink node like wise this will
                loop until source
                */
            }
            //updating the residual graph and reduce the weight on the forward edge by flowCapacity then increases
            //-the weight on backward edge by flowCapacity
            for (int t = sink; t != source; t = parent[t]) {
                int s = parent[t];
                residualGraph.getGraph()[s][t] -= flowCapacity;
                residualGraph.getGraph()[t][s] += flowCapacity;

                /*
                synchronisation will happen in the same sequence as mentioned in above and in the residual graph the
                capacity will be updated as if the flow is to forward weight will be reduced in the forward edge then if
                the flow is into backwards it will increase the flow capacity
                 */
            }
            //this will add flowCapacity to maximumFlow
            maximumFlow += flowCapacity;
        }
        //this will return the maximum flow
        return maximumFlow;
    }
}
