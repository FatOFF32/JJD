package collections;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * Created by xmitya on 07.04.17.
 */
public class ReadWords {
    public static void main(String[] args) throws IOException {
        // Создаем файл, указывая путь к текстовому файлу на диске
        File text = new File("D:\\Учеба JAVA\\ДЗ\\wp\\wp.txt");

        // Вычитываем все строки из файла
        List<String> lines = Files.readAllLines(text.toPath());

        // Создаем пустую коллекцию для слов.
        List<String> words = new ArrayList<>();

        for (String line : lines) {
            // Для каждой строки
            String[] wordSplit =
                    line.toLowerCase() // Переводим в нижний регистр
                    .replaceAll("\\p{Punct}", " ") // Заменяем все знаки на пробел
                    .trim() // Убираем пробелы в начале и конце строки.
                    .split("\\s"); // Разбиваем строки на слова

            for (String s : wordSplit) {
                // Выбираем только непустые слова.
                if (s.length() > 0)
                    words.add(s.trim());
            }
        }

        // Частота встечаемых слов задание 1
        Map<String, Integer> wordCur = new HashMap<>();
        Integer count;
        for (String word : words) {
            if (!word.isEmpty()){
                count = wordCur.get(word);
                if (count == null)
                    count = 0;
                wordCur.put(word, ++count);
            }
        }
//        for (Map.Entry<String, Integer> entry: wordCur.entrySet())
//            System.out.println(entry.getKey() + " : " + entry.getValue());

        // Количество слов по количеству букв задание 2
        ArrayList<String> arr;
        Map<Integer, ArrayList> wordLength = new TreeMap<>();
        for (String word : wordCur.keySet()){
            if (!word.isEmpty()){
                arr = wordLength.get(word.length());
                if (arr != null)
                    arr.add(word);
                else {
                    arr = new ArrayList<>();
                    arr.add(word);
                    wordLength.put(word.length(), arr);
                }
            }
        }

//        for (Map.Entry<Integer, ArrayList> entry: wordLength.entrySet())
//            System.out.println(entry.getKey() + " : " + entry.getValue());

        // Самые встречаемые фразы задание 3
        List<Map.Entry> list = new ArrayList<>(wordCur.entrySet());
        Collections.sort(list, new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                Integer v1 = (Integer) o1.getValue();
                Integer v2 = (Integer) o2.getValue();
                return v2.compareTo(v1);
            }
        });

        System.out.println("Самые популярные слова:");
        int i = 0;
        for (Map.Entry entry : list){
            System.out.println(entry.getKey() + " количество повторений : " + entry.getValue());
            i++;
            if (i == 10) break;
        }

        // Частота встречаемости букв. Задание 5
        double numberOfChar = 0;
        Integer curChar;
        char[] strChar;
        Map<Character, Integer> symbols = new TreeMap<>((o1, o2) -> o1.compareTo(o2));
        for (String word : wordCur.keySet()){
            strChar = word.toCharArray();
            for (char ch : strChar){
                curChar = symbols.get(ch);
                if (curChar == null)
                    curChar = 0;
                curChar += 1;
                symbols.put(ch,curChar);
                numberOfChar++;
            }
        }

        for (Map.Entry<Character, Integer> entry : symbols.entrySet()){
            System.out.println(entry.getKey() + " встречается " + entry.getValue()
                    + " раз, что составляет " + String.format("%.2f", (double) entry.getValue() * 100 / numberOfChar) + "%");
        }


    }
}
