package com.revature.registry.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * An {@link Organization} refers to a Github Organization.
 *
 * Typically, the root/first {@link Iteration} Github Repository will be found in the `revaturelabs` Organization.
 * Subsequent sprints have an individual {@link Organization} created, with as many {@link Repository}'s
 * as required to meet the applications needs.
 */

@Entity
@Data
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
