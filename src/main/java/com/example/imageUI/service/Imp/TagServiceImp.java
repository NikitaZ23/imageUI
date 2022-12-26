package com.example.imageUI.service.Imp;

import com.example.imageUI.domain.Tag;
import com.example.imageUI.dto.request.CreateTagRequest;
import com.example.imageUI.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TagServiceImp implements TagService {
    @Override
    public Tag createTag(CreateTagRequest request) {
        return null;
    }

    @Override
    public Iterable<Tag> findAll() {
        return null;
    }

    @Override
    public void deleteTag(UUID uuid) {

    }

    @Override
    public Optional<Tag> findByUuid(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public Optional<Tag> updateTag(CreateTagRequest request, UUID uuid) {
        return Optional.empty();
    }
}
