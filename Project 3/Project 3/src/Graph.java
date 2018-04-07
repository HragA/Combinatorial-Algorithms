/*
* Name: Hrag Ayvazian
* Date: 12/7/2017
* COMP 482
* Project #3
* */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Graph {
    //------------------------------------------------------
    private ArrayList<EdgeNode>[] adjList;
    private int nVertices;
    private int nEdges;
    private String fileName;

    public static void main (String[] args)
    {
        //A
        System.out.println("Instructor Testcase A");
        System.out.println("\nDijkstra Shortest Paths");
        Graph g1 = new Graph("inputA.txt");
        g1.printGraph();
        int start = 1;

        SPPacket spp = g1.dijkstraShortestPaths(start);
        System.out.println("\nPrint shortest paths from start vertex  = " + start);
        g1.printShortestPaths( spp );


        if( g1.isStronglyConnected())
            System.out.println( "\nGraph is strongly connected");
        else
            System.out.println( "\nGraph is not strongly connected");

        //B
        System.out.println("Instructor Testcase B");
        System.out.println("\nBellman Ford Shortest Paths");
        g1 = new Graph("inputB.txt");
        g1.printGraph();
        start = 0;

        spp = g1.bellmanFordShortestPaths(start);
        if( spp != null)
        {
            System.out.println("\nPrint shortest paths from start vertex  = " + start);
            g1.printShortestPaths( spp );
        }
        else
            System.out.println("Graph has a negative cycle");

        //C
        System.out.println("Instructor Testcase C");
        System.out.println("\nBFS Shortest paths Shortest Paths");
        g1 = new Graph("inputC.txt");
        g1.printGraph();
        start = 5;

        spp = g1.bfsShortestPaths(start);
        System.out.println("\nPrint shortest paths from start vertex  = " + start);
        g1.printShortestPaths( spp );

        //D
        System.out.println("Instructor Testcase D");
        System.out.println("\nBellman Ford Shortest Paths");
        g1 = new Graph("inputD.txt");
        g1.printGraph();
        start = 0;

        spp = g1.bellmanFordShortestPaths(start);
        if( spp != null)
        {
            System.out.println("\nPrint shortest paths from start vertex  = " + start);
            g1.printShortestPaths( spp );
        }
        else
            System.out.println("\nGraph has a negative cycle");

        if( g1.isStronglyConnected())
            System.out.println( "\nGraph is strongly connected");
        else
            System.out.println( "\nGraph is not strongly connected");

    }  //end main

    /******************Constructor**********************/
    public Graph(String inputFileName) {
        fileName = inputFileName;
        try {
            Scanner input = new Scanner(new File(fileName));
            nVertices = input.nextInt();
            adjList = new ArrayList[nVertices];
            nEdges = 0;
            for(int i = 0; i < nVertices; i++) {
                adjList[i] = new ArrayList<EdgeNode>();
            }
            while(input.hasNextInt()) {
                int v1 = input.nextInt();
                adjList[v1].add(new EdgeNode(v1,input.nextInt(),input.nextInt()));
                nEdges++;
            }
        }
        catch(FileNotFoundException e) {}
    }

    /******************Print graph method***************/

    public void printGraph() {
        System.out.println("Graph: nVertices = " + nVertices + " nEdges = " + nEdges);
        System.out.println("Adjacency Lists");
        for(int i = 0; i < nVertices; i++) {
            System.out.print("v = " + i + " ");
            System.out.println(adjList[i]);
        }
    }

    /*******************BFS Shortest paths******************/
    public SPPacket bfsShortestPaths(int start) {
        int[] parent = new int[nVertices];
        int[] distance = new int[nVertices];
        boolean[] visited = new boolean[nVertices];
        int l = 0;
        Queue<Integer> visit = new PriorityQueue<>();
        for(int i = 0; i < nVertices; i++) {
            parent[i] = -1;
            distance[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        visited[start] = true;
        distance[start] = l;
        visit.add(start);
        while(!visit.isEmpty()) {
            int current = visit.remove();
            int numAdj = adjList[current].size();
            for(int i = 0; i < numAdj; i++) {
                int dest = adjList[current].get(i).vertex2;
                if(!visited[dest]) {
                    visit.add(dest);
                    visited[dest] = true;
                    distance[dest] = distance[current] + 1;
                    parent[dest] = current;
                }
            }
        }
        return new SPPacket(start, distance, parent);
    }

    /********************Dijkstra's Shortest Path Algorithm*****/
    public SPPacket dijkstraShortestPaths(int start) {
        int[] parent = new int[nVertices];
        int[] distance = new int[nVertices];
        boolean[] visited = new boolean[nVertices];
        Queue<Integer> visit = new PriorityQueue<>();
        for(int i = 0; i < nVertices; i++) {
            parent[i] = -1;
            distance[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        visited[start] = true;
        distance[start] = 0;
        visit.add(start);
        while(!visit.isEmpty()) {
            int current = visit.remove();
            int numAdj = adjList[current].size();
            for(int i = 0; i < numAdj; i++) {
                int dest = adjList[current].get(i).vertex2;
                if(!visited[dest]) {
                    visit.add(dest);
                    visited[dest] = true;
                }
                if(distance[current] + adjList[current].get(i).weight < distance[dest]) {
                    distance[dest] = distance[current] + adjList[current].get(i).weight;
                    parent[dest] = current;
                }
            }
        }
        return new SPPacket(start, distance, parent);
    }

    /********************Bellman Ford Shortest Paths***************/
    public SPPacket bellmanFordShortestPaths(int start){
        int[] p = new int[nVertices];
        int[] d = new int[nVertices];
        EdgeNode[] edges = new EdgeNode[nEdges];
        int k = 0;
        for(int i = 0; i < nVertices; i++) {
            p[i] = -1;
            d[i] = Integer.MAX_VALUE;
            for(int j = 0; j < adjList[i].size(); j++) {
                edges[k] = adjList[i].get(j);
                k++;
            }
        }
        d[start] = 0;
        for(int i = 0; i < nVertices - 1; i++) {
            for(int j = 0; j < nEdges; j++) {
                if(d[edges[j].vertex1] + edges[j].weight < d[edges[j].vertex2]) {
                    d[edges[j].vertex2] = d[edges[j].vertex1] + edges[j].weight;
                    p[edges[j].vertex2] = edges[j].vertex1;
                }
            }
        }
        for(int j = 0; j < nEdges; j++) {
            if((long)d[edges[j].vertex1] + edges[j].weight < (long)d[edges[j].vertex2]) {
                return null;
            }
        }
        return new SPPacket(start, d, p);
    }

    /***********************Prints shortest paths*************************/
    public void printShortestPaths(SPPacket spp) {
        System.out.println(spp);
    }

    /*****************isStronglyConnected***************************/
    public boolean isStronglyConnected() {
        int[] p = new int[nVertices];
        for(int i = 0; i < nVertices; i++) {
            for(int j = 0; j < nVertices; j++) {
                p[j] = -1;
            }
            dfs(i,p);
            for(int j = 0; j < nVertices; j++) {
                if(p[j] == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    private void dfs(int s, int[] p) {
        for(int i = 0; i < adjList[s].size(); i++) {
            if(p[adjList[s].get(i).vertex2] == -1) {
                p[adjList[s].get(i).vertex2] = s;
                dfs(adjList[s].get(i).vertex2, p);
            }
        }
    }
}//end Graph class

/*******************************************/
class EdgeNode {
    int vertex1;
    int vertex2;
    int weight;
    public EdgeNode(int v1, int v2, int w) {
        vertex1 = v1;
        vertex2 = v2;
        weight = w;
    }
    public String toString() {
        return "(" + vertex1 + "," + vertex2 + "," + weight + ")";
    }

}

/***********************************************/
class SPPacket {
    int[] d;//distance array
    int[] parent;//parent path array
    int source;//source vertex
    public SPPacket(int start, int[] dist, int[] pp) {
        source = start;
        d = dist;
        parent = pp;
    }
    public int[] getDistance() {
        return d;
    }

    public int[] getParent() {
        return parent;
    }

    public int getSource() {
        return source;
    }
    public String toString() {
        String str = "";
        str += ("Shortest Paths from vertex " + source + " to vertex\n");
        for(int i = 0; i < parent.length; i++) {
            str += (i + ": [");
            String s = "";
            int p = i;
            if(p != source) {
                while(parent[p] != -1) {
                    s = parent[p] + "," + s;
                    p = parent[p];
                }
            }
            str += s;
            str += i;
            str += ("] Path Weight = " + d[i] + "\n");
        }
        return str;
    }
}