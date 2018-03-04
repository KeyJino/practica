package com.exsposit.practica.Beans;

import com.exsposit.practica.Utilites.Downloader;

import java.util.List;

public class LoaderBean {

    private int countTreads = 0;

    protected void download(List<File> files, String path, String nThreads) {


        int n = Integer.parseInt(nThreads);
        int nLastThreadsNumber = files.size() % n;
        java.lang.Thread[] threads;

        while(countTreads < files.size()) {
            if(countTreads + nLastThreadsNumber == files.size()) {
                threads = new java.lang.Thread[nLastThreadsNumber];
            }
            else {
                threads = new java.lang.Thread[n];
            }

            for (int j = 0; j < n && j + countTreads != files.size(); ++j) {
                threads[j] = new java.lang.Thread(new Thread(files.get(countTreads + j), path),
                        String.format("Thread %d", j));
            }

            if(countTreads + threads.length <= files.size()) {
                countTreads = countTreads + threads.length;
            }
            startThreads(threads);
        }
    }

    private static void startThreads(java.lang.Thread[] threads) {
            for (java.lang.Thread thread : threads) {
                thread.start();
                continue;

            }
    }
}

class Thread implements Runnable {

    private String path;
    private com.exsposit.practica.Beans.File file;

    Thread(com.exsposit.practica.Beans.File file, String path){
        this.path = path;
        this.file = file;
    }

    public  void run() {
        new Downloader(file.getUrl(), path, file.getName()).startDownload();
        System.out.println(java.lang.Thread.currentThread().getName());
        System.out.println(" ============================== ");
    }

}