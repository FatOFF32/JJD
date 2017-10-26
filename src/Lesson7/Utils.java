package Lesson7;


public class Utils {

    public static Object find(Predicate pred, List list){

        for (Object o: list){
            if (pred.apply(o))
                return o;
        }
        return null;
    }

//    public static List filter(Predicate pred, List list){
//
//        List listReturn = list.clone();
//        return listReturn;
//    }
}

