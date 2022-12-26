package com.example.imageUI.service;

import com.example.imageUI.domain.Image;
import com.example.imageUI.dto.request.CreateImageRequest;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

public interface ImageService {
    Iterable<Image> findAll();

    Optional<Image> findByUuid(UUID uuid);

    void deleteImage(UUID uuid);

    Image createImage(@NotNull CreateImageRequest request);

    Optional<Image> updateImage(@NotNull CreateImageRequest request, UUID uuid);

    long count();
}
