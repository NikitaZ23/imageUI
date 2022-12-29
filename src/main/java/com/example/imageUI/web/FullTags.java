package com.example.imageUI.web;

import com.example.imageUI.domain.Image;
import com.example.imageUI.domain.Tag;
import com.example.imageUI.service.Imp.ImWithTagsServiceImp;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;


@Route("tags")
public class FullTags extends AppLayout {
    Grid<Tag> grid;
    Label label;

    Image image;

    Button buttonEnd;

    @Autowired
    ImWithTagsServiceImp imWithTagsServiceImp;

    public FullTags() {
        VerticalLayout layout = new VerticalLayout();
        grid = new Grid<>();

        label = new Label();
        buttonEnd = new Button("Назад");
        buttonEnd.addClickListener((event -> UI.getCurrent().navigate(MainWindow.class)));

        layout.add(label);
        layout.add(grid);
        layout.add(buttonEnd);
        setContent(layout);
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void refreshAll() {
        grid.setItems();

        label.setText(image.getName());

        grid.setItems(imWithTagsServiceImp.getTags(image.getId()));
    }

    @PostConstruct
    public void fillGrid() {
        grid.addColumn(Tag::getName).setHeader("Name");
        grid.addColumn(new NativeButtonRenderer<>("Удалить", contact -> {
            System.out.println(contact.getName() + contact.getId());
            imWithTagsServiceImp.delete(imWithTagsServiceImp.findByOneObject(image.getId(), contact.getId()).getUuid());
            refreshAll();
        }));
    }
}
