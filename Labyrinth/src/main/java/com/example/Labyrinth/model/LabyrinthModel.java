package com.example.Labyrinth.model;

import java.util.List;
import java.util.Map;

public class LabyrinthModel {
    private Map<String, List<String>> graph;
    private String start;
    private String end;
    public LabyrinthModel() {
    }
    public Map<String, List<String>> getGraph() {
        return graph;
    }
    public void setGraph(Map<String, List<String>> graph) {
        this.graph = graph;
    }
    public String getStart() {
        return start;
    }
    public void setStart(String start) {
        this.start = start;
    }
    public String getEnd() {
        return end;
    }
    public void setEnd(String end) {
        this.end = end;
    }
    @Override
    public String toString() {
        return "{"+
            "laberinto" + graph +
            "start" + start +
            "end" + end + 
            "}";
    }

    
}
