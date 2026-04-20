package com.inkWell.inkWell_api.controller;

import com.inkWell.inkWell_api.dto.TagRequestDTO;
import com.inkWell.inkWell_api.dto.TagResponseDTO;
import com.inkWell.inkWell_api.model.Tag;
import com.inkWell.inkWell_api.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
@CrossOrigin(origins = "*")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping
    public ResponseEntity<List<TagResponseDTO>> getAllTags(){
        return ResponseEntity.ok(tagService.getAllTags());
    }

    @PostMapping
    public ResponseEntity<Tag> addNewTag(@RequestBody TagRequestDTO newTagRequest){
        return ResponseEntity.ok(tagService.createNewTag(newTagRequest));
    }
}
