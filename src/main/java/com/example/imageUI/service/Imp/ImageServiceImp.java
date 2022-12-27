package com.example.imageUI.service.Imp;

import com.example.imageUI.domain.ImWithTags;
import com.example.imageUI.domain.Image;
import com.example.imageUI.dto.request.CreateImageRequest;
import com.example.imageUI.exceptions.ImageNotFoundExceptions;
import com.example.imageUI.repository.ImagesRepository;
import com.example.imageUI.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ImageServiceImp implements ImageService {
    public static final String IMAGE_NOT_FOUND = "Image not found";
    private final ImagesRepository repository;

    private final ImWithTagsServiceImp serviceImp;

    @Override
    public Iterable<Image> findAll() {
        Iterable<Image> images = repository.findAll();

        images.forEach(image ->
        {
            List<String> list = new ArrayList<>();
            Iterable<ImWithTags> imWithTags = serviceImp.findById_Im(image.getId());
            imWithTags.forEach(imWithTags1 -> list.add(String.valueOf(imWithTags1.getId_tg())));
        });

        return images;
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
    @Transactional
    public Image createImage(CreateImageRequest request, List<String> list) {
        Image image;

        Optional<Image> optionalImage = repository.findByName(request.getName());
        if (optionalImage.isPresent()) {
            image = optionalImage.get();
        } else {
            image = new Image(request.getName());
            repository.save(image);
        }
        serviceImp.createIWT(image.getId(), list);

        return image;
    }

    @Override
    @Transactional
    public Optional<Image> updateImage(CreateImageRequest request, UUID uuid) {
        Optional<Image> optionalImage = repository.findByName(request.getName());
        if (optionalImage.isPresent()) {
            Image image = optionalImage.get();
            image.setName(request.getName());
            return Optional.of(repository.save(image));
        } else {
            return Optional.empty();
        }
    }
}
