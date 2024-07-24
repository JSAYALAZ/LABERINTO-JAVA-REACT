package com.example.Labyrinth.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.Labyrinth.model.Celda;
import com.example.Labyrinth.model.Grapho;
import com.example.Labyrinth.model.NodeGraph;
import com.example.Labyrinth.model.Summary;

public class SimpleRecursive {

    public Summary serviceGetRecursive(Grapho laberinto) {
        List<String> path = new ArrayList<>();
        List<String> allSteps = new ArrayList<>();
        Set<String> visited = new HashSet<>(); // Para evitar ciclos
        NodeGraph<Celda> start = laberinto.getCelda(0, 0);

        // Calcular el tiempo de inicio
        long startTime = System.nanoTime();

        if (getRecursiveUtil(laberinto, start, laberinto.getSizeX() - 1, laberinto.getSizeY() - 1, path, visited, allSteps)) {
            Collections.reverse(path); // Revertimos el path para que el orden sea desde el inicio hasta el final
        }

        // Calcular el tiempo de fin
        long endTime = System.nanoTime();

        // Crear el objeto Summary
        Summary summary = new Summary();
        summary.setName("Labyrinth Solution");
        summary.setTime(Double.toString((endTime-startTime)*10e-9));
        summary.setRecorrido(allSteps);
        summary.setRespuesta(path); // Solo el camino hacia el resultado
        summary.setPasos(path.size());

        return summary;
    }

    private boolean getRecursiveUtil(Grapho laberinto, NodeGraph<Celda> node, int finalRow, int finalCol, List<String> path, Set<String> visited, List<String> allSteps) {
        if (node == null) {
            return false; // Salir si el nodo es nulo
        }

        Celda current = node.getValue();
        allSteps.add(current.getId()); // Añadir cada paso al recorrido total

        // Verificar si la celda ya ha sido visitada
        if (!visited.add(current.getId())) {
            return false; // Si ya fue visitada, retornar falso
        }

        // Verificar si hemos llegado a la última celda
        if (Integer.parseInt(current.getId().split(",")[0]) == finalRow && Integer.parseInt(current.getId().split(",")[1]) == finalCol) {
            path.add(current.getId());
            return true;
        }

        // Explorar todos los nodos conectados
        for (NodeGraph<Celda> neighbor : node.getArista()) {
            if (getRecursiveUtil(laberinto, neighbor, finalRow, finalCol, path, visited, allSteps)) {
                path.add(current.getId());
                return true;
            }
        }

        // Si no se encontró un camino, desmarcar la visita
        visited.remove(current.getId());
        return false;
    }
}
