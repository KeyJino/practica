package com.exsposit.practica.Loader;

import com.exsposit.practica.Beans.File;
import com.exsposit.practica.Beans.LoaderBean;

import java.util.List;

public class Loader extends LoaderBean{

    String path;
    String threads;
    List<File> files;

    public Loader(String path, List<File> files, String threads) {
        this.path = path;
        this.files = files;
        this.threads = threads;
    }

    public void download() throws InterruptedException {
        super.download(files, path, threads);
    }
}
