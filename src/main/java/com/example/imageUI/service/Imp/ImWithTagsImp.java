package com.example.imageUI.service.Imp;

import com.example.imageUI.domain.ImWithTags;
import com.example.imageUI.service.ImWithTagsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ImWithTagsImp implements ImWithTagsService {
    @Override
    public Iterable<ImWithTags> findAll() {
        return null;
    }

    @Override
    public Optional<ImWithTags> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Iterable<ImWithTags> findById_Im(int id_im) {
        return null;
    }

    @Override
    public Iterable<ImWithTags> findById_Tg(int id_tg) {
        return null;
    }

    @Override
    public void deleteIWT(UUID uuid) {

    }
}
