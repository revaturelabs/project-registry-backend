package com.revature.registry.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import com.revature.registry.model.Iteration;
import com.revature.registry.repository.IterationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;





@Service
@Slf4j
public class IterationService {
@Autowired
private IterationRepository iterationRepository;

public ResponseEntity<List<Iteration>> getAllIterations() {
log.debug("Fetching all iterations: {}", iterationRepository.findAll());
return ResponseEntity.ok(iterationRepository.findAll());
}

public ResponseEntity<Iteration> getIterationById(int id) {
    Optional<Iteration> iteration = iterationRepository.findById(id);
    if (iteration.isPresent()) {
        log.debug("Fetching Iteration with id of {}: {}", iteration.get());
        return ResponseEntity.ok(iteration.get());
    }
    log.error("Unable to GET. Iteration with id {} not found.", id);
    return ResponseEntity.badRequest().build();
}

public ResponseEntity<Iteration> createIteration(Iteration iteration) {
    Iteration savedIteration = iterationRepository.save(iteration);
    log.debug("Iteration created with the following properties: {}", savedIteration);
    String location = String.format("/api/iteration/%s", savedIteration.getId());
    return ResponseEntity.created(URI.create(location)).body(savedIteration);
}

public ResponseEntity<Iteration> updateIterationById(int id, Iteration newIteration) {
    Optional<Iteration> iteration = iterationRepository.findById(id);
    if (iteration.isPresent()) {
        newIteration.setId(id);
        iterationRepository.save(newIteration);
        log.debug("Iteration with id: {}. Updated with the following properties: {}", id, newIteration);
        return ResponseEntity.ok(iteration.get());
    }

    log.error("Unable to update. Iteration with id {} not found.", id);
    return ResponseEntity.badRequest().build();
}

public ResponseEntity<Iteration> deleteIterationById(int id) {
    Optional<Iteration> iteration = iterationRepository.findById(id);
    if (iteration.isPresent()) {
        iterationRepository.deleteById(id);
        log.debug("Iteration Deleted with id: {}", id);
        return ResponseEntity.noContent().build();
    }
    log.error("Unable to DELETE. Iteration with id {} not found.", id);
    return ResponseEntity.badRequest().build();
}


}