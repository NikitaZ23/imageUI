package com.example.imageUI.web;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.Route;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Route("mainWindow")
public class MainWindow extends AppLayout {
    VerticalLayout layout;

    Button bFullSp;

    public MainWindow() throws IOException {
        layout = new VerticalLayout();

        bFullSp = new Button("Полный список");

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

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            // Do something with the file data
            // processFile(inputStream, fileName);
        });

        //layout.add(bLoad);
        layout.add(bFullSp);
        layout.add(upload);

        setContent(layout);

        //BufferedImage image = ImageIO.read(new File("apple.jpg"));
        //GoogleVision googleVision = new GoogleVision();
        //ImaggaVision imaggaVision = new ImaggaVision();
    }

}