package com.example.imageUI.web;

import com.example.imageUI.common.ImageFull;
import com.example.imageUI.domain.Image;
import com.example.imageUI.exceptions.TagNotFoundExceptions;
import com.example.imageUI.service.Imp.ImWithTagsServiceImp;
import com.example.imageUI.service.Imp.ImageServiceImp;
import com.example.imageUI.service.Imp.TagServiceImp;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Route("mainWindow")
public class MainWindow extends AppLayout {
    VerticalLayout layout;
    HorizontalLayout horizontalLayout;

    Grid<ImageFull> grid;

    MessageInput input;
    List<ImageFull> fulls;

    Button button;

    @Autowired
    ImageServiceImp imageServiceImp;
    @Autowired
    ImWithTagsServiceImp imWithTagsServiceImp;

    @Autowired
    TagServiceImp tagServiceImp;

    public MainWindow() {
        layout = new VerticalLayout();
        horizontalLayout = new HorizontalLayout();

        button = new Button("Refresh");
        grid = new Grid<>();

        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
        Upload upload = new Upload(buffer);

        upload.addSucceededListener(event -> {
            imageServiceImp.createImage(buffer, event.getFileName());

            refreshAll();
        });

        input = new MessageInput();
        input.setMinWidth("200");
        input.addSubmitListener(submitEvent -> {
            Notification.show("Filter: " + submitEvent.getValue(),
                    3000, Notification.Position.MIDDLE);

            grid.setItems(getList(submitEvent.getValue()));

        });

        button.addClickListener(buttonClickEvent -> refreshAll());

        horizontalLayout.add(upload);
        horizontalLayout.add(input);
        horizontalLayout.add(button);
        horizontalLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        layout.add(horizontalLayout);
        layout.add(grid);

        setContent(layout);
    }

    @PostConstruct
    public void fillGrid() {
        grid.addColumn(ImageFull::getName).setHeader("Name");
        grid.addColumn(ImageFull::getTags).setHeader("Tags");

        grid.addColumn(new NativeButtonRenderer<>("Редактировать", contact -> UI.getCurrent().navigate(FullTags.class).ifPresent(fullTags ->
        {
            fullTags.setImage(imageServiceImp.findByName(contact.getName()).get());

            fullTags.refreshAll();
        })));

        grid.setItems(getList());
    }

    public void refreshAll() {
        grid.setItems();
        grid.setItems(getList());
    }

    public List<ImageFull> getList() {
        fulls = new ArrayList<>();

        imageServiceImp.findAll().forEach(image -> {
            List<String> tags = new ArrayList<>();
            imWithTagsServiceImp.findById_Im(image.getId()).forEach(imWithTags -> tags.add(imWithTags.getId_tg().getName()));

            fulls.add(new ImageFull(image.getName(), tags));
        });

        return fulls;
    }

    public List<ImageFull> getList(String nameTag) {
        fulls = new ArrayList<>();

        System.out.println(nameTag);
        imWithTagsServiceImp.findById_Tg(tagServiceImp.findByName(nameTag).orElseThrow(() -> new TagNotFoundExceptions("Tag not found")).getId()).forEach(imWithTags -> {
            Image image = imageServiceImp.findById(imWithTags.getId_im()).get();
            System.out.println(image.getName());

            List<String> tags = new ArrayList<>();
            imWithTagsServiceImp.findById_Im(image.getId()).forEach(imWithTags2 -> tags.add(imWithTags2.getId_tg().getName()));

            fulls.add(new ImageFull(image.getName(), tags));
        });

        return fulls;
    }

}
