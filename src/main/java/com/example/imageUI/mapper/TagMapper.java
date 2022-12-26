package com.example.imageUI.mapper;

import com.example.imageUI.domain.Tag;
import com.example.imageUI.dto.TagDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagDto map(Tag tag);

    Iterable<TagDto> map(Iterable<Tag> tags);
}
