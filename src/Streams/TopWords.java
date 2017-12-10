package Streams;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.Set;

public class TopWords {

    public static void main(String[] args) throws IOException {

        File file = new File("D:\\Учеба JAVA\\ДЗ\\wp\\wp.txt");

        Map<Integer, Set<String>> result =
                Files.lines(file.toPath())
                .parallel()
                .map()

    }

}
