package Lesson7;


public final class Utils {

    private Utils() {
    }

    public static Object find(Predicate pred, List list) {

        for (Object o : list) {
            if (pred.apply(o))
                return o;
        }
        return null;
    }

    public static List filter(Predicate pred, List list) {

        List listReturn = list.getCopyList();
        for (Object o : list)
            if (pred.apply(o))
                listReturn.add(o);
        return listReturn;
    }

    public static List transform(Transformer trans, List list) {
        List listReturn = list.getCopyList();
        for (Object o : list)
            listReturn.add(trans.apply(o));
        return listReturn;
    }

    public static List toList(Object[] arr){

        List list = new LinkedList();
        for(Object o : arr)
            list.add(o);
        return list;
    }

    public static List intersect(List list1, List list2, Predicate2 pred){

        List listReturn = list1.getCopyList();

        for (Object o1 : list1)
            for (Object o2 : list2)
                if (pred == null && o1.equals(o2)
                        || pred != null && pred.apply(o1, o2))
                    listReturn.add(o1);
        return listReturn;
    }

    public static List difference(List list1, List list2, Predicate2 pred){

        List listReturn = list1.getCopyList();

        for (Object o1 : list1)
            for (Object o2 : list2)
                if (pred == null && o1.equals(o2)
                        || pred != null && !pred.apply(o1, o2))
                    listReturn.add(o1);
        return listReturn;
    }
}

