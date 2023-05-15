package com.mjc.school.repository.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public List<String> readFileContent(String path) throws IOException {
        List<String> content = new ArrayList<>();
        try (InputStream stream = this.getClass().getResourceAsStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            while (reader.ready()) {
                content.add(reader.readLine());
            }
        } catch ( IOException | NullPointerException e) {
            throw new IOException("Resource not found: "+path);
        }
        return content;
    }
}
