package com.example.Labyrinth.service;

import java.util.Random;

import org.springframework.stereotype.Service;
import com.example.Labyrinth.model.Grapho;
import com.example.Labyrinth.model.LabyrinthModel;
import com.example.Labyrinth.model.Summary;

@Service
public class LabService {

    public LabService() {
    }

    Random rand = new Random();
    Grapho laberinto;
    String start;
    String end;
    Laberinto creator = new Laberinto();

    /**
     * Este método crea un laberinto con el número de filas y columnas especificado
     * y devuelve un mapa del laberinto donde cada celda está mapeada a sus celdas conectadas.
     *
     * @param row El número de filas del laberinto.
     * @param col El número de columnas del laberinto.
     * @return Un mapa donde las claves son los IDs de las celdas y los valores son listas de IDs de celdas conectadas.
     */
    public LabyrinthModel createLabyrinth(int row, int col, String start, String end) {
        this.start = start;
        this.end = end;
        laberinto = creator.createGraph(row, col);
        return creator.getLabyrinthResponse(laberinto, start, end);
    }


    public LabyrinthModel createRute(String id){
        return creator.addRute(laberinto, id, start, end);
    }
    public LabyrinthModel delleteRute(String id){
        return creator.deleteRute(laberinto, id, start, end);
    }

    /**
     * Este método encuentra un camino en el laberinto utilizando el algoritmo de búsqueda en profundidad (DFS).
     *
     * @return Una lista de cadenas que representa el camino encontrado por DFS.
     */
    public Summary pathDFS() {
        return new DFS().serviceGetDFS(laberinto,start,end);
    }

    /**
     * Este método encuentra un camino en el laberinto utilizando el algoritmo de búsqueda en anchura (BFS).
     *
     * @return Una lista de cadenas que representa el camino encontrado por BFS.
     */
    public Summary pathBFS() {
        return new BFS().serviceGetBFS(laberinto,start,end);
    }

    /**
     * Este método encuentra un camino en el laberinto utilizando un enfoque recursivo simple.
     *
     * @return Una lista de cadenas que representa el camino encontrado recursivamente.
     */
    public Summary pathRecursive() {
        return new SimpleRecursive().serviceGetRecursive(laberinto,start,end);
    }

    /**
     * Este método encuentra un camino en el laberinto utilizando un enfoque de programación dinámica.
     *
     * @return Una lista de cadenas que representa el camino encontrado mediante programación dinámica.
     */
    public Summary pathDinamic() {
        return new Dinamic().serviceGetDinamic(laberinto,start,end);
    }

    // public Summary[] extra() {
    //     return new MetodoExtra().getBestPath(laberinto);
    // }
}
