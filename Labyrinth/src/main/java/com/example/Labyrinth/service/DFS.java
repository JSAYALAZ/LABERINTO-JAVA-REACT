package com.example.Labyrinth.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.example.Labyrinth.model.Celda;
import com.example.Labyrinth.model.Grapho;
import com.example.Labyrinth.model.NodeGraph;

public class DFS {

    /**
     * Este método realiza una búsqueda en profundidad (DFS) en un grafo de laberinto
     * para explorar todas las celdas accesibles desde la celda inicial (0,0).
     *
     * @param laberinto Un objeto Grapho que representa el laberinto.
     * @return Una lista de cadenas que representa los IDs de las celdas visitadas en el orden de visita.
     */
    public List<String> serviceGetDFS(Grapho laberinto) {
        // Mapa para rastrear las celdas visitadas
        Map<String, Boolean> visited = new LinkedHashMap<>();
        // Obtener la celda inicial
        NodeGraph<Celda> startNode = laberinto.getCelda(0, 0);

        // Iniciar DFS si la celda inicial no es nula
        if (startNode != null) {
            getDFSUtil(startNode, visited);
        }

        // Devolver la lista de celdas visitadas
        return new ArrayList<>(visited.keySet());
    }

    /**
     * Método utilitario recursivo para realizar DFS desde una celda dada.
     *
     * @param node La celda desde la cual iniciar la DFS.
     * @param visited Mapa que lleva un registro de las celdas visitadas.
     */
    private void getDFSUtil(NodeGraph<Celda> node, Map<String, Boolean> visited) {
        // Marcar la celda actual como visitada
        visited.put(node.getValue().getId(), true);
        // Recorrer todos los vecinos de la celda actual
        for (NodeGraph<Celda> neighbor : node.getArista()) {
            // Si el vecino no ha sido visitado, realizar DFS recursivamente
            if (!visited.getOrDefault(neighbor.getValue().getId(), false)) {
                getDFSUtil(neighbor, visited);
            }
        }
    }
}
