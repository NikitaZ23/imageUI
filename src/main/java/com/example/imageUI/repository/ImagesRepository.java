package com.example.imageUI.repository;

import com.example.imageUI.domain.Images;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository extends CrudRepository<Images, Integer> {
}
