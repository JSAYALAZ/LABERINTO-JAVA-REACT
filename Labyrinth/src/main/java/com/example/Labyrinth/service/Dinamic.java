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

    public Summary serviceGetDinamic(Grapho laberinto,String start, String end) {
        int startX = Integer.parseInt(start.split(",")[0]);
        int startY = Integer.parseInt(start.split(",")[1]);
        int endX = Integer.parseInt(end.split(",")[0]);
        int endY = Integer.parseInt(end.split(",")[1]);

        List<String> path = new ArrayList<>();
        List<String> allSteps = new ArrayList<>();
        NodeGraph<Celda> startNode = laberinto.getCelda(startX, startY);
        NodeGraph<Celda> endNode = laberinto.getCelda(endX, endY);
        Map<NodeGraph<Celda>, Boolean> cache = new HashMap<>();

        long startTime = System.nanoTime();

        if (findPathUtil(startNode,endNode, path, cache, laberinto, allSteps)) {
            Collections.reverse(path); 
        }

        long endTime = System.nanoTime();
       

        Summary summary = new Summary();
        summary.setName("Metodo dinamico - cache");
        summary.setTime(Double.toString((endTime-startTime)*10e-9));
        summary.setRecorrido(allSteps);
        summary.setRespuesta(path);
        summary.setPasos(path.size());

        return summary;
    }

    private boolean findPathUtil(NodeGraph<Celda> startNode,NodeGraph<Celda> endNode, List<String> path, Map<NodeGraph<Celda>, Boolean> cache, Grapho laberinto, List<String> allSteps) {
        if (startNode == null) {
            return false; // Si el nodo es nulo, regresa falso inmediatamente.
        }
        Celda current = startNode.getValue();
        allSteps.add(current.getId()); // Añadir cada paso al recorrido total

        // Verifica si es la celda final
        if (current.equals(endNode.getValue())) {
            path.add(current.getId());
            return true;
        }

        // Marcar el nodo como visitado para evitar ciclos
        if (cache.containsKey(startNode) && cache.get(startNode)) {
            return false; // Si el nodo ya fue visitado y no formó parte de un camino válido, retornar falso
        }
        cache.put(startNode, true); // Marcar como visitado

        boolean hasPath = false;
        // Itera sobre cada vecino conectado
        for (NodeGraph<Celda> neighbor : startNode.getArista()) {
            // Sólo procesa vecinos no visitados o que no están en cache
            if (!cache.containsKey(neighbor) || !cache.get(neighbor)) {
                hasPath = findPathUtil(neighbor,endNode, path, cache, laberinto, allSteps);
                if (hasPath) {
                    path.add(current.getId());
                    break;
                }
            }
        }

        // Si no se encontró un camino desde este nodo, desmarcarlo (esto es parte del backtracking)
        if (!hasPath) {
            cache.put(startNode, false);
        }

        return hasPath;
    }
}
