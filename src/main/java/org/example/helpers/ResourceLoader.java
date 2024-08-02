package org.example.helpers;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class ResourceLoader {

    public static String getResourcePath(String filename) {
        ClassLoader classLoader = ResourceLoader.class.getClassLoader();
        URL resourceUrl = Objects.requireNonNull(classLoader.getResource(filename), "File not found: " + filename);
        String path = resourceUrl.getPath();
        if(path.startsWith("/")) {
            path = path.substring(1);
        }
        Path p = Paths.get(path);
        return p.toString();
    }
}