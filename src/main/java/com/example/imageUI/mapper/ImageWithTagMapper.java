package com.example.imageUI.mapper;

import com.example.imageUI.domain.ImWithTags;
import com.example.imageUI.dto.ImWithTagsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImageWithTagMapper {
    @Mapping(source = "id_tg.id", target = "id_tg")
    ImWithTagsDto map(ImWithTags tag);

    Iterable<ImWithTagsDto> map(Iterable<ImWithTags> tags);
}
