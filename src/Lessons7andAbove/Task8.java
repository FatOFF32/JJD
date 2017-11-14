package Lessons7andAbove;

import java.io.File;

public class Task8 {

    public static void main(String[] args) throws CloneNotSupportedException {

        // Задача 1 и 4
        LinkedList linkedList = new LinkedList();
        linkedList.add(new Integer(1));
        linkedList.add(new Integer(2));
        linkedList.add(new Integer(3));
        linkedList.add(new Integer(4));

        // Затем поменяем их местами
        Integer integer1 = new Integer(5);
        Integer integer2 = new Integer(6);
        linkedList.add(integer1);
        linkedList.add(integer2);

        LinkedList listClone = (LinkedList) linkedList.clone();

        System.out.println("После клонирования. Значения: 1: " + linkedList.toString() + " 2: " + listClone.toString()
                             + "\n hashcod. 1: " + linkedList.hashCode() + " 2: " + listClone.hashCode()
                             + "\n euqals: " + linkedList.equals(listClone));

        listClone.remove(listClone.size() - 1);
        listClone.remove(listClone.size() - 1);
        listClone.add(integer1);
        listClone.add(integer2);

        System.out.println("После удаления и добавления. Значения: 1: " + linkedList.toString() + " 2: " + listClone.toString()
                + "\n hashcod. 1: " + linkedList.hashCode() + " 2: " + listClone.hashCode()
                + "\n euqals: " + linkedList.equals(listClone));

        listClone.remove(listClone.size() - 1);
        listClone.remove(listClone.size() - 1);
        listClone.add(integer2);
        listClone.add(integer1);

        System.out.println("После подмены значений местами. Значения: 1: " + linkedList.toString() + " 2: " + listClone.toString()
                + "\n hashcod. 1: " + linkedList.hashCode() + " 2: " + listClone.hashCode()
                + "\n euqals: " + linkedList.equals(listClone));

//         //задачи 2
//        LinkedList list1 = new LinkedList();
//        list1.add(new Integer(1));
//        list1.add(new Integer(2));
//        list1.add(new Integer(3));
//        list1.add(new Integer(4));
//
//        LinkedList list2 = new LinkedList();
//        list2.add(new Integer(3));
//        list2.add(new Integer(4));
//        list2.add(new Integer(5));
//        list2.add(new Integer(6));
//
//        List list3 = Utils.intersect(list1, list2, null);
//        List list4 = Utils.difference(list1, list2, null);
//
//        System.out.println(list3.toString());
//        System.out.println(list4.toString());
//
//        // Задание 3
        File dir1 = new File("D:\\Учеба JAVA\\ДЗ\\Сравнение объектов\\Каталог 1");
        File dir2 = new File("D:\\Учеба JAVA\\ДЗ\\Сравнение объектов\\Каталог 2");

        List files1 = Utils.toList(dir1.listFiles());
        List files2 = Utils.toList(dir2.listFiles());

        List duplicated = Utils.intersect(files1, files2, new Predicate2() {
            @Override
            public boolean apply(Object o1, Object o2) {
                File file1 = (File) o1;
                File file2 = (File) o2;
                return file1.getName().equals(file2.getName());
            }
        });

        System.out.println("Файлы с одинаковым именем: " + duplicated.toString());

        Object fife3txt = Utils.find(new Predicate() {
            @Override
            public boolean apply(Object o) {
                return ((File) o).getName().equals("3.txt");
            }
        }, files1);

        System.out.println("Нашли файл: " + fife3txt);

        List listFilter = Utils.filter(new Predicate() {
            @Override
            public boolean apply(Object o) {
                return ((File) o).length() < (long) 1048576;
            }
        }, files1);

        System.out.println("Файлы размером меньше 1 МБ: " + listFilter.toString());

        List listFilter2 = Utils.filter(new Predicate() {
            @Override
            public boolean apply(Object o) {
                return getFileExtension((File) o).equals("java");
            }
        }, files1);

        System.out.println("Фильтр файлов с расширение java: " + listFilter2.toString());


    }

    //тупо спёр! Даже коменты не поправил... спать хочу...
    public static String getFileExtension(File file) {
        String fileName = file.getName();
        // если в имени файла есть точка и она не является первым символом в названии файла
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            // то вырезаем все знаки после последней точки в названии файла, то есть ХХХХХ.txt -> txt
            return fileName.substring(fileName.lastIndexOf(".")+1);
            // в противном случае возвращаем заглушку, то есть расширение не найдено
        else return "";
    }

}
