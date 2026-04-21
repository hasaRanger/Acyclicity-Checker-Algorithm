/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany._w2120076_nadeesha_hasaranga;

import java.util.*;

/**
 * Name: K. K. Nadeesha Hasaranga
 * ID: 20240675 / w2120076
 */

public class Main {
//     Set up the base path for the benchmark files, requiring the user to specify only,
//     - correct directory (acyclic/cyclic)
//     - test file
    private static final String BASE_PATH = "src/benchmarks/";

    public static void main(String[] args) {
        String filename;

        if(args.length == 1) {
            filename = args[0];
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.println("Base Path: " + BASE_PATH);
            System.out.print("Enter the rest of the path to the graph file (acyclic/a_40_0.txt): ");
            String input = sc.nextLine().trim();
            filename = BASE_PATH + input;
            sc.close();
        }

        System.out.println("Loading file " + filename);

        try {
            long startTime = System.nanoTime();

            Graph graph = Parser.parse(filename);
            AcyclicityChecker checker = new AcyclicityChecker(graph);
            boolean acyclic = checker.check();

            long endTime = System.nanoTime();
            long elapsed = endTime - startTime;
            System.out.println("\nTime taken: " + elapsed + " ns (" + elapsed / 1_000_000 + " ms)");

            System.out.println("\nResult: " + (acyclic ? "YES - the graph is acyclic." : "NO - the graph is cyclic."));

            if (!acyclic) {
                checker.printCycle();
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
