package com.example.Labyrinth.model;

public class Celda {
    private String id;

    /**
     * Constructor para inicializar un objeto celda con un ID dado.
     * 
     * @param id El identificador único de la celda.
     */
    public Celda(String id) {
        this.id = id;
    }

    /**
     * Método para obtener el ID de la celda.
     * 
     * @return El ID de la celda.
     */
    public String getId() {
        return id;
    }
}
