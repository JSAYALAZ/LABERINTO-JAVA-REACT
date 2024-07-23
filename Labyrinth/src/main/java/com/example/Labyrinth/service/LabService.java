package com.example.Labyrinth.service;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;
import com.example.Labyrinth.model.Grapho;

@Service
public class LabService {

    public LabService() {
    }

    Random rand = new Random();
    Grapho laberinto;
    Laberinto creator = new Laberinto();

    /**
     * Este método crea un laberinto con el número de filas y columnas especificado
     * y devuelve un mapa del laberinto donde cada celda está mapeada a sus celdas conectadas.
     *
     * @param row El número de filas del laberinto.
     * @param col El número de columnas del laberinto.
     * @return Un mapa donde las claves son los IDs de las celdas y los valores son listas de IDs de celdas conectadas.
     */
    public Map<String, List<String>> createLabyrinth(int row, int col) {
        laberinto = creator.createGraph(row, col);
        return creator.getLabyrinthMap(laberinto);
    }

    /**
     * Este método encuentra un camino en el laberinto utilizando el algoritmo de búsqueda en profundidad (DFS).
     *
     * @return Una lista de cadenas que representa el camino encontrado por DFS.
     */
    public List<String> pathDFS() {
        return new DFS().serviceGetDFS(laberinto);
    }

    /**
     * Este método encuentra un camino en el laberinto utilizando el algoritmo de búsqueda en anchura (BFS).
     *
     * @return Una lista de cadenas que representa el camino encontrado por BFS.
     */
    public List<String> pathBFS() {
        return new BFS().serviceGetBFS(laberinto);
    }

    /**
     * Este método encuentra un camino en el laberinto utilizando un enfoque recursivo simple.
     *
     * @return Una lista de cadenas que representa el camino encontrado recursivamente.
     */
    public List<String> pathRecursive() {
        return new SimpleRecursive().serviceGetRecursive(laberinto);
    }

    /**
     * Este método encuentra un camino en el laberinto utilizando un enfoque de programación dinámica.
     *
     * @return Una lista de cadenas que representa el camino encontrado mediante programación dinámica.
     */
    public List<String> pathDinamic() {
        return new Dinamic().serviceGetDinamic(laberinto);
    }
}
