/*
w1714910
2018162
Yasaswin Bandara
 */

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        //this is to get inputs from user
        Scanner sc = new Scanner(System.in);
        //this exit variable will be used to get the input from user
        String exit;
        //these boolean variables will be used to function switches
        boolean rMenu = true;
        boolean r1Menu = true;
        //until rMenu is true this loop will work
        while (rMenu) {
            //MaxFlow object will be created
            MaxFlow maxFlow = new MaxFlow();
            //first we ask user to enter a graph
            System.out.println("To add a directed graph manually press 1");
            System.out.println("To import a directed graph using a file press 2");
            System.out.println("To generate a random directed graph press 3");
            int expression = sc.nextInt();
            //we use a switch to workout users preferences
            switch (expression) {
                //in adding graph process we ask user to enter vertices count, weight counts, source and sink
                case 1:
                    System.out.println("Enter count of vertices/nodes ");
                    int vCount = sc.nextInt();
                    Graph graph = new Graph(vCount);
                    System.out.println("Enter count of weights/edges ");
                    int wCount = sc.nextInt();

                    System.out.println("Enter source");
                    int source = sc.nextInt();

                    System.out.println("Enter sink");
                    int sink = sc.nextInt();

                    //this loop will get the graph initialized
                    for (int i = 0; i < wCount; i++) {
                        System.out.println("Enter starting vertex");
                        int vertex1 = sc.nextInt();
                        System.out.println("Enter ending vertex");
                        int vertex2 = sc.nextInt();
                        System.out.println("Enter weight/edge");
                        int weight = sc.nextInt();
                        graph.addEdge(vertex1, vertex2, weight);
                    }
                    //this sub menu will process after user entered a graph
                    while (r1Menu) {
                        //ask users preference
                        graph.printGraph();
                        System.out.println("\nTo find the max flow press 1");
                        System.out.println("To delete a edge press 2");
                        System.out.println("To add a edge press 3");
                        System.out.println("To modify a edge press 4");
                        expression = sc.nextInt();
                        //this switch will be used to process above preferences
                        switch (expression) {
                            //here maximum flow will be prompted and asked to exit or not after task is completed
                            case 1:
                                System.out.println("Maximum flow to path from " + source + " to " + sink + " is " + maxFlow.findMaxFlow(graph, source, sink) + "\n");
                                System.out.println("Do you want to exit press y/n");
                                exit = sc.next();
                                if (exit.equals("y")) {
                                    r1Menu = false;
                                    rMenu = false;
                                } else if (exit.equals("n")) {
                                    r1Menu = true;
                                }
                                break;
                            //here we remove an existing edge according to the users preference and asked to exit or not after task is completed
                            case 2:
                                System.out.println("Enter starting vertex");
                                int vertex1 = sc.nextInt();
                                System.out.println("Enter ending vertex2");
                                int vertex2 = sc.nextInt();
                                graph.removeEdge(vertex1, vertex2);
                                System.out.println("Do you want to exit press y/n");
                                exit = sc.next();
                                if (exit.equals("y")) {
                                    r1Menu = false;
                                    rMenu = false;
                                } else if (exit.equals("n")) {
                                    r1Menu = true;
                                }
                                break;
                            //here we add non existing edge and asked to exit or not after task is completed
                            case 3:
                                System.out.println("Enter starting vertex");
                                vertex1 = sc.nextInt();
                                System.out.println("Enter ending vertex2");
                                vertex2 = sc.nextInt();
                                System.out.println("Enter weight");
                                int weight = sc.nextInt();
                                graph.addNewEdge(vertex1, vertex2, weight);
                                System.out.println("Do you want to exit press y/n");
                                exit = sc.next();
                                if (exit.equals("y")) {
                                    r1Menu = false;
                                    rMenu = false;
                                } else if (exit.equals("n")) {
                                    r1Menu = true;
                                }
                                break;
                            //here we modify existing edges and asked to exit or not after task is completed
                            case 4:
                                System.out.println("Enter starting vertex");
                                vertex1 = sc.nextInt();
                                System.out.println("Enter ending vertex2");
                                vertex2 = sc.nextInt();
                                System.out.println("Enter weight");
                                weight = sc.nextInt();
                                graph.modifyEdge(vertex1, vertex2, weight);
                                System.out.println("Do you want to exit press y/n");
                                exit = sc.next();
                                if (exit.equals("y")) {
                                    r1Menu = false;
                                    rMenu = false;
                                } else if (exit.equals("n")) {
                                    r1Menu = true;
                                }
                                break;
                            default:
                                System.out.println("Error");
                        }
                    }
                    break;
                //reading from file
                case 2:
                    System.out.println("Enter dataset file name with extension");
                    String fileName = sc.next();
                    Graph graphFile = new Graph();
                    graphFile.dataset(fileName);
                    System.out.println("Do you want to exit press y/n");
                    exit = sc.next();
                    if (exit.equals("y")) {
                        rMenu = false;
                    } else if (exit.equals("n")) {
                        rMenu = true;
                    }
                    break;
                //creating a random network flow and calculating max flow
                case 3:
                    System.out.println("Enter vertex count");
                    vCount = sc.nextInt();
                    Graph graphGenerated = new Graph(vCount);
                    System.out.println("Enter edges count (Please enter edges count greater than comparing to number of vertices)");
                    int edgesCount = sc.nextInt();
                    System.out.println("Enter source");
                    source = sc.nextInt();
                    System.out.println("Enter sink");
                    sink = sc.nextInt();
                    graphGenerated.generateEdges(vCount, edgesCount, source, sink);
                    double start = (double) System.currentTimeMillis();
                    System.out.println("Maximum flow to path from " + source + " to " + sink + " is " + maxFlow.findMaxFlow(graphGenerated, source, sink) + "\n");
                    double finish = (double) System.currentTimeMillis();
                    System.out.println("Elapsed time = " + ((finish - start)) + " millisecond");
                    System.out.println("Do you want to exit press y/n");
                    exit = sc.next();
                    if (exit.equals("y")) {
                        rMenu = false;
                    } else if (exit.equals("n")) {
                        rMenu = true;
                    }
                    break;
                default:
                    System.out.println("Error");
            }
        }

/*
        Graph graphFile = new Graph();
        graphFile.dataset("dataset.txt");
*/
/*
        //this part can be used to hard code
        MaxFlow maxFlow = new MaxFlow();
        Graph graph = new Graph(6);
        graph.addEdge(0,1,10);
        graph.addEdge(1,3,5);
        graph.addEdge(3,5,3);
        graph.addEdge(0,2,8);
        graph.addEdge(2,4,10);
        graph.addEdge(4,5,14);
        graph.addEdge(3,2,7);
        graph.addEdge(1,2,5);
        graph.addEdge(2,1,4);
        graph.addEdge(3,4,6);
        graph.addEdge(4,3,10);

        graph.printGraph();
        System.out.println(" ");
        double start = (double) System.currentTimeMillis();
        System.out.println("Maximum flow to path from " + 0 + " to " + 5 + " is " + maxFlow.findMaxFlow(graph, 0, 5));
        double finish = (double) System.currentTimeMillis();
        System.out.println("Elapsed time = " +((finish-start)) + " millisecond/s");
*/
    }
}
