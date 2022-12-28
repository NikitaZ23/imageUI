package com.example.imageUI.controller;

import com.example.imageUI.dto.ImWithTagsDto;
import com.example.imageUI.dto.request.CreateIWTRequest;
import com.example.imageUI.exceptions.ImageNotFoundExceptions;
import com.example.imageUI.exceptions.ImageNotFoundRestException;
import com.example.imageUI.mapper.ImageWithTagMapper;
import com.example.imageUI.service.Imp.ImWithTagsServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/iwt")
@AllArgsConstructor
public class IWTController {
    private final ImageWithTagMapper mapper;

    private final ImWithTagsServiceImp serviceImp;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Iterable<ImWithTagsDto> findAll(){
        return mapper.map(serviceImp.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Iterable<ImWithTagsDto> findImageTags(@PathVariable("id") final int id){
        return mapper.map(serviceImp.findById_Im(id));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ImWithTagsDto createDependence(@Valid @RequestBody final CreateIWTRequest request){
        return mapper.map(serviceImp.createIWT(request));
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteDependence(@PathVariable("uuid") final UUID uuid){
        try{
            serviceImp.delete(uuid);
        }
        catch (ImageNotFoundExceptions e){
            throw new ImageNotFoundRestException(e.getMessage());
        }
    }
}
