package com.example.imageUI.service;

import com.example.imageUI.domain.ImWithTags;
import com.example.imageUI.domain.Image;
import com.example.imageUI.domain.Tag;
import com.example.imageUI.dto.request.CreateImageRequest;
import com.example.imageUI.repository.ImWithTagsRepository;
import com.example.imageUI.repository.ImagesRepository;
import com.example.imageUI.repository.TagRepository;
import com.example.imageUI.service.Imp.ImWithTagsServiceImp;
import com.example.imageUI.service.Imp.ImageServiceImp;
import com.example.imageUI.service.Imp.TagServiceImp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ImageServiceTest {

    @Mock
    ImagesRepository repository;
    @Mock
    ImWithTagsRepository repositoryIm;
    @Mock
    TagRepository tagRepository;

    @InjectMocks
    ImageServiceImp service;
    @InjectMocks
    ImWithTagsServiceImp serviceImp;
    @InjectMocks
    TagServiceImp tagServiceImp;


    @Test
    @DisplayName("Проверка получения пустого репозитория")
    public void findAllEmptyTest() {
        Iterable<Image> all = service.findAll();

        assertThat(all).isEmpty();
    }

    @Test
    @DisplayName("Проверка получения всех картинок")
    public void findAllTest() {
        Image image = new Image("a");
        Image image2 = new Image("b");

        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(image, image2));

        Iterable<Image> all = service.findAll();

        assertThat(Arrays.asList(image, image2)).isEqualTo(all);
    }

    @Test
    @DisplayName("Проверка получения картинки по uuid")
    public void findImageTest() {
        Image image = new Image("a");

        Mockito.when(repository.findByUuid(Mockito.any())).thenReturn(Optional.of(image));

        Optional<Image> byUuid = service.findByUuid(UUID.randomUUID());

        assertThat(byUuid).isEqualTo(Optional.of(image));
    }

    @Test
    @DisplayName("Проверка удаления картинки")
    public void deleteImageTest() {
        Image image = new Image("a");

        Mockito.when(repository.findByUuid(Mockito.any())).thenReturn(Optional.of(image));

        service.deleteImage(UUID.randomUUID());

        assertThat(repository.count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Проверка обновления картинки")
    public void updateImageTest() {
        Image image = new Image("1");
        Image image2 = new Image("2");

        Mockito.when(repository.save(Mockito.any())).thenReturn(image2);
        Mockito.when(repository.findByUuid(Mockito.any())).thenReturn(Optional.of(image));

        Optional<Image> image3 = service.updateImage(new CreateImageRequest("2"), UUID.randomUUID());

        assertThat(image3).isEqualTo(Optional.of(image2));
    }

//    @Test
//    @DisplayName("Проверка создания картинки")
//    public void createImage() {
//        Image image = new Image("1");
//        Tag tag = new Tag("tag");
//        ImWithTags imt = new ImWithTags(1, tag);
//
//        List<String> list = new ArrayList<>();
//        list.add("tag1");
//
//        Mockito.when(repository.findByName(Mockito.anyString())).thenReturn(Optional.empty());
//        Mockito.when(repository.save(Mockito.any())).thenReturn(image);
//
//        Mockito.when(repositoryIm.save(Mockito.any())).thenReturn(imt);
//        Mockito.when(repositoryIm.findByOneObject(Mockito.anyInt(), Mockito.anyInt())).thenReturn(Optional.empty());
//
//        Mockito.when(tagRepository.findByName(Mockito.anyString())).thenReturn(Optional.of(tag));
//        Mockito.when(tagRepository.save(Mockito.any())).thenReturn(tag);
//
//        Image image2 = service.createImage(new CreateImageRequest("1"), list);
//
//        assertThat(image).isEqualTo(image2);
//    }
}
