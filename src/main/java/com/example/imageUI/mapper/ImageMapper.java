package com.example.imageUI.mapper;

import com.example.imageUI.domain.Image;
import com.example.imageUI.dto.ImageDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageDto map(Image image);

    Iterable<ImageDto> map(Iterable<Image> images);
}
