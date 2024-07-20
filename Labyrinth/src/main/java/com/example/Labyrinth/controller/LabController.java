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
import com.example.Labyrinth.service.LabService;;

@RestController
@RequestMapping("/laberinto")
@CrossOrigin(origins = "*")
public class LabController {

    @Autowired
    private LabService service;

    /**
     * /laberinto/create - POST
     * Endpoint params row, col.
     * 
     * @return
     *         {
     *         '0,0':['0,1', '1,1', '1,5'],
     *         '0,0':['0,1', '1,1', '1,5'],
     *         '0,0':['0,1', '1,1', '1,5'],
     *         '0,0':['0,1', '1,1', '1,5'],
     *         }
     */
    @PostMapping("/create")
    public Map<String, List<String>> mover(@RequestParam int row, int col) {
        return service.createLabyrinth(row, col);
    }

    /**
     * /laberinto/create - GET
     * Endpoint pruebas
     * 
     * @return
     */
    @GetMapping("/create")
    public Map<String, List<String>> mover() {
        return service.createLabyrinth(5, 8);
    }

}
