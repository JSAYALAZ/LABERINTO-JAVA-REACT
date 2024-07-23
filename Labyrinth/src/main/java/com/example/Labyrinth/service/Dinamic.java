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
     * Encuentra un camino desde la celda inicial hasta la celda final en un laberinto.
     *
     * @param laberinto El objeto Grapho que representa el laberinto.
     * @return Una lista de cadenas que representan el camino desde la celda inicial hasta la final.
     */
    public List<String> serviceGetDinamic(Grapho laberinto) {
        // Lista para almacenar el camino encontrado.
        List<String> path = new ArrayList<>();
        // Celda inicial
        NodeGraph<Celda> start = laberinto.getCelda(0, 0);
        // Cache para memorizar celdas visitadas y si forman parte de un camino válido.
        Map<NodeGraph<Celda>, Boolean> cache = new HashMap<>();
        
        // Inicia la búsqueda desde la celda inicial
        if (findPathUtil(start, path, cache, laberinto)) {
            Collections.reverse(path); // El camino se recoge en orden inverso, así que lo invertimos.
            return path;
        }
        return Collections.emptyList(); // Devuelve una lista vacía si no hay camino.
    }

    /**
     * Método auxiliar para encontrar el camino mediante programación dinámica y memorización.
     *
     * @param node El nodo actual en el laberinto.
     * @param path Lista que almacena el camino encontrado.
     * @param cache Mapa para memorización de los resultados.
     * @param laberinto El grafo del laberinto.
     * @return true si existe un camino hasta la celda final.
     */
    private boolean findPathUtil(NodeGraph<Celda> node, List<String> path, Map<NodeGraph<Celda>, Boolean> cache, Grapho laberinto) {
        if (node == null) {
            return false; // Si el nodo es nulo, regresa falso inmediatamente.
        }
        Celda current = node.getValue();
        

        // Verifica si es la celda final
        if (current.equals(laberinto.getCelda(laberinto.getSizeX() - 1, laberinto.getSizeY() - 1).getValue())) {
            path.add(current.getId());
            return true;
        }
    
        // Marcar el nodo como visitado para evitar ciclos
        if (cache.containsKey(node) && cache.get(node)) {
            return false; // Si el nodo ya fue visitado y no formó parte de un camino válido, retornar falso
        }
        cache.put(node, true); // Marcar como visitado
    
        boolean hasPath = false;
        // Itera sobre cada vecino conectado
        for (NodeGraph<Celda> neighbor : node.getArista()) {
            // Sólo procesa vecinos no visitados o que no están en cache
            if (!cache.containsKey(neighbor) || !cache.get(neighbor)) {
                hasPath = findPathUtil(neighbor, path, cache, laberinto);
                if (hasPath) {
                    path.add(current.getId());
                    break;
                }
            }
        }
    
        // Si no se encontró un camino desde este nodo, desmarcarlo (esto es parte del backtracking)
        if (!hasPath) {
            cache.put(node, false);
        }
    
        return hasPath;
    }
}