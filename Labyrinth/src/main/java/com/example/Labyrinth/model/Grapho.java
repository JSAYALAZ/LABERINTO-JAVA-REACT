package com.example.Labyrinth.model;

import java.util.ArrayList;
import java.util.List;

public class Grapho {
    int curruntColumn = 0;
    int currentRow = 0;
    int sizeX, sizeY;
    
    private NodeGraph<Celda>[][] matrizCeldas;
    
    @SuppressWarnings("unchecked")
    public Grapho(int x, int y) {
        this.sizeX = x;
        this.sizeY = y;
        matrizCeldas = new NodeGraph[x][y];

        /**
         * Se crean todos los nodos por cada espacio disponible en la malla
         * del grapho en este caso es el resultante de la multiplicacion 
         * entre x - y
         */
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Celda celda = new Celda(i + "," + j);
                NodeGraph<Celda> nodo = new NodeGraph<>(celda);
                matrizCeldas[i][j] = nodo;
            }
        }
    }

    /**
     * Getter celda (Nodo)
     */
    public NodeGraph<Celda> getCelda(int x, int y) {
        return matrizCeldas[x][y];
    }


    /**
     * Obtener todas las celdas (Nodos)
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
     * Grapho ponderado
    */
    public void addRute(NodeGraph<Celda> node1, NodeGraph<Celda> node2) {
        node1.addArista(node2);
        node2.addArista(node1);
    }
}
