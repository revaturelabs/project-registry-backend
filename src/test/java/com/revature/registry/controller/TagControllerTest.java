package com.revature.registry.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.isA;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.registry.ProjectRegistryBackendApplication;
import com.revature.registry.model.Tag;
import com.revature.registry.service.TagService;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



@SpringBootTest(classes = ProjectRegistryBackendApplication.class)
@ExtendWith(SpringExtension.class)
public class TagControllerTest {
    
    private MockMvc mockMvc;
    
    @Autowired
    @InjectMocks
    private TagController tagController;
    
    @MockBean
    private TagService tagService;
    
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(tagController).build();
    }
    
    @Test
    void getAllTagsReturnsData() throws Exception {
        
        Tag tag1 = new Tag();
        tag1.setId(50);
        tag1.setName("Tester tag");
        tag1.setDescription("My purpose is to exist");

        Tag tag2 = new Tag();
        tag2.setId(51);
        tag2.setName("Tester tag 2");
        tag2.setDescription("My purpose is to foil the first tag, Muhaha");
        
        List<Tag> tags = Lists.newArrayList(tag1, tag2);
        
        when(tagService.getAllTags()).thenReturn(new ResponseEntity<List<Tag>>(tags, HttpStatus.OK));
        
        mockMvc.perform(get("/api/Tag")).andExpect(status().isOk())
        
        .andExpect(MockMvcResultMatchers.jsonPath("$.*", isA(List.class)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Tester tag"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Tester tag 2"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(50))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(51));

    }
    
    @Test
    void should_get_appropriate_tag_when_50isInput() throws Exception {
        Tag tag = new Tag();
        tag.setId(50);
        tag.setName("Tester tag");
        tag.setDescription("My purpose is to exist");
        
        when(tagService.getTagById(anyInt())).thenReturn(new ResponseEntity<Tag>(tag, HttpStatus.OK));
        
        mockMvc.perform(get("/api/Tag/id/50")).andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Tester tag"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(50))
        .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("My purpose is to exist"));
    }

    @Test
    void should_create_tag_when_valid_input() throws Exception {
        Tag tag = new Tag();
        tag.setId(50);
        tag.setName("Tester tag");
        tag.setDescription("My purpose is to exist");
        
        when(tagService.createTag(any(Tag.class))).thenReturn(new ResponseEntity<String>("Success", HttpStatus.OK));
        
        mockMvc.perform(post("/api/Tag").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(tag))).andExpect(status().isOk());
    }
}
