package com.example.imageUI.web;

import com.example.imageUI.common.ImageFull;
import com.example.imageUI.common.ImaggaVision;
import com.example.imageUI.domain.ImWithTags;
import com.example.imageUI.domain.Image;
import com.example.imageUI.dto.request.CreateImageRequest;
import com.example.imageUI.service.Imp.ImWithTagsServiceImp;
import com.example.imageUI.service.Imp.ImageServiceImp;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.Route;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Route("mainWindow")
public class MainWindow extends AppLayout {
    VerticalLayout layout;

    Button bFullSp;

    Grid<ImageFull> grid;

    @Autowired
    ImageServiceImp imageServiceImp;
    @Autowired
    ImWithTagsServiceImp imWithTagsServiceImp;

    public MainWindow() throws IOException {
        layout = new VerticalLayout();

        //bFullSp = new Button("Полный список");
        grid = new Grid<>();

        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
        Upload upload = new Upload(buffer);

        upload.addSucceededListener(event -> {
            String fileName = event.getFileName();
            InputStream inputStream = buffer.getInputStream(fileName);
            System.out.println(fileName);

            File file = new File("./pictures/" + fileName);
            try {
                FileUtils.copyInputStreamToFile(inputStream, file);
                ImaggaVision imaggaVision = new ImaggaVision(file.getPath());

                //System.out.println(imaggaVision.getParseJson().getList());
                imageServiceImp.createImage(new CreateImageRequest(fileName), imaggaVision.getParseJson().getList());

                grid.getDataProvider().refreshAll();
                refreshAll();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        //layout.add(bLoad);
        //layout.add(bFullSp);
        layout.add(upload);
        layout.add(grid);

        setContent(layout);
    }

    @PostConstruct
    public void fillGrid() {
        List<ImageFull> fulls = new ArrayList<>();

        imageServiceImp.findAll().forEach(image -> {
            List<String> tags = new ArrayList<>();
            imWithTagsServiceImp.findById_Im(image.getId()).forEach(imWithTags -> tags.add(imWithTags.getId_tg().getName()));

            fulls.add(new ImageFull(image.getName(), tags));
        });

        grid.addColumn(ImageFull::getName).setHeader("Name");
        grid.addColumn(ImageFull::getTags).setHeader("Tags");

        //grid.addColumn(Image::getList).setHeader("Tags");
//        grid.addColumn(new NativeButtonRenderer<Image>("Редактировать", contact -> {
//            UI.getCurrent().navigate(FullTags.class);
//        }));

        grid.setItems(fulls);
    }

    public void refreshAll() {
        grid.setItems();
        List<ImageFull> fulls = new ArrayList<>();

        imageServiceImp.findAll().forEach(image -> {
            List<String> tags = new ArrayList<>();
            imWithTagsServiceImp.findById_Im(image.getId()).forEach(imWithTags -> tags.add(imWithTags.getId_tg().getName()));

            fulls.add(new ImageFull(image.getName(), tags));
        });
        grid.setItems(fulls);
    }

}
