package com.example.imageUI.service.Imp;

import com.example.imageUI.common.ImaggaVision;
import com.example.imageUI.domain.ImWithTags;
import com.example.imageUI.domain.Image;
import com.example.imageUI.dto.request.CreateImageRequest;
import com.example.imageUI.exceptions.ImageNotFoundExceptions;
import com.example.imageUI.repository.ImagesRepository;
import com.example.imageUI.service.ImageService;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ImageServiceImp implements ImageService {
    public static final String IMAGE_NOT_FOUND = "Image not found";
    private final ImagesRepository repository;

    private final ImWithTagsServiceImp serviceImp;

    @Override
    public Iterable<Image> findAll() {
        Iterable<Image> images = repository.findAll();

        images.forEach(image ->
        {
            List<String> list = new ArrayList<>();
            Iterable<ImWithTags> imWithTags = serviceImp.findById_Im(image.getId());
            imWithTags.forEach(imWithTags1 -> list.add(String.valueOf(imWithTags1.getId_tg())));
        });

        return images;
    }

    @Override
    public Optional<Image> findByUuid(UUID uuid) {
        return repository.findByUuid(uuid);
    }

    @Override
    @Transactional
    public void deleteImage(UUID uuid) {
        Image image = findByUuid(uuid).orElseThrow(() -> new ImageNotFoundExceptions(IMAGE_NOT_FOUND));
        repository.delete(image);
    }

    @Override
    @Transactional
    public Image createImage(CreateImageRequest request, List<String> list) {
        Image image;

        Optional<Image> optionalImage = repository.findByName(request.getName());
        if (optionalImage.isPresent()) {
            image = optionalImage.get();
        } else {
            image = new Image(request.getName());
            repository.save(image);
        }
        serviceImp.createIWT(image.getId(), list);

        return image;
    }

    @Override
    @SneakyThrows
    public Image createImage(MultipartFile multipartFile) {
        byte[] bytes = multipartFile.getBytes();
        String name = multipartFile.getOriginalFilename();

        File file = new File("./pictures/" + name);

        BufferedOutputStream stream =
                new BufferedOutputStream(new FileOutputStream(file));
        stream.write(bytes);
        stream.close();

        ImaggaVision imaggaVision = new ImaggaVision(file.getPath());

        Image image = createImage(new CreateImageRequest(name), imaggaVision.getParseJson().getList());

        return image;
    }

    @SneakyThrows
    @Override
    public void createImage(MultiFileMemoryBuffer buffer, String fileName) {
        InputStream inputStream = buffer.getInputStream(fileName);
        System.out.println(fileName);

        File file = new File("./pictures/" + fileName);

        FileUtils.copyInputStreamToFile(inputStream, file);
        ImaggaVision imaggaVision = new ImaggaVision(file.getPath());

        createImage(new CreateImageRequest(fileName), imaggaVision.getParseJson().getList());
    }

    @Override
    @Transactional
    public Optional<Image> updateImage(CreateImageRequest request, UUID uuid) {
        Optional<Image> optionalImage = repository.findByName(request.getName());
        if (optionalImage.isPresent()) {
            Image image = optionalImage.get();
            image.setName(request.getName());
            return Optional.of(repository.save(image));
        } else {
            return Optional.empty();
        }
    }
}
