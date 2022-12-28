package com.example.imageUI.controller;

import com.example.imageUI.dto.ImageDto;
import com.example.imageUI.mapper.ImageMapper;
import com.example.imageUI.service.Imp.ImageServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@RestController
@AllArgsConstructor
@RequestMapping("/images")
public class ImageController {
    ImageServiceImp imageServiceImp;

    ImageMapper mapper;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Iterable<ImageDto> findAll() {
        return mapper.map(imageServiceImp.findAll());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ImageDto createImage(@RequestParam("file") MultipartFile file) {
        return mapper.map(imageServiceImp.createImage(file));
    }
}
