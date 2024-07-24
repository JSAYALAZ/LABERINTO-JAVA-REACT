package com.example.Labyrinth.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.Labyrinth.model.Celda;
import com.example.Labyrinth.model.Grapho;
import com.example.Labyrinth.model.NodeGraph;
import com.example.Labyrinth.model.Summary;

public class Dinamic {

    public Summary serviceGetDinamic(Grapho laberinto) {
        List<String> path = new ArrayList<>();
        List<String> allSteps = new ArrayList<>();
        NodeGraph<Celda> start = laberinto.getCelda(0, 0);
        Map<NodeGraph<Celda>, Boolean> cache = new HashMap<>();

        long startTime = System.nanoTime();

        if (findPathUtil(start, path, cache, laberinto, allSteps)) {
            Collections.reverse(path); // El camino se recoge en orden inverso, así que lo invertimos.
        }

        long endTime = System.nanoTime();
       

        Summary summary = new Summary();
        summary.setName("Labyrinth Dynamic Solution");
        summary.setTime(Double.toString((endTime-startTime)*10e-9));
        summary.setRecorrido(allSteps);
        summary.setRespuesta(path);
        summary.setPasos(path.size());

        return summary;
    }

    private boolean findPathUtil(NodeGraph<Celda> node, List<String> path, Map<NodeGraph<Celda>, Boolean> cache, Grapho laberinto, List<String> allSteps) {
        if (node == null) {
            return false; // Si el nodo es nulo, regresa falso inmediatamente.
        }
        Celda current = node.getValue();
        allSteps.add(current.getId()); // Añadir cada paso al recorrido total

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
                hasPath = findPathUtil(neighbor, path, cache, laberinto, allSteps);
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
