package com.example.Labyrinth.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.example.Labyrinth.model.Celda;
import com.example.Labyrinth.model.Grapho;
import com.example.Labyrinth.model.NodeGraph;
import com.example.Labyrinth.model.Summary;

public class DFS {

    /**
     * Este método realiza una búsqueda en profundidad (DFS) en un grafo de laberinto
     * para explorar todas las celdas accesibles desde la celda inicial (0,0).
     *
     * @param laberinto Un objeto Grapho que representa el laberinto.
     * @return Una lista de cadenas que representa los IDs de las celdas visitadas en el orden de visita.
     */
    public Summary serviceGetDFS(Grapho laberinto, String start, String end) {
        int startX = Integer.parseInt(start.split(",")[0]);
        int startY = Integer.parseInt(start.split(",")[1]);
        int endX = Integer.parseInt(end.split(",")[0]);
        int endY = Integer.parseInt(end.split(",")[1]);
        // Mapa para rastrear las celdas visitadas
        Map<String, Boolean> visited = new LinkedHashMap<>();
        // Obtener la celda inicial
        NodeGraph<Celda> startNode = laberinto.getCelda(startX, startY);

        // Iniciar DFS si la celda inicial no es nula
        long timeStart = System.nanoTime();
        if (startNode != null) {
            getDFSUtil(startNode, visited,null);
        }
        long timeEnd = System.nanoTime();

        Summary summary = new Summary();
        summary.setPasos(visited.size());
        summary.setName("dfs");
        summary.setTime(Double.toString(((timeEnd-timeStart)*10e-9)));
        summary.setRecorrido(new ArrayList<>(visited.keySet()));


        List<String> recorridoRespuesta = new ArrayList<>();
        NodeGraph<Celda> currentNode = laberinto.getCelda(endX,endY); // Suponiendo que hay un método para obtener el nodo objetivo
        while (currentNode != null) {
            recorridoRespuesta.add(0, currentNode.getValue().getId());
            currentNode = currentNode.getPadre();
        }

        summary.setRespuesta(recorridoRespuesta);
        // Devolver la lista de celdas visitadas
        return summary;
    }

    /**
     * Método utilitario recursivo para realizar DFS desde una celda dada.
     *
     * @param node La celda desde la cual iniciar la DFS.
     * @param visited Mapa que lleva un registro de las celdas visitadas.
     */
    private void getDFSUtil(NodeGraph<Celda> node, Map<String, Boolean> visited, NodeGraph<Celda> parent) {
        // Marcar la celda actual como visitada
        visited.put(node.getValue().getId(), true);
        node.setPadre(parent); // Establecer el nodo padre
        // Recorrer todos los vecinos de la celda actual
        for (NodeGraph<Celda> neighbor : node.getArista()) {
            // Si el vecino no ha sido visitado, realizar DFS recursivamente
            if (!visited.getOrDefault(neighbor.getValue().getId(), false)) {
                getDFSUtil(neighbor, visited,node);
            }
        }
    }
}
