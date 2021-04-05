package com.revature.registry.controller;

import java.util.List;

import com.revature.registry.model.Tag;
import com.revature.registry.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping(value = "/api/Tag", produces = MediaType.APPLICATION_JSON_VALUE)
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("")
    public ResponseEntity<List<Tag>> getAllTags() {
        return tagService.getAllTags();
    }
    
    @PostMapping("")
    public ResponseEntity<String> createTag(@RequestBody Tag tag) {
        return tagService.createTag(tag);
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable("id") int id) {
        return tagService.getTagById(id);
    }
    
    
}
