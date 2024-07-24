package com.example.Labyrinth.model;

import java.util.List;

public class Summary {
    private String name;
    private List<String> recorrido;
    private List<String> respuesta;
    private int pasos;

    public Summary(List<String> recorrido,List<String> respuesta, int pasos){
        this.recorrido = recorrido;
        this.respuesta = respuesta;
        this.pasos = pasos;
    }
    public Summary(){
        
    }

    
    public List<String> getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(List<String> recorrido) {
        this.recorrido = recorrido;
    }

    public int getPasos() {
        return pasos;
    }

    public void setPasos(int pasos) {
        this.pasos = pasos;
    }
    

    @Override
    public String toString() {
        return name+"={" +
                "recorrido=" + recorrido +
                "pasos" + pasos +
                "respuesta" + respuesta +
                '}';
    }

    public List<String> getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(List<String> respuesta) {
        this.respuesta = respuesta;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
