package com.polyfrontiere.data.utils;

public enum FileSystemPaths {
    PATH_QR_IMAGES("src/main/resources/image/");
    private String path;

    FileSystemPaths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
