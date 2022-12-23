package com.example.imageUI.web;

import javax.swing.*;
import java.io.File;

public class FileChooser extends JFrame {

    JFileChooser fileOpen;

    public FileChooser(){
        fileOpen = new JFileChooser();
        System.out.println("a");
    }

    public String getFilePath(){
        JFrame frame = new JFrame();
        frame.setSize(100,100);

        String str = "";
        frame.show();
        int ret = fileOpen.showDialog(frame, "Открыть файл");
        frame.show();
        System.out.println("b");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileOpen.getSelectedFile();
            System.out.println(file.getName() + file.getAbsolutePath());
            str = file.getName() + file.getAbsolutePath();
        }

        return str;
    }
}
