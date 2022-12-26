package com.example.imageUI.web;

import com.example.imageUI.common.ImaggaVision;
import com.example.imageUI.domain.Image;
import com.example.imageUI.dto.request.CreateImageRequest;
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
import java.util.List;

@Route("mainWindow")
public class MainWindow extends AppLayout {
    VerticalLayout layout;

    Button bFullSp;

    Grid<Image> grid;

    @Autowired
    ImageServiceImp imageServiceImp;

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

                imaggaVision.getParseJson().getList();
                imageServiceImp.createImage(new CreateImageRequest(fileName));
                //images = new Images(fileName, imaggaVision.getParseJson().getMap());
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
    public void fillGrid() {
        List<Image> list = new ArrayList<>();
        list.add(new Image("asd"));
        list.add(new Image("asd"));
        list.add(new Image("asd"));
        list.add(new Image("asd"));

        imageServiceImp.findAll();//.forEach(image1 -> list.add(image1));
        grid.addColumn(Image::getName).setHeader("Name");
//        grid.addColumn(new NativeButtonRenderer<Image>("Редактировать", contact -> {
//            UI.getCurrent().navigate(FullTags.class);
//        }));

        grid.setItems(list);
    }

}
