package collections;

import collections.inner.MessageGenerator;
import collections.inner.Message;
import collections.inner.MessagePriority;

import java.util.*;

/**
 * Created by xmitya on 17.10.16.
 */
public class Tasks1 {

    public static void main(String[] args) {
        MessageGenerator generator = new MessageGenerator();

        List<Message> messages = generator.generate(100);

        countEachPriority(messages);
        countCountEachCode(messages);
        countUniqueMessages(messages);

        System.out.println("Genuine messages in natural order: \n" + genuineMessagesInOriginalOrder(messages));

        removeEach(generator.generate(100), MessagePriority.LOW);
        removeOther(generator.generate(100), MessagePriority.URGENT);
    }

    private static void countEachPriority(List<Message> messages) {
        // Сосчитайте количество сообщений для каждого приоритета.
        // Ответ необходимо вывести в консоль.

        // Способ 1, до java 8
        Integer cur;
        Map<MessagePriority, Integer> map = new HashMap<>();
        for (Message mes : messages){
            cur = map.get(mes.getPriority());
            if (cur == null)
                cur = 0;
            cur += 1;
            map.put(mes.getPriority(), cur);
        }

        System.out.println(map);

        // Способ 2. Симпатишный но только в JAVA 8 и выше
        Map<MessagePriority, Integer> map1= new HashMap<>();
        for (Message mes : messages)
            map1.merge(mes.getPriority(), 1, (integer, integer2) -> integer + integer2);
        System.out.println(map1);

    }

    private static void countCountEachCode(List<Message> messages) {
        // Сосчитайте количество сообщений для каждого кода сообщения.
        // Ответ необходимо вывести в консоль.

        Integer cur;
        Map<Integer, Integer> map = new HashMap<>();
        for(Message mes : messages){
            cur = map.get(mes.getCode());
            if (cur == null)
                cur = 0;
            cur += 1;
            map.put(mes.getCode(), cur);
        }

        System.out.println(map);

        // Способ 2. Симпатишный но только в JAVA 8 и выше
        Map<Integer, Integer> map1= new HashMap<>();
        for (Message mes : messages) {
            map1.merge(mes.getCode(), 1, (integer, integer2) -> integer + integer2);
        }
        System.out.println(map1);
    }

    private static void countUniqueMessages(List<Message> messages) {
        // Сосчитайте количество уникальных сообщений.
        // Ответ необходимо вывести в консоль.

        HashSet<Message> hashSet = new HashSet<>(messages);

        System.out.println(hashSet.size());
    }

    private static List<Message> genuineMessagesInOriginalOrder(List<Message> messages) {
        // Здесь необходимо вернуть только неповторяющиеся сообщения и в том порядке, в котором
        // они встречаются в первоначальном списке. Например, мы на входе имеем такие сообщения:
        // [{URGENT, 4}, {HIGH, 9}, {LOW, 3}, {HIGH, 9}],
        // то на выходе должны получить:
        // [{URGENT, 4}, {HIGH, 9}, {LOW, 3}].
        // Т.е. остались только уникальные значения, и порядок их поступления сохранен.

        LinkedHashSet<Message> lhs = new LinkedHashSet<>(messages);

        return new ArrayList<>(lhs);
    }

    private static void removeEach(Collection<Message> messages, MessagePriority priority) {
        // Удалить из коллекции каждое сообщение с заданным приоритетом.
        System.out.printf("Before remove each: %s, %s\n", priority, messages);

        messages.removeIf(message -> message.getPriority() == priority);

        System.out.printf("After remove each: %s, %s\n", priority, messages);
    }

    private static void removeOther(Collection<Message> messages, MessagePriority priority) {
        // Удалить из коллекции все сообщения, кроме тех, которые имеют заданный приоритет.
        System.out.printf("Before remove other: %s, %s\n", priority, messages);

        messages.removeIf(message -> message.getPriority() != priority);

        System.out.printf("After remove other: %s, %s\n", priority, messages);
    }
}
