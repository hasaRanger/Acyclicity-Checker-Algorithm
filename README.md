# Acyclicity Checker

Java implementation to detect whether a directed graph is acyclic using sink elimination and DFS cycle detection.

## Features
- Sink elimination algorithm
- Cycle detection with DFS
- Benchmark testing support

## Project Structure
- Graph.java → Graph representation
- Parser.java → Input parsing
- AcyclicityChecker.java → Core algorithm
- Main.java → Execution entry

## How to Run
javac *.java
java Main

## Input Format
<number_of_vertices><br>
u v<br>
u v

## Example
5<br>
0 1<br>
1 2<br>
2 3<br>