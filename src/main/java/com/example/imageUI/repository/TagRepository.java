package com.example.imageUI.repository;

import com.example.imageUI.domain.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer> {
    Optional<Tag> findByName(String name);

    Optional<Tag> findByUuid(UUID uuid);
}
