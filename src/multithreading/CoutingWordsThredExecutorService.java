package multithreading;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.*;

public class CoutingWordsThredExecutorService {

    int proc = Runtime.getRuntime().availableProcessors(); // Количество процессоров
    // чтобы не вывалиться по нехватке памяти, очередь всегда надо ограничивать!
    // Это может произовйсти если первый поток заполняет очередь быстрее чем остальные берут оттуда.
    BlockingQueue<String> blockingLine = new ArrayBlockingQueue<>(20);
    BlockingQueue<Map<String, Integer>> resultHM = new ArrayBlockingQueue<>(proc);
    Map<String, Integer> countWords = new TreeMap();
    String stop = new String();
    List<Future> futures = new ArrayList<>(); 

    // Создадим пул потоков
    ExecutorService pool = Executors.newFixedThreadPool(proc);


    public static void main(String[] args) throws IOException, InterruptedException {

        CoutingWordsThredExecutorService wp = new CoutingWordsThredExecutorService();
        wp.start("D:\\Учеба JAVA\\ДЗ\\wp\\wp.txt"); //"D:\Java\Лекии_Задания\wp\wp.txt"

        // Покажем максимально количество повторений
        wp.showMaxRepead(10); // последние 10
    }

    private void waitForCompletion(){

        // Подождем пока все выполнится
        pool.shutdown();

        // Заполним результирующую мапу
        for (Map<String, Integer> map : resultHM)
            for (Map.Entry<String, Integer> entry : map.entrySet())
                countWords.merge(entry.getKey(), entry.getValue(), (integer, integer2) -> integer + integer2);

    }

    public void showMaxRepead(int size){

        // Подождем пока все выполнится
        waitForCompletion();

        // Создадим лист entrySet из мапы, и отсортируем коллекцию по значению
        List<Map.Entry<String, Integer>> listResult = new ArrayList(countWords.entrySet());
        Collections.sort(listResult, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        int i = 1;
        for (Map.Entry<String, Integer> entry : listResult){
            System.out.println(entry);
            if (++i > size) break;
        }

    }

    public void start(String fileName) throws IOException, InterruptedException {

        List<String> lines = Files.readAllLines(new File(fileName).toPath());

//        for (int i = 0; i < proc; i++) { // Так не работает!
//            threads.add(new ReadWords());
//        }
//        pool.invokeAll(threads);

        for (int i = 0; i < proc; i++)
            pool.submit(new ReadWords());

        // Добавим в очередь строки
        for (String line : lines)
            blockingLine.put(line);

        // добавим стопы
        for (int i = 0; i < proc; i++)
            blockingLine.put(stop);


    }
    protected class ReadWords implements Callable{

        Map<String, Integer> countWords = new HashMap<>();
        String line;

        // подсчитаем переданное количество слов
        @Override
        public Map<String, Integer> call() {

            // Проверяем флаг: не было ли выполение потока прервано,
            // если да, то выходим
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    line = blockingLine.take();

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

                } catch (InterruptedException e) {
                    // Если выбрасывается исключение InterruptedException,
                    // то флаг (isInterrupted()) не переводится в true. Для этого
                    // вручную вызывается метод interrupt() у текущего потока.
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
            // загрузим обработанные слова в очередь для для последующего сбора
           return countWords;
        }
    }

}
