package net.olyts.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@UtilityClass
public class FileUtils {

    private static List<String> readFile(Path filePath) {
        try (Stream<String> lines = Files.lines(filePath)) {
            return lines.toList();
        } catch (IOException e) {
            log.warn("IOException encountered while reading file at " + filePath);
            return new ArrayList<>();
        }
    }

    public static List<String> readFile(String filePath) {
        return readFile(Paths.get(filePath));
    }

}
