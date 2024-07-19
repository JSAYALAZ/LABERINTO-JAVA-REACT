package com.example.Labyrinth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.Labyrinth.service.LabService;;

@RestController
@RequestMapping("/laberinto")
@CrossOrigin(origins = "*") 
public class LabController {
    @Autowired
    private LabService service;

    @GetMapping("/hello")
    public String saludo(){
        return service.getLab();
    }

    @PostMapping("/create")
    public boolean[][] mover(@RequestParam int row, int col) {
        return service.createLabyrinth(row, col);
    }
}
