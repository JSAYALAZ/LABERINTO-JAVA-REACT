package com.example.Labyrinth.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.Labyrinth.model.Celda;
import com.example.Labyrinth.model.Grapho;
import com.example.Labyrinth.model.LabyrinthModel;
import com.example.Labyrinth.model.NodeGraph;

public class Laberinto {

    /**
     * Método para conocer el representante (parent) de un conjunto.
     * Implementación del algoritmo Union-Find utilizado en el algoritmo de Kruskal.
     *
     * @param parent El arreglo que representa los padres de cada conjunto.
     * @param i El índice del nodo cuya raíz estamos buscando.
     * @return El representante (parent) del conjunto.
     */
    private int find(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        } else {
            return parent[i] = find(parent, parent[i]);
        }
    }

    /**
     * Método para unir dos conjuntos conservando el representante (parent) del primero.
     * Implementación del algoritmo Union-Find utilizado en el algoritmo de Kruskal.
     *
     * @param parent El arreglo que representa los padres de cada conjunto.
     * @param x El índice del primer nodo.
     * @param y El índice del segundo nodo.
     */
    private void union(int[] parent, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }

    /**
     * Método llamado desde el controlador, nuestra API REST completa.
     * Este método crea un laberinto utilizando el algoritmo de Kruskal.
     *
     * @param filas El número de filas del laberinto.
     * @param columnas El número de columnas del laberinto.
     * @return Un objeto Grapho que representa el laberinto.
     */
    public Grapho createGraph(int filas, int columnas) {
        
        Grapho laberinto = new Grapho(filas, columnas);
        List<NodeGraph<Celda>> nodes = laberinto.getAllCeldas();
        List<int[]> edges = new ArrayList<>();
        int[] parent = new int[filas * columnas];

        
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        // Helper help = new Helper();
        // help.printArray(parent);

        // Crear todas las posibles aristas
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int index = i * columnas + j;
                if (j < columnas - 1) { // Pared hacia la derecha
                    edges.add(new int[] { index, index + 1 });
                }
                if (i < filas - 1) { // Pared hacia abajo
                    edges.add(new int[] { index, index + columnas });
                }
            }
        }

        
        // Mezclar y seleccionar aristas utilizando Kruskal
        Collections.shuffle(edges);
        for (int[] edge : edges) {
            int src = edge[0];
            int dest = edge[1];

            if (find(parent, src) != find(parent, dest)) {
                union(parent, src, dest);
                // Conectar nodos en el modelo de Grapho
                laberinto.addRute(nodes.get(src), nodes.get(dest));
            }
        }

        return laberinto;
    }


    public LabyrinthModel deleteRute(Grapho graph , String nodeDelete, String start, String end){
        List<NodeGraph<Celda>> nodes = graph.getAllCeldas();
        for (NodeGraph<Celda> nodeGraph : nodes) {
            List<NodeGraph<Celda>> newAristas = new ArrayList<>();
            //Each neighborth of the node
            for (NodeGraph<Celda> node : nodeGraph.getAristas()) {
                if (!node.getValue().getId().equals(nodeDelete)) {
                    newAristas.add(node);
                }
            }
            nodeGraph.setAristas(newAristas);

            if(nodeGraph.getValue().getId()==nodeDelete){
                nodeGraph.setAristas(null);
            }
        }
        
        return getLabyrinthResponse(graph, start, end);
    }
    public LabyrinthModel addRute(Grapho graph, String nodeAdd, String start, String end){
        int x = Integer.parseInt(nodeAdd.split(",")[0]);
        int y = Integer.parseInt(nodeAdd.split(",")[1]);
        NodeGraph<Celda> node = graph.getCelda(x,y);
        List<NodeGraph<Celda>> vecinos = new ArrayList<>();
        vecinos.add(graph.getCelda(x+1, y));
        vecinos.add(graph.getCelda(x-1, y));
        vecinos.add(graph.getCelda(x, y+1));
        vecinos.add(graph.getCelda(x, y-1));
        node.setAristas(vecinos);
        return getLabyrinthResponse(graph, start, end);
    }


    /**
     * Método para obtener un mapa del laberinto, donde cada celda está mapeada
     * a sus celdas conectadas.
     *
     * @param laberinto Un objeto Grapho que representa el laberinto.
     * @return Un mapa donde las claves son los IDs de las celdas y los valores son listas de IDs de celdas conectadas.
     */
    public LabyrinthModel getLabyrinthResponse(Grapho laberinto, String start, String end) {
        List<NodeGraph<Celda>> nodes = laberinto.getAllCeldas();
        Map<String, List<String>> caminos = new HashMap<>();

        for (NodeGraph<Celda> nodo : nodes) {
            List<String> conocidos = new ArrayList<>();
            for (NodeGraph<Celda> cell : nodo.getAristas()) {
                conocidos.add(cell.getValue().getId());
            }
            caminos.put(nodo.getValue().getId(), conocidos);
        }


        LabyrinthModel modelo = new LabyrinthModel();
        modelo.setGraph(caminos);
        modelo.setEnd(end);
        modelo.setStart(start);;
        return modelo;
    }
}
