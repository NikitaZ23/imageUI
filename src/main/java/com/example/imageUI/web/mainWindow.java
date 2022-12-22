package com.example.imageUI.web;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.router.Route;

import java.awt.*;

@Route("mainWindow")
public class mainWindow extends AppLayout {
    VerticalLayout layout;

    Button bLoad;

    Button bFullSp;

    public mainWindow() {
        layout = new VerticalLayout();
        EmailField emailField = new EmailField();
        emailField.setLabel("Email address");

        bLoad = new Button("asd");
        bFullSp = new Button("asd");

        layout.add(emailField);
        layout.addComponent(bLoad);
        layout.add(bFullSp);
        setContent(layout);

    }
}
