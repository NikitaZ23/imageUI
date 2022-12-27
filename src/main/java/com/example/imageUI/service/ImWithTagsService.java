package com.example.imageUI.service;

import com.example.imageUI.domain.ImWithTags;
import com.example.imageUI.dto.request.CreateIWTRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ImWithTagsService {
    Iterable<ImWithTags> findAll();

    Optional<ImWithTags> findById(int id);

    Iterable<ImWithTags> findById_Im(int id_im);
    Iterable<ImWithTags> findById_Tg(int id_tg);

    void deleteIWT(UUID uuid);

    void createIWT(int id_im, List<String> tags);

    void createIWT(CreateIWTRequest request);

    void deleteBy_Id(int id_im);
}
