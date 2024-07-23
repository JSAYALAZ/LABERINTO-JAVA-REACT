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
     * para encontrar el camino más corto desde la celda inicial (0,0) hasta la celda final (sizeY-1, sizeX-1).
     *
     * @param laberinto Un objeto Grapho que representa el laberinto.
     * @return Una lista de cadenas que representa el camino desde la celda inicial hasta la celda final.
     */
    public List<String> serviceGetBFS(Grapho laberinto) {
        // Cola para BFS y mapa para almacenar el camino
        Queue<NodeGraph<Celda>> cola = new LinkedList<>();
        Map<String, Boolean> visited = new LinkedHashMap<>();
        
        // Celdas inicial y final
        NodeGraph<Celda> startNode = laberinto.getCelda(0, 0);
        
        if (startNode != null) {
            cola.add(startNode);
            visited.put(startNode.getValue().getId(), true);

            while (!cola.isEmpty()) {
                NodeGraph<Celda> currentNode = cola.poll();

                // Recorrer todos los vecinos de la celda actual
                for (NodeGraph<Celda> neighbor : currentNode.getArista()) {
                    // Si el vecino no ha sido visitado, añadirlo a la cola y marcarlo como visitado
                    if (!visited.getOrDefault(neighbor.getValue().getId(), false)) {
                        cola.add(neighbor);
                        visited.put(neighbor.getValue().getId(), true);
                    }
                }
            }
        }
        
        // Reconstruir el camino si se encontró
        return new ArrayList<>(visited.keySet());
    }
}
