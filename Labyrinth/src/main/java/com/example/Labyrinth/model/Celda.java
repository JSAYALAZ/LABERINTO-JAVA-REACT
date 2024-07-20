package com.example.Labyrinth.model;

public class Celda {
    private String id;

    /**
     * Objeto celda
     * @param id
     */
    public Celda(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }
}
