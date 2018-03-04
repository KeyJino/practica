package com.exsposit.practica.Beans;

import com.exsposit.practica.Utilites.Downloader;

import java.util.List;

public class LoaderBean {


    protected void download(List<File> files, String path) {

        for (File file : files) {
            new Downloader(file.getUrl(), path, file.getName()).startDownload();
            System.out.println(" ============================== ");
        }

    }
}
