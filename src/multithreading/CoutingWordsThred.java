package multithreading;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class CoutingWordsThred {

    public static void main(String[] args) throws IOException, InterruptedException {

        int proc = Runtime.getRuntime().availableProcessors(); // Количество процессоров
        Map<String, Integer> countWords = new TreeMap();
        List<String> words = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        List<String> lines = Files.readAllLines(new File("D:\\Java\\Лекии_Задания\\wp\\wp.txt").toPath()); //"D:\\Учеба JAVA\\ДЗ\\wp\\wp.txt"

        for (String line : lines){

            String[] lineWords =
                    line.toLowerCase() // перевод в нижний регистр
                            .replaceAll("\\p{Punct}", " ")
                            .trim() // убираем пробелы по краям
                            .split("\\s");

            for (String word : lineWords)
                if (word.length() > 0)
                    words.add(word);
        }

        for (int i = 0; i < proc; i++) {
            // Запишем в массив потоки. В параметр запишем нужную часть часть массива строк для обработки.
            int frIdx = words.size() / proc * i;
            int toIdx = i == proc - 1 ? words.size() : words.size() / proc * (i + 1) - 1;
            threads.add(new ReadWords(countWords, words.subList(frIdx, toIdx)));
        }

        // Запустим потоки
        for (Thread thread : threads)
            thread.start();

        // Подождем пока все выполнится
        for (Thread thread : threads)
            thread.join();

        List<Map.Entry<String, Integer>> listResult = new ArrayList(countWords.entrySet());
        Collections.sort(listResult, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        int i = 1;
        for (Map.Entry<String, Integer> entry : listResult){
            System.out.println(entry);
            if (++i > 100) break; // обговорить!
        }

    }

    static class ReadWords extends Thread{

        Map<String, Integer> countWords = new HashMap<>();
        final Map<String, Integer> countWordsGeneral;
        final List<String> words;

        ReadWords(Map<String, Integer> countWordsGeneral, List<String> words) {
            this.countWordsGeneral = countWordsGeneral;
            this.words = words;
        }

        @Override
        public void run() {
            // подсчитаем переданное количество слов
            for (String word : words)
                countWords.merge(word, 1, (integer, integer2) -> integer + integer2);
            // загрузим обработанные слова в главную мапу
            synchronized (countWordsGeneral){
                for (Map.Entry<String, Integer> entry : countWords.entrySet())
                    countWordsGeneral.merge(entry.getKey(), entry.getValue(), (integer, integer2) -> integer + integer2);
            }
        }
    }

}
