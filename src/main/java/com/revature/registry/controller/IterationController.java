package com.revature.registry.controller;

import java.util.List;

import com.revature.registry.model.Iteration;
import com.revature.registry.service.IterationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/iteration", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class IterationController {
    @Autowired
    private IterationService iterationService;

    @GetMapping("")
    public ResponseEntity<List<Iteration>> getAllIterations() {
        return iterationService.getAllIterations();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Iteration> getIterationById(@PathVariable("id") int id) {
        return iterationService.getIterationById(id);
    }

    @PostMapping("")
    public ResponseEntity<Iteration> createIteration(@RequestBody Iteration iteration) {
        return iterationService.createIteration(iteration);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<Iteration> updateIteration(@PathVariable("id") int id, @RequestBody Iteration iteration) {
        return iterationService.updateIterationById(id, iteration);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<Iteration> deleteIteration(@PathVariable("id") int id) {
        return iterationService.deleteIterationById(id);
    }

}
