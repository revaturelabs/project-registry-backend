package com.revature.registry.controller;

import java.util.List;


import com.revature.registry.model.Status;
import com.revature.registry.service.StatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/api/status", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class StatusController {
    @Autowired
    private StatusService statusService;

    @GetMapping("")
    public ResponseEntity<List<Status>> getAllStatuses() {
        return statusService.getAllStatuses();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Status> getStatusById(@PathVariable("id") int id) {
        return statusService.getStatusById(id);
    }
    
}
