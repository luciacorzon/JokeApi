package org.example;

public class ChisteMain {
    public static void main(String[] args) {
        Runnable downloader = ChisteDownloader.getInstance();
        downloader.run();
    }
}
