package multithreading;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CoutingWordsThredBlockQeue {

    int proc = Runtime.getRuntime().availableProcessors(); // Количество процессоров
    // чтобы не вывалиться по нехватке памяти, очередь всегда надо ограничивать!
    // Это может произовйсти если первый поток заполняет очередь быстрее чем остальные берут оттуда.
    BlockingQueue<String> blockingСountWords = new ArrayBlockingQueue<>(20);
    String stop = new String();
    List<Thread> threads = new ArrayList<>();


    public static void main(String[] args) throws IOException, InterruptedException {

        Map<String, Integer> countWords = new TreeMap(); // elfkb
        List<String> words = new ArrayList<>(); // elfkbnm

        CoutingWordsThredBlockQeue wp = new CoutingWordsThredBlockQeue();
        wp.start("D:\\Учеба JAVA\\ДЗ\\wp\\wp.txt");



    }

    public void start(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(new File(fileName).toPath());

        for (int i = 0; i < proc; i++) {
            threads.add(new ReadWords(blockingСountWords, stop));
        }

        // Запустим потоки
        for (Thread thread : threads)
            thread.start();

        for (String line : lines) {
            blockingСountWords.add(line);
        }

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


    }
    protected class ReadWords extends Thread{

        Map<String, Integer> countWords = new HashMap<>();
        String line;
//        final String stop;
//        final BlockingQueue<String> blockingСountWordsGeneral;

//        ReadWords(String line) {
//            this.line = line;
//        }

        @Override
        public void run() {
            // подсчитаем переданное количество слов
            try {
                while (true){
                    line = blockingСountWords.take();

                    if (line == stop)
                        break;

                    String[] lineWords =
                            line.toLowerCase() // перевод в нижний регистр
                                    .replaceAll("\\p{Punct}", " ")
                                    .trim() // убираем пробелы по краям
                                    .split("\\s");

                    for (String word : lineWords)
                        if (word.length() > 0)
                            countWords.merge(word, 1, (integer, integer2) -> integer + integer2);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // загрузим обработанные слова в главную мапу
            synchronized (countWordsGeneral){
                for (Map.Entry<String, Integer> entry : countWords.entrySet())
                    countWordsGeneral.merge(entry.getKey(), entry.getValue(), (integer, integer2) -> integer + integer2);
            }
        }
    }

}
