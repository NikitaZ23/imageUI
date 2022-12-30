package com.example.imageUI.service;

import com.example.imageUI.domain.ImWithTags;
import com.example.imageUI.domain.Tag;
import com.example.imageUI.dto.request.CreateIWTRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ImWithTagsService {
    Iterable<ImWithTags> findAll();

    Optional<ImWithTags> findByUuid(UUID id);

    Iterable<ImWithTags> findById_Im(int id_im);
    Iterable<ImWithTags> findById_Tg(int id_tg);

    Optional<ImWithTags> findByOneObject(int id_im, int id_tg);

    void createIWT(int id_im, List<String> tags);

    List<Tag> getTags(int id_im);

    ImWithTags createIWT(CreateIWTRequest request);

    void deleteBy_IdIm(int id_im);

    void deleteBy_IdTg(int id_tg);

    void delete(UUID uuid);
}
