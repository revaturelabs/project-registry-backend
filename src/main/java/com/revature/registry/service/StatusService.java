package com.revature.registry.service;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import com.revature.registry.model.Status;
import com.revature.registry.repository.StatusRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;


    public ResponseEntity<List<Status>> getAllStatuses() {
        log.debug("Getting all Statuses");
        return ResponseEntity.ok(statusRepository.findAll());
    }
    
    public ResponseEntity<Status> getStatusById(int id) {
        log.debug("Getting status by Id");
        Optional<Status> status = statusRepository.findById(id);
        if (status.isPresent()) {
            log.debug("Fetching Status with id of {}: {}", status.get());
            return ResponseEntity.ok(status.get());
        }
        log.error("Unable to GET. status with id {} not found.", id);
        return ResponseEntity.badRequest().build();
    }
}
