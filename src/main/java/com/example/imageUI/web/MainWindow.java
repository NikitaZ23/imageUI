package com.example.imageUI.web;

import com.example.imageUI.common.ImaggaVision;
import com.example.imageUI.domain.Images;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.Route;
import org.apache.commons.io.FileUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Route("mainWindow")
public class MainWindow extends AppLayout {
    VerticalLayout layout;

    Button bFullSp;

    Grid<Images> grid;
    Images images;

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
                //imaggaVision.getParseJson().getMap();
                images = new Images(fileName, imaggaVision.getParseJson().getMap());
                grid.getDataProvider().refreshAll();
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
    public void fillGrid(){
        grid.addColumn(Images::getName).setHeader("Name");
        grid.addColumn(new NativeButtonRenderer<Images>("Редактировать", contact -> {
            UI.getCurrent().navigate(FullTags.class);
        }));

        grid.setItems(images);
    }

}
