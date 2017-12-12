package Streams;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class TopWords {

    public static void main(String[] args) throws IOException {

        File file = new File("D:\\Учеба JAVA\\ДЗ\\wp\\wp.txt");

        Map<Integer, List<String>> result =
                Files.lines(file.toPath())
                .parallel()
                .map(line -> line.toLowerCase().replaceAll("\\pP", " ")) // \\pP сокращенное p{Punct} (символы пунктуации, т.е. один из !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~;)
                .flatMap(line -> Arrays.stream(line.split("\\s"))) // Ищем пробелы и разбиваем на массив строк, преобразуем в стримы (\s - пробельный символ, т.е. [ \t\n\x0B\f\r];)
                .map(String::trim) // Для каждого стрима, который уже соджержит слова вызываем трим (СокрЛП()) ;)
                .filter(word -> !"".equals(word)) // Наложим фильтр, удалим пустые слова...
                .collect(groupingBy(word -> word.length())); // Создадим коллекции слов по длинне.
//                .entrySet().parallelStream() // Создадим паралельный стрим ентрисетов
//                .sorted(Comparator.comparing(Map.Entry::getKey)) // Сортируем по значению ключа. В такую конструкцию система приводит из (o1, o2) -> o1.getKey().compareTo(o2.getKey())
//                .limit(10) // Оставим 10 элементов после сортировки
//                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue)); // Соберем мапу после сортировки и обрезки

        System.out.println(result);

    }

}
