package com.example.imageUI.repository;

import com.example.imageUI.domain.ImWithTags;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface imWithTagsRepository extends CrudRepository<ImWithTags, Integer> {
}
