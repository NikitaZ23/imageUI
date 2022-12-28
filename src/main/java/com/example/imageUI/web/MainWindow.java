package com.example.imageUI.web;

import com.example.imageUI.common.ImageFull;
import com.example.imageUI.service.Imp.ImWithTagsServiceImp;
import com.example.imageUI.service.Imp.ImageServiceImp;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Route("mainWindow")
public class MainWindow extends AppLayout {
    VerticalLayout layout;

    Grid<ImageFull> grid;

    @Autowired
    ImageServiceImp imageServiceImp;
    @Autowired
    ImWithTagsServiceImp imWithTagsServiceImp;

    public MainWindow() throws IOException {
        layout = new VerticalLayout();

        grid = new Grid<>();

        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
        Upload upload = new Upload(buffer);

        upload.addSucceededListener(event -> {
            imageServiceImp.createImage(buffer, event.getFileName());

            refreshAll();
        });

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
