package com.example.Labyrinth.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.Labyrinth.model.Celda;
import com.example.Labyrinth.model.Grapho;
import com.example.Labyrinth.model.NodeGraph;

public class Dinamic {

    /**
     * Este método encuentra un camino desde la celda inicial (0,0) hasta la celda final (sizeY-1, sizeX-1)
     * en un laberinto utilizando un enfoque de programación dinámica con memorización.
     *
     * @param laberinto Un objeto Grapho que representa el laberinto.
     * @return Una lista de cadenas que representa el camino desde la celda inicial hasta la celda final.
     */
    public List<String> serviceGetDinamic(Grapho laberinto) {
        List<String> path = new ArrayList<>();

        // Mapa para almacenar si ya visitamos una celda y si es parte del camino
        Map<Celda, Boolean> cache = new HashMap<>();
        if (getDinamicUtil(laberinto, laberinto.getCelda(0, 0), 0, 0, path, cache)) {
            Collections.reverse(path); // Revertimos el path para que el orden sea desde el inicio hasta el final
            return path;
        }
        return path;
    }

    /**
     * Método recursivo con memorización para encontrar un camino en el laberinto.
     *
     * @param laberinto El grafo que representa el laberinto.
     * @param grid La celda actual en el grafo.
     * @param row La fila de la celda actual.
     * @param col La columna de la celda actual.
     * @param path La lista que almacena el camino encontrado.
     * @param cache Mapa que almacena los resultados de las celdas visitadas.
     * @return true si se encontró un camino desde la celda actual hasta la celda final, false en caso contrario.
     */
    private boolean getDinamicUtil(Grapho laberinto, NodeGraph<Celda> grid, int row, int col, List<String> path,
            Map<Celda, Boolean> cache) {

        // Verificar si la celda actual está fuera de los límites del laberinto
        if (row < 0 || col < 0 || row >= laberinto.getSizeY() || col >= laberinto.getSizeX()) {
            return false;
        }
        Celda point = new Celda(row + "," + col);

        // Verificar el cache
        if (cache.containsKey(point)) {
            return cache.get(point);
        }

        // Verificar si hemos llegado a la última celda
        boolean isAtEnd = (row == laberinto.getSizeY() - 1) && (col == laberinto.getSizeX() - 1);
        if (isAtEnd) {
            path.add(point.getId());
            cache.put(point, true);
            return true;
        }

        // Intentar moverse a la derecha o hacia abajo recursivamente
        boolean success = getDinamicUtil(laberinto, grid, row, col + 1, path, cache)
                || getDinamicUtil(laberinto, grid, row + 1, col, path, cache);
        if (success) {
            path.add(point.getId());
        }

        // Almacenar el resultado en el cache
        cache.put(point, success);
        return success;
    }
}
