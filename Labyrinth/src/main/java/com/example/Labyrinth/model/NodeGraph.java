package com.example.Labyrinth.model;

import java.util.ArrayList;
import java.util.List;

public class NodeGraph<T> {
    private T value;
    private List<NodeGraph<T>> arista;

    /**
     * Constructor para inicializar un nodo con un valor dado.
     * 
     * @param valor El valor del nodo.
     */
    public NodeGraph(T valor) {
        this.value = valor;
        this.arista = new ArrayList<>();
    }

    /**
     * Método para obtener el valor del nodo.
     * 
     * @return El valor del nodo.
     */
    public T getVisited() {
        return this.value;
    }

    /**
     * Método para obtener la lista de aristas (nodos conectados).
     * 
     * @return Una lista de nodos conectados a este nodo.
     */
    public List<NodeGraph<T>> getAristas() {
        return this.arista;
    }

    /**
     * Método para añadir una arista (conexión) a otro nodo.
     * 
     * @param nodo El nodo al cual se conectará este nodo.
     */
    public void addArista(NodeGraph<T> nodo) {
        this.arista.add(nodo);
    }

    /**
     * Método para obtener el valor del nodo.
     * 
     * @return El valor del nodo.
     */
    public T getValue() {
        return value;
    }

    /**
     * Método para establecer el valor del nodo.
     * 
     * @param value El nuevo valor del nodo.
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Método para obtener la lista de aristas (nodos conectados).
     * 
     * @return Una lista de nodos conectados a este nodo.
     */
    public List<NodeGraph<T>> getArista() {
        return arista;
    }

    /**
     * Método para establecer la lista de aristas (nodos conectados).
     * 
     * @param arista La nueva lista de nodos conectados a este nodo.
     */
    public void setArista(List<NodeGraph<T>> arista) {
        this.arista = arista;
    }
}
