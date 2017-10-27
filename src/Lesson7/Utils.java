package Lesson7;


public final class Utils {

    private Utils(){};

    public static Object find(Predicate pred, List list){

        for (Object o: list){
            if (pred.apply(o))
                return o;
        }
        return null;
    }

    public static List filter(Predicate pred, List list){

        List listReturn = list.getCopyList();
        for (Object o : list)
            if(pred.apply(o))
                listReturn.add(o);
        return listReturn;
    }

    public static List transform(Transformer trans, List list){
        return null;
    }
}

