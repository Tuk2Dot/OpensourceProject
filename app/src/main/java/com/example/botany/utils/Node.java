package com.example.botany.utils;

/*******************************
 * Program name : Node.java
 * Author : Jeong, Junho
 * Last update : 2022. 05. 30.
 * Description : Weighted graph and A* algorithm class
 *******************************/

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Node implements Comparable<Node> {
    /* fields saved in waypoints.json */
    public int id;
    public double x;
    public double y;
    public List<Edge> neighbors;

    /* fields for a* algorithm */
    public Node parent = null;              // Parent in the path
    public double f = Double.MAX_VALUE;     // Evaluation functions
    public double g = Double.MAX_VALUE;     //
    public double h;                        // Hardcoded heuristic

    public Node(int i, int target, @NonNull JSONArray jsonArray) throws JSONException {
        JSONObject jsonObject_target = jsonArray.getJSONObject(target);
        JSONObject jsonObject_origin = jsonArray.getJSONObject(i);
        JSONArray jsonArray_edge = jsonObject_origin.getJSONArray("neighbors");

        this.id = jsonObject_origin.getInt("id");
        this.x = jsonObject_origin.getDouble("x");
        this.y = jsonObject_origin.getDouble("y");
        // h : euclidean distance to target
        this.h = Math.sqrt(Math.pow(jsonObject_target.getDouble("x") - x, 2)
                + Math.pow(jsonObject_target.getDouble("y") - y, 2));

        this.neighbors = new ArrayList<>();
        for (int k = 0; k < jsonObject_origin.getJSONArray("neighbors").length(); k++) {
            addBranch(jsonArray_edge.getJSONObject(k).getDouble("weight"), jsonArray_edge.getJSONObject(k).getInt("dest"));
        }
    }

    public Node(double x, double y, Edge link) {
        this.id = 0;
        this.x = x;
        this.y = y;
        this.neighbors = new ArrayList<>();
        addBranch(link.weight, link.node);
    }

    @Override
    public int compareTo(Node node) {
        return 0;
    }

    public static class Edge {
        Edge(double weight, int node){
            this.weight = weight;
            this.node = node;
        }

        public double weight;
        public int node;
    }

    public void addBranch(double weight, int node){
        Edge newEdge = new Edge(weight, node);
        neighbors.add(newEdge);
    }

    public double calculateHeuristic(Node target){
        return this.h;
    }

    public static Node aStar(Node start, Node target, Node[] graph){
        PriorityQueue<Node> closedList = new PriorityQueue<>();     // closed list U
        PriorityQueue<Node> openList = new PriorityQueue<>();       // open list O

        start.f = start.g + start.calculateHeuristic(target);       // set start node's f = g + h
        openList.add(start);            // 0번 노드 O에 추가

        while(!openList.isEmpty()){
            Node n = openList.peek();
            if(n == target){
                return n;
            }

            for(Node.Edge edge : n.neighbors){
                int l = edge.node;
                double totalWeight = n.g + edge.weight;
                Node m = graph[l];

                if(!openList.contains(m) && !closedList.contains(m)){
                    m.parent = n;
                    m.g = totalWeight;
                    m.f = m.g + m.calculateHeuristic(target);
                    openList.add(m);
                } else {
                    if(totalWeight < m.g){
                        m.parent = n;
                        m.g = totalWeight;
                        m.f = m.g + m.calculateHeuristic(target);

                        if(closedList.contains(m)){
                            closedList.remove(m);
                            openList.add(m);
                        }
                    }
                }
            }

            openList.remove(n);
            closedList.add(n);
        } // O 가 비면 경로 없는 것..
        return null;
    }

    public static void printPath(Node target){
        Node n = target;

        if(n==null)
            return;

        List<Integer> ids = new ArrayList<>();

        while(n.parent != null){
            ids.add(n.id);
            n = n.parent;
        }
        ids.add(n.id);
        Collections.reverse(ids);

        for(int id : ids){
            System.out.print(id + " ");
        }
        System.out.println("");
    }
}