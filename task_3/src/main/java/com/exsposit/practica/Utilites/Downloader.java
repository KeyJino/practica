package com.exsposit.practica.Utilites;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Downloader {

    private String url;
    private String path;
    private String name;

    public Downloader(String url, String path, String name){
        this.url = url;
        this.path = path;
        this.name = name;
    }

    public void startDownload(){
        try {
            downloadUsingStream(url, path + "/" + name);
            System.out.println("Download is successfully!");
            System.out.println("Url : " + url);
            System.out.println("Path : " + path);
            System.out.println("File name : " + name);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private static void downloadUsingStream(String urlStr, String file) throws IOException {
        try {
            URL url = new URL(urlStr);
            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            FileOutputStream fis = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int count;
            while ((count = bis.read(buffer, 0, 1024)) != -1) {
                fis.write(buffer, 0, count);
            }
            fis.close();
            bis.close();
        } catch (FileNotFoundException e) {
            System.err.println("1)File not found \n2)Access denied \ncheck please and repeat");
            System.exit(0);
        }
    }
}