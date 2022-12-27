package com.example.imageUI.controller;

import com.example.imageUI.domain.Tag;
import com.example.imageUI.dto.TagDto;
import com.example.imageUI.dto.request.CreateTagRequest;
import com.example.imageUI.exceptions.TagNotFoundExceptions;
import com.example.imageUI.exceptions.TagNotFoundRestException;
import com.example.imageUI.mapper.TagMapper;
import com.example.imageUI.service.Imp.TagServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/tags")
public class TagController {
    public static final String TAG_NOT_FOUND = "Tag not found";
    private final TagServiceImp tagServiceImp;

    private final TagMapper mapper;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public TagDto createTag(@Valid @RequestBody final CreateTagRequest request){
        return mapper.map(tagServiceImp.createTag(request));
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Iterable<TagDto> getAll(){
        return mapper.map(tagServiceImp.findAll());
    }

    @DeleteMapping("/{tagId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteTag(@PathVariable("tagId") final UUID uuid){
        try {
            tagServiceImp.deleteTag(uuid);
        }
        catch (TagNotFoundExceptions e){
            throw new TagNotFoundRestException(TAG_NOT_FOUND);
        }
    }

    @GetMapping("/{tagId}")
    @ResponseStatus(code = HttpStatus.OK)
    public TagDto findTag(@PathVariable("tagId") final UUID uuid){
        Tag tag = tagServiceImp.findByUuid(uuid).orElseThrow(() -> new TagNotFoundRestException(TAG_NOT_FOUND));
        return mapper.map(tag);
    }

    @PutMapping("/{tagId}")
    @ResponseStatus(code = HttpStatus.OK)
    public TagDto updateTag(@Valid @ResponseBody final CreateTagRequest request,
                            @PathVariable("tagId") final UUID uuid){

        return ;
    }

}
