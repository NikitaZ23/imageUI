package com.example.imageUI.service;

import com.example.imageUI.domain.Tag;
import com.example.imageUI.repository.TagRepository;
import com.example.imageUI.service.Imp.TagServiceImp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@ExtendWith(MockitoExtension.class)
public class TagServiceTest {
    @Mock
    TagRepository tagRepository;

    @InjectMocks
    TagServiceImp tagServiceImp;

    @Test
    @DisplayName("Проверка получения пустого репозитория")
    public void findAll() {
        Iterable<Tag> all = tagServiceImp.findAll();

        assertThat(all).isEmpty();
    }
}
