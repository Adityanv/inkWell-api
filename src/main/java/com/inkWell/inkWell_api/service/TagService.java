package com.inkWell.inkWell_api.service;

import com.inkWell.inkWell_api.dto.TagRequestDTO;
import com.inkWell.inkWell_api.dto.TagResponseDTO;
import com.inkWell.inkWell_api.model.Tag;
import com.inkWell.inkWell_api.repository.TagRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public List<TagResponseDTO> getAllTags(){
        List<Tag> allTags = tagRepository.findAll();
        List<TagResponseDTO> tagsWithPostCount = new ArrayList<>();
        for(Tag tag : allTags){
            int tagId = tag.getId();
            int postCount = tagRepository.getPostCountByTagId(tagId);
            tagsWithPostCount.add(new TagResponseDTO(tagId, tag.getName(), tag.getSlug(), postCount));
        }
        return tagsWithPostCount;
    }

    public Tag createNewTag(TagRequestDTO newTagRequest) {
        Tag newTag = new Tag();
        newTag.setName(newTagRequest.getName());
        newTag.setSlug(newTagRequest.getSlug());
        return tagRepository.save(newTag);
    }
}
