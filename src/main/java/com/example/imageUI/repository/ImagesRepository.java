package com.example.imageUI.repository;

import com.example.imageUI.domain.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImagesRepository extends CrudRepository<Image, Integer> {
    Optional<Image> findByUuid(UUID uuid);

    Optional<Image> findByName(String name);
}
