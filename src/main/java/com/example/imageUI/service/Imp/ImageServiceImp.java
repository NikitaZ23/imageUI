package com.example.imageUI.service.Imp;

import com.example.imageUI.domain.Image;
import com.example.imageUI.dto.request.CreateImageRequest;
import com.example.imageUI.exceptions.ImageNotFoundExceptions;
import com.example.imageUI.repository.ImagesRepository;
import com.example.imageUI.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ImageServiceImp implements ImageService {
    public static final String IMAGE_NOT_FOUND = "Image not found";
    private final ImagesRepository repository;

    @Override
    public Iterable<Image> findAll() {
        System.out.println(repository.count());
        return repository.findAll();
    }

    @Override
    public Optional<Image> findByUuid(UUID uuid) {
        return repository.findByUuid(uuid);
    }

    @Override
    @Transactional
    public void deleteImage(UUID uuid) {
        Image image = findByUuid(uuid).orElseThrow(() -> new ImageNotFoundExceptions(IMAGE_NOT_FOUND));
        repository.delete(image);
    }

    @Override
    public Image createImage(CreateImageRequest request) {
        Optional<Image> optionalImage = repository.findByName(request.getName());
        if(optionalImage.isPresent()){
            return optionalImage.get();
        }
        else {
            return repository.save(new Image(request.getName()));
        }
    }

    @Override
    public Optional<Image> updateImage(CreateImageRequest request, UUID uuid) {
        Optional<Image> optionalImage = repository.findByName(request.getName());
        if(optionalImage.isPresent()){
            Image image = optionalImage.get();
            image.setName(request.getName());
            return Optional.of(repository.save(image));
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public long count() {
        return repository.count();
    }
}
