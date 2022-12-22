package com.example.imageUI.web;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.router.Route;

import java.awt.*;
import java.util.Collection;

@Route("mainWindow")
public class mainWindow extends AppLayout {
    VerticalLayout layout;

    Button bLoad;

    Button bFullSp;

    public mainWindow(){
        layout = new VerticalLayout(this);
        EmailField emailField = new EmailField();
        emailField.setLabel("Email address");
        layout.add(emailField);
    }
}
