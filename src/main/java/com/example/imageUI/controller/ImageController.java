package com.example.imageUI.controller;

import com.example.imageUI.service.Imp.ImageServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ImageController {
    ImageServiceImp imageServiceImp;


}
