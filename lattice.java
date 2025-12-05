/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalproject;
import java.util.*;

public class lattice {

    private int N;
    private int rows;
    private int cols;

    public lattice(int N){
        this.N = N;

        // Construct the most square-like rectangle
        this.rows = (int)Math.floor(Math.sqrt(N));
        this.cols = (int)Math.ceil((double)N / rows);
    }

    public int getN(){
        return N;
    }

    // Generate 4-neighbor toroidal lattice
    public LinkedList<Agent[]> genLatticeAgents() {

        LinkedList<Agent[]> connections = new LinkedList<>();

        // Create N agents (power is irrelevant for lattice)
        Agent[] agents = new Agent[N];
        for(int i = 0; i < N; i++){
            agents[i] = new Agent(i, 0.5);
        }

        // Build connections
        for(int i = 0; i < N; i++){

            int r = i / cols;
            int c = i % cols;

            // Wrapped neighbors
            int up_r    = (r - 1 + rows) % rows;
            int down_r  = (r + 1) % rows;
            int left_c  = (c - 1 + cols) % cols;
            int right_c = (c + 1) % cols;

            int up    = up_r * cols + c;
            int down  = down_r * cols + c;
            int left  = r * cols + left_c;
            int right = r * cols + right_c;

            // Fix wrapping beyond N (in incomplete last row)
            if (up >= N)    up    %= N;
            if (down >= N)  down  %= N;
            if (left >= N)  left  %= N;
            if (right >= N) right %= N;

            // Add the 4 neighbors as separate connections
            connections.add(new Agent[]{agents[i], agents[up]});
            connections.add(new Agent[]{agents[i], agents[down]});
            connections.add(new Agent[]{agents[i], agents[left]});
            connections.add(new Agent[]{agents[i], agents[right]});
        }

        return connections;
    }
}