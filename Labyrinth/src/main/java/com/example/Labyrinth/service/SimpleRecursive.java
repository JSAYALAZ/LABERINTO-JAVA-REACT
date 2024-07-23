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
        NodeGraph<Celda> start = laberinto.getCelda(0, 0);
        if (getRecursiveUtil(laberinto, start, laberinto.getSizeX() - 1, laberinto.getSizeY() - 1, path, visited)) {
            Collections.reverse(path); // Revertimos el path para que el orden sea desde el inicio hasta el final
        }
        return path;
    }

    /**
     * Método recursivo para encontrar un camino en el laberinto.
     *
     * @param laberinto El grafo que representa el laberinto.
     * @param node La celda actual en el grafo.
     * @param finalRow La fila de la celda final.
     * @param finalCol La columna de la celda final.
     * @param path La lista que almacena el camino encontrado.
     * @param visited Conjunto que almacena los IDs de las celdas visitadas para evitar ciclos.
     * @return true si se encontró un camino desde la celda actual hasta la celda final, false en caso contrario.
     */
    private boolean getRecursiveUtil(Grapho laberinto, NodeGraph<Celda> node, int finalRow, int finalCol, List<String> path, Set<String> visited) {
        if (node == null) {
            return false; // Salir si el nodo es nulo
        }

        Celda current = node.getValue();

        // Verificar si la celda ya ha sido visitada
        if (!visited.add(current.getId())) {
            return false; // Si ya fue visitada, retornar falso
        }

        // Verificar si hemos llegado a la última celda
        if (Integer.parseInt(current.getId().split(",")[0]) == finalRow && Integer.parseInt(current.getId().split(",")[1])== finalCol) {
            path.add(current.getId());
            return true;
        }

        // Explorar todos los nodos conectados
        for (NodeGraph<Celda> neighbor : node.getArista()) {
            if (getRecursiveUtil(laberinto, neighbor, finalRow, finalCol, path, visited)) {
                path.add(current.getId());
                return true;
            }
        }

        // Si no se encontró un camino, desmarcar la visita
        visited.remove(current.getId());
        return false;
    }
}
