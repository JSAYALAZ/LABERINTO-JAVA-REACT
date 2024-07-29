package com.example.Labyrinth.model;

import java.util.ArrayList;
import java.util.List;

public class Grapho {
    int curruntColumn = 0;
    int currentRow = 0;
    int sizeX, sizeY;
    int size;
    
    private NodeGraph<Celda>[][] matrizCeldas;
    
    /**
     * Constructor para inicializar el grafo con el tamaño especificado.
     * 
     * @param x El número de columnas del grafo.
     * @param y El número de filas del grafo.
     */
    @SuppressWarnings("unchecked")
    public Grapho(int x, int y) {
        this.sizeX = x;
        this.sizeY = y;
        this.size = x * y;
        matrizCeldas = new NodeGraph[x][y];

        // Crear todos los nodos para cada espacio disponible en la malla del grafo
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Celda celda = new Celda(i + "," + j);
                NodeGraph<Celda> nodo = new NodeGraph<>(celda);
                matrizCeldas[i][j] = nodo;
            }
        }
    }

    /**
     * Método para obtener una celda (Nodo) del grafo.
     * 
     * @param x La columna de la celda.
     * @param y La fila de la celda.
     * @return El nodo en la posición especificada.
     */
    public NodeGraph<Celda> getCelda(int x, int y) {
        return matrizCeldas[x][y];
    }

    /**
     * Método para obtener todas las celdas (Nodos) del grafo.
     * 
     * @return Una lista con todos los nodos del grafo.
     */
    public List<NodeGraph<Celda>> getAllCeldas() {
        List<NodeGraph<Celda>> list = new ArrayList<>();
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                list.add(matrizCeldas[i][j]);
            }
        }
        return list;
    }

    /**
     * Método para agregar una ruta (arista) ponderada entre dos nodos.
     * 
     * @param node1 El primer nodo.
     * @param node2 El segundo nodo.
     */
    public void addRute(NodeGraph<Celda> node1, NodeGraph<Celda> node2) {
        node1.addArista(node2);
        node2.addArista(node1);
    }

    /**
     * Método para obtener el tamaño total del grafo.
     * 
     * @return El tamaño total del grafo.
     */
    public int getSize() {
        return size;
    }

    /**
     * Método para obtener el número de columnas del grafo.
     * 
     * @return El número de columnas del grafo.
     */
    public int getSizeX() {
        return sizeX;
    }

    /**
     * Método para obtener el número de filas del grafo.
     * 
     * @return El número de filas del grafo.
     */
    public int getSizeY() {
        return sizeY;
    }

    public void setMatrizCeldas(NodeGraph<Celda>[][] matrizCeldas) {
        this.matrizCeldas = matrizCeldas;
    }

    
}
