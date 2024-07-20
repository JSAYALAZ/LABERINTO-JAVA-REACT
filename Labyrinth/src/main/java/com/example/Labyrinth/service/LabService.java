package com.example.Labyrinth.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.Labyrinth.model.Celda;
import com.example.Labyrinth.model.Grapho;
import com.example.Labyrinth.model.NodeGraph;

@Service
public class LabService {

    Random rand = new Random();

    /**
     * Metodo para conocer el representante (parent) de un conjunto: 
     *  - Kruskal
     *  - Union-find
    */
    private int find(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        } else {
            return parent[i] = find(parent, parent[i]);
        }
    }

    /**
     * Metodo para incluir un conjunto en otro, concervando
     * el representante (parent) del primero 
     * - Kruskal
     * - Union-find
    */
    private void union(int[] parent, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }

    /**
     * Metodo llamado desde el controllador
     * la cual es nuestra full rest / rest api
     * 
     * RESULTANTE:
         * 
                * {
                *      celda: [celdaConocida1, celdaConocida2],
                *      celda: [celdaConocida2],
                *      celda: [celdaConocida1, celdaConocida2, celdaConocida3, celdaConocida4],
                *      celda: [celdaConocida1, celdaConocida2, celdaConocida3],
                * }
    */
    public Map<String, List<String>> createLabyrinth(int filas, int columnas) {
        Grapho laberinto = new Grapho(filas, columnas);
        List<NodeGraph<Celda>> nodes = laberinto.getAllCeldas();
        List<int[]> edges = new ArrayList<>();
        int[] parent = new int[filas * columnas];


        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        // Crear todas las posibles aristas
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int index = i * columnas + j;
                if (j < columnas - 1) { // Pared hacia la derecha
                    edges.add(new int[]{index, index + 1});
                }
                if (i < filas - 1) { // Pared hacia abajo
                    edges.add(new int[]{index, index + columnas});
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

        /**
         * Retorno de estructura MAP 
         * {
         *  Llave: Celda actual
         *  Valor: Lista de celdas conocidas
         * }
         */
        Map<String, List<String>> caminos = new HashMap<>();
        for (NodeGraph<Celda> nodo : nodes) {
            List<String> conocidos = new ArrayList<>();
            for (NodeGraph<Celda> cell : nodo.getAristas()) {
                conocidos.add(cell.getValue().getId());
            }
            caminos.put(nodo.getValue().getId(), conocidos);
        }

        return caminos;
    }
}
