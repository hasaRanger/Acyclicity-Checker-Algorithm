/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany._w2120076_nadeesha_hasaranga;

import java.util.*;

/**
 * Name: K. K. Nadeesha Hasaranga
 * ID: 20240675 / w2120076
 */

public class Graph {
    private final int numVertices;
    private final Set<Integer>[] outNeighbours; // who does v point TO?
    private final Set<Integer>[] inNeighbours;  // who points into v?
    private final int[] outDegree;              // how many outgoing edges from v?
    private final boolean[] removed;            // has this vertex been eliminated?
    private final Queue<Integer> sinkQueue;     // current sinks
    private int remainingVertices;              // how many vertices are still in the graph?

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        this.remainingVertices = numVertices;
        this.outNeighbours = new HashSet[numVertices];
        this.inNeighbours = new HashSet[numVertices];
        this.outDegree = new int[numVertices];
        this.removed = new boolean[numVertices];
        this.sinkQueue = new LinkedList<>();

        for (int i=0; i<numVertices; i++) {
            outNeighbours[i] = new HashSet<>();
            inNeighbours[i] = new HashSet<>();
        }
    }

    public int getRemainingVertices() {
        return remainingVertices;
    }

    public Set<Integer>[] getInNeighbours() {
        return inNeighbours;
    }

    public Set<Integer> getInNeighbours(int v) {
        return inNeighbours[v];
    }

    public Set<Integer>[] getOutNeighbours() {
        return outNeighbours;
    }

    public Set<Integer> getOutNeighbours(int v) {
        return outNeighbours[v];
    }

    public int[] getOutDegree() {
        return outDegree;
    }

    public int getOutDegree(int v) {
        return outDegree[v];
    }

    public int getNumVertices() {
        return numVertices;
    }

    public void addEdge(int u, int v) {
        outNeighbours[u].add(v);
        inNeighbours[v].add(u);
        outDegree[u]++;
    }

    public void initialiseSinks() {
        for (int i=0; i < numVertices; i++) {
            if(outDegree[i] == 0) {
                sinkQueue.add(i);
            }
        }
    }

    public boolean hasSink() {
        return !sinkQueue.isEmpty();
    }

    public int removeSink() {
        int sink = sinkQueue.poll();
        removed[sink] = true;
        remainingVertices--;

        for (int predecessor : inNeighbours[sink]) {
            if(!removed[predecessor]) {
                outNeighbours[predecessor].remove(sink);
                outDegree[predecessor]--;
                if(outDegree[predecessor] == 0) {
                    sinkQueue.add(predecessor);
                }
            }
        }
        return sink;
    }

    public boolean isEmpty() {
        return remainingVertices == 0;
    }

    public boolean isRemoved(int v) {
        return removed[v];
    }

}
