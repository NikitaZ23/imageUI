package com.example.imageUI.mapper;

import com.example.imageUI.domain.ImWithTags;
import com.example.imageUI.dto.ImWithTagsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageWithTagMapper {

    ImWithTagsDto map(ImWithTags tag);

    Iterable<ImWithTagsDto> map(Iterable<ImWithTags> tags);
}
