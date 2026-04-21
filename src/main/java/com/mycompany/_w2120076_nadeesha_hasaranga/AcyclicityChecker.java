package com.mycompany._w2120076_nadeesha_hasaranga;

import java.util.*;

/**
 * Name: K. K. Nadeesha Hasaranga
 * ID: 20240675 / w2120076
 */

public class AcyclicityChecker {
    private final Graph graph;
    private boolean cyclic = false;
    private List<Integer> cycle = null;

    public AcyclicityChecker(Graph graph) {
        this.graph = graph;
    }

    public boolean check() {
        System.out.println("\n-------- Sink Elimination --------");

        int step = 1;

        while(true) {
            if(graph.isEmpty()) {
                System.out.println("\nAll vertices eliminated. Graph is ACYCLIC.");
                cyclic = false;
                return true;
            }

            if(!graph.hasSink()) {
                System.out.println("\nNo sink available but vertices remain. Graph is CYCLIC.");
                cyclic = true;
                findCycle();
                return false;
            }

            int sink = graph.removeSink();
            System.out.println("Step " + step + ": Removed sink --> vertex " + sink);
            step++;
        }
    }

    private void findCycle() {
        int n = graph.getNumVertices();
        int[] color = new int[n];
        int[] parent = new int[n];

        for(int i=0; i<n; i++) {
            parent[i] = -1;
        }

        for(int start=0; start<n; start++) {
            if(!graph.isRemoved(start) && color[start] == 0) {
                List<Integer> foundCycle = dfs(start, color, parent);
                if(foundCycle != null) {
                    cycle = foundCycle;
                    return;
                }
            }
        }
    }

    private List<Integer> dfs(int start, int[] color, int[] parent) {
        Stack<int[]> stack = new Stack<>();
        List<Integer> currentPath = new ArrayList<>();
        Map<Integer, Integer> pathIndex = new HashMap<>();

        stack.push(new int[] {start, 0});
        color[start] = 1;
        currentPath.add(start);
        pathIndex.put(start, 0);

        while(!stack.isEmpty()) {
            int[] frame = stack.peek();
            int v = frame[0];

            Integer[] neighbours = graph.getOutNeighbours(v)
                    .stream()
                    .filter(w -> !graph.isRemoved(w))
                    .toArray(Integer[]::new);

            if (frame[1] < neighbours.length) {
                int w = neighbours[frame[1]];
                frame[1]++;

                if (color[w] == 1) {
                    // Back edge found: w is on the current path -> cycle detected
                    return extractCycle(currentPath, w, v);
                }

                if (color[w] == 0) {
                    color[w] = 1;
                    parent[w] = v;
                    stack.push(new int[]{w, 0});
                    currentPath.add(w);
                    pathIndex.put(w, currentPath.size() - 1);
                }
            } else {
                // All neighbours processed, backtrack
                color[v] = 2;
                stack.pop();
                currentPath.remove(currentPath.size() - 1);
                pathIndex.remove(v);
            }
        }
        return null;
    }

    //pull out the cycle from the current path when we hit a back edge
    private List<Integer> extractCycle(List<Integer> path, int cycleStart, int cycleEnd) {
        List<Integer> result = new ArrayList<>();
        int startIdx = path.indexOf(cycleStart);
        for (int i = startIdx; i < path.size(); i++) {
            result.add(path.get(i));
        }
        result.add(cycleStart); // close the cycle
        return result;
    }


    // Prints the cycle found, if any.
    public void printCycle() {
        if (!cyclic) {
            System.out.println("Graph is acyclic - no cycle exists.");
            return;
        }
        if (cycle == null || cycle.isEmpty()) {
            System.out.println("Cycle exists but could not be extracted.");
            return;
        }

        System.out.print("\nCycle found: ");
        for (int i = 0; i < cycle.size(); i++) {
            System.out.print(cycle.get(i));
            if (i < cycle.size() - 1) System.out.print(" -> ");
        }
        System.out.println();
    }

    public boolean isCyclic() {
        return cyclic;
    }

    public List<Integer> getCycle() {
        return cycle;
    }
}
