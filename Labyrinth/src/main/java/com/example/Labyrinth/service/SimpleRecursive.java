package com.example.Labyrinth.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.Labyrinth.model.Celda;
import com.example.Labyrinth.model.Grapho;
import com.example.Labyrinth.model.NodeGraph;

public class SimpleRecursive {

    /**
     * Este método encuentra un camino en un laberinto desde la celda inicial (0,0) hasta la celda final (sizeY-1, sizeX-1)
     * utilizando un enfoque recursivo simple.
     *
     * @param laberinto Un objeto Grapho que representa el laberinto.
     * @return Una lista de cadenas que representa el camino desde la celda inicial hasta la celda final.
     */
    public List<String> serviceGetRecursive(Grapho laberinto) {
        List<String> path = new ArrayList<>();
        Set<String> visited = new HashSet<>(); // Para evitar ciclos
        getRecursiveUtil(laberinto, laberinto.getCelda(0, 0), 0, 0, path, visited);
        Collections.reverse(path); // Revertimos el path para que el orden sea desde el inicio hasta el final
        return path;
    }

    /**
     * Método recursivo para encontrar un camino en el laberinto.
     *
     * @param laberinto El grafo que representa el laberinto.
     * @param grid La celda actual en el grafo.
     * @param row La fila de la celda actual.
     * @param col La columna de la celda actual.
     * @param path La lista que almacena el camino encontrado.
     * @param visited Conjunto que almacena los IDs de las celdas visitadas para evitar ciclos.
     * @return true si se encontró un camino desde la celda actual hasta la celda final, false en caso contrario.
     */
    private boolean getRecursiveUtil(Grapho laberinto, NodeGraph<Celda> grid, int row, int col, List<String> path,
            Set<String> visited) {

        // Verificar si la celda actual está fuera de los límites del laberinto
        if (row < 0 || col < 0 || row >= laberinto.getSizeY() || col >= laberinto.getSizeX()) {
            return false;
        }

        // Crear una nueva celda con la posición actual
        Celda point = new Celda(row + "," + col);

        // Verificar si la celda ya ha sido visitada
        if (visited.contains(point.getId())) {
            return false;
        }

        visited.add(point.getId());

        // Verificar si hemos llegado a la última celda
        boolean isAtEnd = (row == laberinto.getSizeY() - 1) && (col == laberinto.getSizeX() - 1);
        if (isAtEnd) {
            path.add(point.getId());
            return true;
        }

        // Intentar moverse a la derecha, hacia abajo, hacia la izquierda o hacia arriba recursivamente
        if (getRecursiveUtil(laberinto, grid, row, col + 1, path, visited) ||  // Moverse a la derecha
            getRecursiveUtil(laberinto, grid, row + 1, col, path, visited) ||  // Moverse hacia abajo
            getRecursiveUtil(laberinto, grid, row, col - 1, path, visited) ||  // Moverse hacia la izquierda
            getRecursiveUtil(laberinto, grid, row - 1, col, path, visited)) {  // Moverse hacia arriba
            path.add(point.getId());
            return true;
        }

        visited.remove(point.getId());
        return false;
    }
}
