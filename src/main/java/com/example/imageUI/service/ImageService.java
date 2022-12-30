package com.example.imageUI.service;

import com.example.imageUI.domain.Image;
import com.example.imageUI.dto.request.CreateImageRequest;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ImageService {
    Iterable<Image> findAll();

    Optional<Image> findByUuid(UUID uuid);

    Optional<Image> findById(int id);

    void deleteImage(UUID uuid);

    Image createImage(@NotNull CreateImageRequest request, List<String> list);

    Optional<Image> updateImage(@NotNull CreateImageRequest request, UUID uuid);

    Image createImage(@NotEmpty MultipartFile file);

    void createImage(@NotNull MultiFileMemoryBuffer buffer, @NotNull String fileName);

    Optional<Image> findByName(String name);
}
