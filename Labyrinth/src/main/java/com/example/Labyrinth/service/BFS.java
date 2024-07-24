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
import com.example.Labyrinth.model.Summary;

public class BFS {
    
    /**
     * Este método realiza una búsqueda en anchura (BFS) en un grafo de laberinto
     * para encontrar el camino más corto desde la celda inicial (0,0) hasta la celda final (sizeY-1, sizeX-1).
     *
     * @param laberinto Un objeto Grapho que representa el laberinto.
     * @return Un objeto Summary que contiene el nombre del algoritmo, el tiempo de ejecución, 
     * el recorrido de las celdas visitadas en el orden de visita, el recorrido completo desde 
     * el inicio hasta la celda final y el número de pasos.
     */
    public Summary serviceGetBFS(Grapho laberinto) {
        // Cola para BFS y mapas para almacenar el camino y los padres de cada nodo
        Queue<NodeGraph<Celda>> cola = new LinkedList<>();
        Map<String, Boolean> visited = new LinkedHashMap<>();
        Map<String, String> parent = new LinkedHashMap<>();
        
        // Celdas inicial y final
        NodeGraph<Celda> startNode = laberinto.getCelda(0, 0);
        NodeGraph<Celda> endNode = laberinto.getCelda(laberinto.getSizeX() - 1, laberinto.getSizeY() - 1); // Suponiendo que hay un método para obtener el nodo objetivo
        
        long timeStart = System.nanoTime();
        
        if (startNode != null) {
            cola.add(startNode);
            visited.put(startNode.getValue().getId(), true);
            parent.put(startNode.getValue().getId(), null); // El nodo inicial no tiene padre

            while (!cola.isEmpty()) {
                NodeGraph<Celda> currentNode = cola.poll();

                // Si llegamos al nodo final, detener la búsqueda
                if (currentNode.getValue().getId().equals(endNode.getValue().getId())) {
                    break;
                }

                // Recorrer todos los vecinos de la celda actual
                for (NodeGraph<Celda> neighbor : currentNode.getArista()) {
                    // Si el vecino no ha sido visitado, añadirlo a la cola y marcarlo como visitado
                    if (!visited.getOrDefault(neighbor.getValue().getId(), false)) {
                        cola.add(neighbor);
                        visited.put(neighbor.getValue().getId(), true);
                        parent.put(neighbor.getValue().getId(), currentNode.getValue().getId());
                    }
                }
            }
        }
        
        long timeEnd = System.nanoTime();
        Double duration = (timeEnd - timeStart)*10e-9;

        // Reconstruir el camino desde el nodo final al inicial usando el mapa de padres
        List<String> path = new ArrayList<>();
        if (visited.getOrDefault(endNode.getValue().getId(), false)) {
            String current = endNode.getValue().getId();
            while (current != null) {
                path.add(0, current); // Añadir al inicio de la lista
                current = parent.get(current);
            }
        }
        
        // Crear el objeto Summary
        Summary summary = new Summary();
        summary.setName("bfs");
        summary.setTime(Double.toString(duration));
        summary.setRecorrido(new ArrayList<>(visited.keySet()));
        summary.setRespuesta(path);
        summary.setPasos(path.size());
        
        return summary;
    }
}
