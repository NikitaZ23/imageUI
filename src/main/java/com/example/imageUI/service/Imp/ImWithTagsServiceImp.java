package com.example.imageUI.service.Imp;

import com.example.imageUI.domain.ImWithTags;
import com.example.imageUI.domain.Tag;
import com.example.imageUI.dto.request.CreateIWTRequest;
import com.example.imageUI.dto.request.CreateTagRequest;
import com.example.imageUI.exceptions.ImageNotFoundExceptions;
import com.example.imageUI.repository.ImWithTagsRepository;
import com.example.imageUI.service.ImWithTagsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ImWithTagsServiceImp implements ImWithTagsService {

    public static final String DEPENDENCY_NOT_FOUND = "Dependency Not Found";
    private final ImWithTagsRepository repository;

    TagServiceImp tagService;

    @Override
    @Transactional
    public void createIWT(int id_im, List<String> tags) {
        List<Tag> list = new ArrayList<>();

        tags.stream().forEach(tag -> list.add(tagService.createTag(new CreateTagRequest(tag))));

        deleteBy_IdIm(id_im);
        list.stream().forEach(tag -> createIWT(new CreateIWTRequest(id_im, tag)));
    }

    @Override
    @Transactional
    public void deleteBy_IdIm(int id_im) {
        Iterable<ImWithTags> imWithTags = repository.findById_Im(id_im);
        imWithTags.forEach(imWithTags1 -> repository.delete(imWithTags1));
    }

    @Override
    @Transactional
    public void deleteBy_IdTg(int id_tg) {
        Iterable<ImWithTags> imWithTags = repository.findById_Tg(id_tg);
        imWithTags.forEach(imWithTags1 -> repository.delete(imWithTags1));
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        ImWithTags imWithTag = repository.findByUuid(uuid).orElseThrow(() -> new ImageNotFoundExceptions(DEPENDENCY_NOT_FOUND));
        repository.delete(imWithTag);
    }

    @Override
    @Transactional
    public ImWithTags createIWT(CreateIWTRequest request) {
        Optional<ImWithTags> object = repository.findByOneObject(request.getId_im(), request.getId_im());

        if (object.isPresent())
            return object.get();
        else
            return repository.save(new ImWithTags(request.getId_im(), request.getId_tg()));
    }

    @Override
    public Iterable<ImWithTags> findAll() {
        return repository.findAll();
    }

    public Optional<ImWithTags> findByUuid(UUID id) {
        return repository.findByUuid(id);
    }

    @Override
    public Iterable<ImWithTags> findById_Im(int id_im) {
        return repository.findById_Im(id_im);
    }

    @Override
    public Iterable<ImWithTags> findById_Tg(int id_tg) {
        return repository.findById_Tg(id_tg);
    }
}
