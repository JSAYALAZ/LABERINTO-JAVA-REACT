package com.example.Labyrinth.service;

import com.example.Labyrinth.model.Grapho;
import com.example.Labyrinth.model.Summary;

public class MetodoExtra {

    Dinamic dinamic = new Dinamic();
    SimpleRecursive simple = new SimpleRecursive();
    

    

    // Método para encontrar el mejor camino comparando los resultados de DFS y BFS
    public Summary[] getBestPath(Grapho laberinto) {
        Summary summarySimple = new SimpleRecursive().summary(laberinto);
        Summary summaryDinamic = new Dinamic().summary(laberinto);
        Summary[] respuesta = new Summary[2];
        respuesta[0] = summarySimple;
        respuesta[1] = summaryDinamic;
        return respuesta;
    }
}
