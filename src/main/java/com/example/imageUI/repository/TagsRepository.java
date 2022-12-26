package com.example.imageUI.repository;

import com.example.imageUI.domain.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepository extends CrudRepository<Tag, Integer> {
}
