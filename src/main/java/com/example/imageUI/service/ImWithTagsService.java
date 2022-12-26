package com.example.imageUI.service;

import com.example.imageUI.domain.ImWithTags;

import java.util.Optional;
import java.util.UUID;

public interface ImWithTagsService {
    Iterable<ImWithTags> findAll();

    Optional<ImWithTags> findById(int id);

    Iterable<ImWithTags> findById_Im(int id_im);
    Iterable<ImWithTags> findById_Tg(int id_tg);

    void deleteIWT(UUID uuid);
}
