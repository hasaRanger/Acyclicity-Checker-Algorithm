package com.mycompany._w2120076_nadeesha_hasaranga;

import java.util.*;
import java.io.*;

/**
 * Name: K. K. Nadeesha Hasaranga
 * ID: 20240675 / w2120076
 */

public class Parser {
    public static Graph parse(String filename) throws FileNotFoundException {
        File file = new File(filename);
        if(!file.exists()) {
            throw new FileNotFoundException("Input file not found: " + filename);
        }

        Scanner sc = new Scanner(file);

        if(!sc.hasNextInt()) {
            sc.close();
            throw new IllegalArgumentException("File is empty or missing vertex count: " + filename);
        }
        
        int numVertices = sc.nextInt();
        if(numVertices <= 0) {
            throw new IllegalArgumentException("Vertex count must be positive, got: " + numVertices);
        }
        
        Graph graph = new Graph(numVertices);
        int edgeCount = 0;
        
        while(sc.hasNextInt()) {
            int u = sc.nextInt();
            int v =sc.nextInt();
            
            if(u<0 || u >= numVertices || v<0 || v >= numVertices) {
                sc.close();
                throw new IllegalArgumentException("Edge (" + u + " -> " + v
                        + ") references a vertex outside range [0, " + (numVertices - 1) + "]");
            }
            
            graph.addEdge(u, v);
            edgeCount++;
        }
        sc.close();
        
        graph.initialiseSinks();

        System.out.println("Loaded graph: " + " vertices - " + numVertices + ", edges - " + edgeCount);
        return graph;
    }
}
