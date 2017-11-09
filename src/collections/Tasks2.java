package collections;

import collections.inner.Message;
import collections.inner.MessageGenerator;
import collections.inner.MessagePriority;
import collections.inner.User;

import javax.jws.soap.SOAPBinding;
import java.util.*;

import static collections.inner.UserGenerator.generate;

/**
 * Created by xmitya on 20.10.16.
 */
public class Tasks2 {
    public static void main(String[] args) {
        //System.out.println(generate(10));

        //1
        MessageGenerator generator = new MessageGenerator();
        List<Message> messages = generator.generate(100);
        sortByPriority(messages);

        System.out.println(messages);

        List<User> users = generate(10);
        System.out.println(users);

        //2
        NavigableSet<User> navigableSet = sortedByCompanyAndName(users);
        System.out.println(navigableSet);

        //3
        NavigableSet<User> navigableSet1 = sortedBySalaryAndName(users);
        System.out.println(navigableSet1);

        //4
        NavigableSet<User> navigableSet2 = sortedBySalaryAgeCompanyAndName(users);
        System.out.println(navigableSet2);
    }

    private static void sortByPriority(List<Message> messages) {

        Collections.sort(messages, (o1, o2) -> o1.getPriority().compareTo(o2.getPriority()));

    }

    private static NavigableSet<User> sortedByCompanyAndName(List<User> users) {

        TreeSet<User> setUsers = new TreeSet<>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int flag = o1.getCompany().compareTo(o2.getCompany());
                if (flag == 0)
                    flag = o1.getName().compareTo(o2.getName());
                return flag;
            }
        });

        setUsers.addAll(users);

        return setUsers;
    }

    private static NavigableSet<User> sortedBySalaryAndName(List<User> users) {

        TreeSet<User> setUsers = new TreeSet<>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int flag = o1.getSalary() - o2.getSalary();
                if (flag == 0)
                    o1.getName().compareTo(o2.getName());
                return flag;
            }
        });

        setUsers.addAll(users);

        return setUsers;
    }

    private static NavigableSet<User> sortedBySalaryAgeCompanyAndName(List<User> users) {

        TreeSet<User> setUsers = new TreeSet<>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int flag = o1.getSalary() - o2.getSalary();
                if (flag == 0)
                    flag = o1.getAge() - o2.getAge();
                if (flag == 0)
                    o1.getCompany().compareTo(o2.getCompany());
                if (flag == 0)
                    o1.getName().compareTo(o2.getName());
                return flag;
            }
        });

        setUsers.addAll(users);

        return setUsers;
    }

    private static <T> Iterator<T> viewIterator(Iterable<T> it1, Iterable<T> it2) {

        return null;
    }


}
