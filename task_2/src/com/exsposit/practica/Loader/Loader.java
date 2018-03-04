package com.exsposit.practica.Loader;

import com.exsposit.practica.Beans.File;
import com.exsposit.practica.Beans.LoaderBean;

import java.util.List;

public class Loader extends LoaderBean{

    String path;
    List<File> files;

    public Loader(String path, List<File> files) {
        this.path = path;
        this.files = files;
    }

    public void download() {
        super.download(files, path);
    }
}
