package com.example.Labyrinth.service;

import org.springframework.stereotype.Service;

@Service
public class LabService {

    public String getLab() {
        return "Lab";
    }

    public boolean[][] createLabyrinth(int row, int col){
        boolean[][] labyrinth = new boolean[row][col];
        for (int i = 0; i < labyrinth.length; i++) {
            labyrinth[i][0] = true;
        }
        return labyrinth;
    }

}
