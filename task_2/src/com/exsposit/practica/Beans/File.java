package com.exsposit.practica.Beans;

public class File {
    private String url;
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return this.name + " -- " + this.url;
    }
}
