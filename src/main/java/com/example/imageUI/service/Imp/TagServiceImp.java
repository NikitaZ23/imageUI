package com.example.imageUI.service.Imp;

import com.example.imageUI.domain.Tag;
import com.example.imageUI.dto.request.CreateTagRequest;
import com.example.imageUI.exceptions.TagNotFoundExceptions;
import com.example.imageUI.repository.TagRepository;
import com.example.imageUI.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TagServiceImp implements TagService {

    public static final String TAG_NOT_FOUND = "Tag not found";
    private final TagRepository repository;

    @Override
    public Tag createTag(CreateTagRequest request) {
        System.out.println("000000");
        Optional<Tag> tagOptional = repository.findByName(request.getName());
        System.out.println("1111111");
        if (tagOptional.isPresent()) {
            System.out.println("2222222");
            return tagOptional.get();
        } else {
            System.out.println("33333333");
            return repository.save(new Tag(request.getName()));
        }
    }

    @Override
    public Iterable<Tag> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void deleteTag(UUID uuid) {
        Tag tag = findByUuid(uuid).orElseThrow(() -> new TagNotFoundExceptions(TAG_NOT_FOUND));
        repository.delete(tag);
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Optional<Tag> findByUuid(UUID uuid) {
        return repository.findByUuid(uuid);
    }

    @Override
    @Transactional
    public Optional<Tag> updateTag(CreateTagRequest request, UUID uuid) {
        Tag tag = findByUuid(uuid).orElseThrow(() -> new TagNotFoundExceptions(TAG_NOT_FOUND));
        tag.setName(request.getName());
        return Optional.of(repository.save(tag));
    }

    @Override
    public Optional<Tag> findById(int id) {
        return repository.findById(id);
    }
}
