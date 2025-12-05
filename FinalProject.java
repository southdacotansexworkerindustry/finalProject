package com.mycompany.finalproject;

import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FinalProject {
    /*
    Project introduction:
    
    Random Network: The nNetwork class implements random graph, also known as G(N, p), where each possible pair of nodes is connected with probability p.
    The class begins by generating N agents, each assigned a random “power” value between 0 and 1. These power values determine whether a node is eligible to form edges.
    During the connection-generation phase, the algorithm iterates over all pairs of agents and forms a connection between two nodes a and b whenever both agents 
    have a power value greater than p. This creates a probabilistic network where connectivity emerges from the interaction of randomness and the threshold parameter 
    p. Because every potential pair is considered, the resulting graph captures the stochastic behavior characteristic of random networks: some nodes become highly 
    connected, others sparsely connected, and no geometric structure constrains the layout. The final output is a list of unordered pairs representing edges, which
    can be written to a text file for visualization or analysis.
    
    Lattice: The lattice class builds a two dimensional grid-like network where each node connects to four neighbors: up, down, left, and right.
    The grid shape is chosen so that the number of rows is close to the square root of N, and the number of columns is set to fit all remaining nodes. 
    Each node is assigned a grid position and then linked to the four nodes around it. To avoid edge nodes having fewer neighbors, the grid wraps around like a torus,
    meaning the top connects to the bottom and the left connects to the right. This guarantees that every node has exactly four connections.
    The result is a very regular structure that is useful when modeling systems with physical or spatial layout.
    
    The network1 class defines a small, custom-built network with exactly 12 agents. Unlike the random and lattice networks, this one does not follow a formula.
    Instead, the edges are all written out by hand. Every agent is created with a power value of 1.0, and the program connects specific pairs according to a predefined list.
    This type of network is helpful for testing and debugging, because its structure never changes. It lets you study how your algorithms behave on a known pattern, check for isolated nodes, 
    and compare results between different topologies.
    
    The main method acts as the central controller of the entire program. It begins by prompting the user to choose one of three network types: a random network created by the nNetwork class,
    a structured two-dimensional lattice created by the lattice class, or a predefined, manually designed network created by the network1 class. Depending on the number the user enters,
    the method then calls the appropriate function: runRandomNetwork, runLatticeNetwork, or runNetwork1. Each of these functions gathers any additional required input,
    generates the full set of agents and their connections, and writes the resulting list of edges to an output text file. If the user enters an invalid option,
    the program displays an error message and ends. Overall, the main method manages the flow of user interaction, directs the program to the correct network model, 
    and keeps the code organized by separating the logic for each type of network.
    
    */

    //user chooses network type to generate
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Do you want a random network [0], lattice network [1] or network1 [2]?");
        int c = input.nextInt();

        if (c == 0) {
            runRandomNetwork(input);
        } 
        else if (c == 1) {
            runLatticeNetwork(input);
        } 
        else if (c == 2){
            runNetwork1(input);
        }
        else {
            System.out.println("Invalid option. Restart the program.");
        }
    }

    // Handles creation and output of a random G(N, p) network
    private static void runRandomNetwork(Scanner input) {

        System.out.println("Enter the numebr of nodes [10–1000]:");
        int N = input.nextInt();

        System.out.println("Enter probability p of connection [1/N – 1]:");
        double p = input.nextDouble();

        // generate agents and connections
        nNetwork network = new nNetwork(N, p);
        LinkedList<Agent> agents = network.genAgents();
        LinkedList<Agent[]> connections = network.connectAgents(agents);

        String fileName = "connections_random.txt";

        // write to file
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {

            writer.println("Random Network G(N,p)");
            writer.println("N = " + N + ", p = " + p);
            writer.println("Edges = " + connections.size());

            // output edges
            for (Agent[] pair : connections) {
                writer.println(pair[0].getNum() + " " + pair[1].getNum());
            }

        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }

        System.out.println("Saved random network to " + fileName);
    }


    // Handles creation of a 2D toroidal lattice where every node has 4 neighbors
    private static void runLatticeNetwork(Scanner input) {

        System.out.println("Please input the number of nodes you want in the lattice (N = [9, 10000]):");
        int N = input.nextInt();

        // range check
        if (N < 9 || N > 10000) {
            System.out.println("Invalid N. Must be between 9 and 10000.");
            return;
        }

        // generate lattice edges
        lattice lat = new lattice(N);
        LinkedList<Agent[]> connections = lat.genLatticeAgents();

        String fileName = "lattice_connections.txt";

        // write lattice edges to file
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {

            for (Agent[] pair : connections) {
                writer.println(pair[0].getNum() + " " + pair[1].getNum());
            }

            System.out.println("Lattice network successfully written to " + fileName);

        } catch (IOException e) {
            System.err.println("Error writing lattice file: " + e.getMessage());
        }
    }


    // Handles creation of predefined Network1 (N = 12)
    private static void runNetwork1(Scanner input) {

        System.out.println("Running network1");

        // generate agents and edges for this hardcoded network
        network1 net = new network1(12);
        LinkedList<Agent> agents = net.genAgents();
        LinkedList<Agent[]> connections = net.connectAgents(agents);

        String fileName = "connections_network1.txt";

        // write output
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {

            writer.println("Predefined Network #1");
            writer.println("N = " + 12);
            writer.println("Edges = " + connections.size());

            // Write all normal edges
            for (Agent[] pair : connections) {
                writer.println(pair[0].getNum() + " " + pair[1].getNum());
            }

            // Find isolated nodes
            LinkedList<Integer> isolated = net.findIsolatedNodes(connections, 1);

            // Write isolated nodes as "i -1"
            for (int x : isolated) {
                writer.println(x + " -1");
            }

        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }

        System.out.println("Saved predefined network to " + fileName);
    }

}