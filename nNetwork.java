/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalproject;
import java.util.*;
/**
 *
 * @author nerubaya
 */
public class nNetwork {

    // number of agents in the network
    private int N;

    // power threshold for connections
    private double p;

    // random generator for power values
    private static final Random RNG = new Random();

    // constructor
    public nNetwork(int N, double p){
        this.N = N;
        this.p = p;
    }

    // returns number of agents
    public int getN(){
        return N;
    }

    // returns connection threshold
    public double getP(){
        return p;
    }

    // generates a random power value
    public double genPower(){
        return RNG.nextDouble();
    }

    // creates N agents with random power
    public LinkedList<Agent> genAgents(){
        LinkedList<Agent> agents = new LinkedList<>();

        // assign ID and power to each agent
        for(int i = 0; i < N; i++){
            double d = genPower();
            Agent x = new Agent(i, d);
            agents.add(x);
        }

        return agents;
    }

    // generates connections between all agent pairs whose power > p
    public LinkedList<Agent[]> connectAgents(LinkedList<Agent> agents){
        LinkedList<Agent[]> connections = new LinkedList<>();

        // iterate through all possible ordered pairs
        for(int i = 0; i < N; i++){
            Agent a = agents.get(i);

            for(int j = 0; j < N; j++){
                Agent b = agents.get(j);

                // create connection if both powers exceed threshold
                if(a.getPower() > p && b.getPower() > p){
                    Agent[] connection = new Agent[]{a, b};
                    connections.add(connection);
                }
            }
        }

        return connections;
    }
}
