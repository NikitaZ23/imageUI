package com.example.imageUI.repository;

import com.example.imageUI.domain.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository extends CrudRepository<Image, Integer> {
}
