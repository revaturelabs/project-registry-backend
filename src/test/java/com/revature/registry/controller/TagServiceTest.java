package com.revature.registry.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import com.revature.registry.ProjectRegistryBackendApplication;
import com.revature.registry.model.Tag;
import com.revature.registry.repository.TagRepository;
import com.revature.registry.service.TagService;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(classes = ProjectRegistryBackendApplication.class)
@ExtendWith(SpringExtension.class)
public class TagServiceTest {

    @Autowired
    private TagService tagService;

    @MockBean
    private TagRepository tagRepository;

    // Retrieve all the tags in the DB and return them in an ArrayList
    @Test
    public void when_getAllTags_return_correct_list() {
        Tag tag1 = new Tag();
        tag1.setId(50);
        tag1.setName("Tester tag");
        tag1.setDescription("My purpose is to exist");

        Tag tag2 = new Tag();
        tag2.setId(51);
        tag2.setName("Tester tag 2");
        tag2.setDescription("My purpose is to foil the first tag, Muhaha");

        List<Tag> tags = Lists.newArrayList(tag1, tag2);

        ResponseEntity<List<Tag>> expected = new ResponseEntity<>(tags, HttpStatus.OK);

        when(tagRepository.findAll()).thenReturn(tags);

        ResponseEntity<List<Tag>> output = tagService.getAllTags();

        assertThat(output).isEqualTo(expected);
    }

    @Test
    void when_getTagByID_then_return_correct_project() {
        Tag tag = new Tag();
        tag.setId(50);
        tag.setName("Tester tag");
        tag.setDescription("My purpose is to exist");

        ResponseEntity<Tag> expected = new ResponseEntity<Tag>(tag, HttpStatus.OK);

        when(tagRepository.findById(50)).thenReturn(Optional.of(tag));

        ResponseEntity<Tag> output = tagService.getTagById(50);

        assertThat(output).isEqualTo(expected);
    }

    @Test
    void when_getTagByID_bad_id_then_return_bad_request() {
        ResponseEntity<Tag> expected = new ResponseEntity<Tag>(HttpStatus.BAD_REQUEST);

        when(tagRepository.findById(anyInt())).thenReturn(Optional.empty());

        ResponseEntity<Tag> output = tagService.getTagById(657);

        assertThat(output).isEqualTo(expected);
    }

    @Test
    void when_createTag_return_created_tag() {
        Tag tag = new Tag();
        tag.setId(50);
        tag.setName("Tester tag");
        tag.setDescription("My purpose is to exist");

        ResponseEntity<String> expected = new ResponseEntity<String>("\"Success\"", HttpStatus.OK);

        when(tagRepository.save(any(Tag.class))).thenReturn(tag);

        Tag input = new Tag();
        input.setName("Test tag1");
        input.setDescription("Hoi");

        ResponseEntity<String> output = tagService.createTag(input);

        assertThat(output).isEqualTo(expected);

    }

}
