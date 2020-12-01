package com.revature.registry.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Iteration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int iterationCount;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
