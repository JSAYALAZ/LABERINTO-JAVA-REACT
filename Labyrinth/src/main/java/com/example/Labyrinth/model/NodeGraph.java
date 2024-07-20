package com.example.Labyrinth.model;

import java.util.ArrayList;
import java.util.List;

public class NodeGraph <T> {
    private T value;
    private List<NodeGraph<T>> arista;

    public NodeGraph(T valor){
        this.value = valor;
        this.arista = new ArrayList<>();
    }

    public T getVisited(){
        return this.value;
    }

    public List<NodeGraph<T>> getAristas(){
        return this.arista;
    }

    public void addArista (NodeGraph<T> nodo){
        this.arista.add(nodo);
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public List<NodeGraph<T>> getArista() {
        return arista;
    }

    public void setArista(List<NodeGraph<T>> arista) {
        this.arista = arista;
    }
    
}
