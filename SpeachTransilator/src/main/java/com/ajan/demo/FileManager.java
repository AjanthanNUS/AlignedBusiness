package com.ajan.demo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

public class FileManager {

    public static List<String> readFileByLines(String fileName) {
        List<String> readLines = new ArrayList<>();
        URL url = ClassLoader.getSystemClassLoader().getResource(fileName);
        if (url != null) {
            File file = new File(url.getFile());
            try (Stream<String> stream = Files.lines(Paths.get(file.toURI()))) {
                stream.forEach(line -> {
                            readLines.add(line);
                            System.out.println(line);
                        }
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return readLines;
    }

    public static Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream resourceStream = ClassLoader.getSystemClassLoader().getResourceAsStream("langmaps.properties")) {
            props.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public static void writeToFile(String filePath, String content) {
        try {
                Files.write(Paths.get(filePath), content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
