package com.example.imageUI.web;

import com.example.imageUI.domain.Image;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.Route;

@Route("tags")
public class FullTags extends AppLayout {
    Grid<Image> grid;

    public FullTags(){
        grid = new Grid<>();
    }

//    @PostConstruct
//    public void fillGrid(){
//        grid.addColumn(Information::getTags).setHeader("Name");
//    }
}
