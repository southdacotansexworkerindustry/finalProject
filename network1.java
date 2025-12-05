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
public class network1 {
    private int N;
    
    public network1(int N){
        this.N = 12;
    }
    
    public int getN(){
        return N;
    }
    
    public LinkedList<Agent> genAgents(){
        LinkedList<Agent> agents = new LinkedList<>();
        for(int i = 0; i < N; i++){
            Agent x = new Agent(i, 1.0);
            agents.add(x);
        }
        return agents;
    }
    
    
    public LinkedList<Agent[]> connectAgents(LinkedList<Agent> agents){
        LinkedList<Agent[]> connections = new LinkedList<>();
            Agent[] connection1 = new Agent[]{agents.get(0), agents.get(4)};
            connections.add(connection1);
            Agent[] connection2 = new Agent[]{agents.get(1), agents.get(7)};
            connections.add(connection2);
            Agent[] connection3 = new Agent[]{agents.get(1), agents.get(10)};
            connections.add(connection3);
            Agent[] connection4 = new Agent[]{agents.get(2), agents.get(7)};
            connections.add(connection4);
            Agent[] connection5 = new Agent[]{agents.get(2), agents.get(9)};
            connections.add(connection5);
            Agent[] connection6 = new Agent[]{agents.get(2), agents.get(11)};
            connections.add(connection6);
            Agent[] connection7 = new Agent[]{agents.get(4), agents.get(5)};
            connections.add(connection7);
            Agent[] connection8 = new Agent[]{agents.get(8), agents.get(10)};
            connections.add(connection8);
            Agent[] connection9 = new Agent[]{agents.get(9), agents.get(10)};
            connections.add(connection9);
            Agent[] connection10 = new Agent[]{agents.get(9), agents.get(12)};
            connections.add(connection10);
        return connections;
    }
    
    // Returns all nodes that appear in NO connection.
    public LinkedList<Integer> findIsolatedNodes(LinkedList<Agent[]> connections, int N) {

        boolean[] seen = new boolean[N];

        // mark every node that appears in any pair
        for (Agent[] pair : connections) {
            int a = pair[0].getNum();
            int b = pair[1].getNum();

            if (a >= 0 && a < N) seen[a] = true;
            if (b >= 0 && b < N) seen[b] = true;
        }

        LinkedList<Integer> isolated = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            if (!seen[i]) isolated.add(i);
        }

        return isolated;
    }
}
