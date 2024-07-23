package com.example.Labyrinth.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Labyrinth.service.LabService;

@RestController
@RequestMapping("/laberinto")
@CrossOrigin(origins = "*")
public class LabController {

    @Autowired
    private LabService service;

    /**
     * Endpoint para crear un laberinto.
     * 
     * /laberinto/create - POST
     * 
     * @param row El número de filas del laberinto.
     * @param col El número de columnas del laberinto.
     * @return Un mapa donde las claves son los IDs de las celdas y los valores son listas de IDs de celdas conectadas.
     */
    @PostMapping("/create")
    public Map<String, List<String>> crear(@RequestParam int row, @RequestParam int col) {
        service = new LabService();
        return service.createLabyrinth(row, col);
    }

    /**
     * Endpoint para obtener un camino en el laberinto utilizando el algoritmo de búsqueda en profundidad (DFS).
     * 
     * /laberinto/dfs - GET
     * 
     * @return Una lista de cadenas que representa el camino encontrado por DFS.
     */
    @GetMapping("/dfs")
    public List<String> DFS() {
        return service.pathDFS();
    }

    /**
     * Endpoint para obtener un camino en el laberinto utilizando un enfoque recursivo simple.
     * 
     * /laberinto/recursivoSimple - GET
     * 
     * @return Una lista de cadenas que representa el camino encontrado recursivamente.
     */
    @GetMapping("/recursivoSimple")
    public List<String> recursivoSimple() {
        return service.pathRecursive();
    }

    /**
     * Endpoint para obtener un camino en el laberinto utilizando un enfoque de programación dinámica.
     * 
     * /laberinto/dinamic - GET
     * 
     * @return Una lista de cadenas que representa el camino encontrado mediante programación dinámica.
     */
    @GetMapping("/dinamic")
    public List<String> getProDinamic() {
        return service.pathDinamic();
    }

    /**
     * Endpoint para obtener un camino en el laberinto utilizando el algoritmo de búsqueda en anchura (BFS).
     * 
     * /laberinto/bfs - GET
     * 
     * @return Una lista de cadenas que representa el camino encontrado por BFS.
     */
    @GetMapping("/bfs")
    public List<String> getPathBFS() {
        return service.pathBFS();
    }

    /**
     * Endpoint de prueba para crear un laberinto con dimensiones predefinidas.
     * 
     * /laberinto/create - GET
     * 
     * @return Un mapa donde las claves son los IDs de las celdas y los valores son listas de IDs de celdas conectadas.
     */
    @GetMapping("/create")
    public Map<String, List<String>> crear() {
        return service.createLabyrinth(3, 3);
    }
}
