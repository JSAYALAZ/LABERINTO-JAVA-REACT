package com.example.Labyrinth.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.example.Labyrinth.model.Celda;
import com.example.Labyrinth.model.Grapho;
import com.example.Labyrinth.model.NodeGraph;

public class BFS {

    /**
     * Este método realiza una búsqueda en anchura (BFS) en un grafo de laberinto
     * para explorar todas las celdas accesibles desde la celda inicial (0,0).
     *
     * @param laberinto Un objeto Grapho que representa el laberinto.
     * @return Una lista de cadenas que representa los IDs de las celdas visitadas en el orden de visita.
     */
    public List<String> serviceGetBFS(Grapho laberinto) {
        // Mapa para rastrear las celdas visitadas
        Map<String, Boolean> visited = new LinkedHashMap<>();
        // Cola para BFS
        Queue<NodeGraph<Celda>> queue = new LinkedList<>();

        // Obtener la celda inicial
        NodeGraph<Celda> startNode = laberinto.getCelda(0, 0);

        // Iniciar BFS si la celda inicial no es nula
        if (startNode != null) {
            queue.add(startNode);
            visited.put(startNode.getValue().getId(), true);

            while (!queue.isEmpty()) {
                NodeGraph<Celda> currentNode = queue.poll();

                // Recorrer todos los vecinos de la celda actual
                for (NodeGraph<Celda> neighbor : currentNode.getArista()) {
                    // Si el vecino no ha sido visitado, añadirlo a la cola y marcarlo como visitado
                    if (!visited.getOrDefault(neighbor.getValue().getId(), false)) {
                        queue.add(neighbor);
                        visited.put(neighbor.getValue().getId(), true);
                    }
                }
            }
        }

        // Devolver la lista de celdas visitadas
        return new ArrayList<>(visited.keySet());
    }
}
