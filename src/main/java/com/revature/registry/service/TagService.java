package com.revature.registry.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;


import com.revature.registry.model.Tag;
import com.revature.registry.repository.TagRepository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class TagService {
private static Logger log = Logger.getLogger(TagService.class);

    @Autowired
    private TagRepository tagRepository;


    public ResponseEntity<List<Tag>> getAllTags() {
        log.info("Getting all tags");
        return ResponseEntity.ok(tagRepository.findAll());
    }
    
    public ResponseEntity<Tag> getTagById(int id) {
        log.debug("Getting tag by Id");
        Optional<Tag> tag = tagRepository.findById(id);
        if (tag.isPresent()) {
            log.info("Fetching Tag with id of " + id);
            return ResponseEntity.ok(tag.get());
        }
        log.error("Unable to GET. tag with id " + id + " not found");
        return ResponseEntity.badRequest().build();
    }
    
    
    public ResponseEntity<String> createTag(Tag tag) {
        Tag savedTag = tagRepository.save(tag);
        log.debug("Tag created with the following properties: {}");
        if ( savedTag.getId() != 0 ) {
            return ResponseEntity.ok("\"Success\"");
        } else {
            return ResponseEntity.badRequest().build();
        }
        
    }


}
