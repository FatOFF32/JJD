package Lessons7andAbove;


import java.util.Iterator;

public final class Utils {

    public static String toString(Object o){

        return new String();

    }

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

    public static List difference(List list1, List list2, Predicate2 pred) throws CloneNotSupportedException {

        List listReturn = list1.getCopyList();
        boolean addToList;

        for (Object o1 : list1) {
            addToList = true;
            for (Object o2 : list2)
                if (pred == null && o1.equals(o2)
                        || pred != null && pred.apply(o1, o2)) {
                    addToList = false;
                    break;
                }
            if (addToList) listReturn.add(o1);
        }

        return listReturn;
    }

    public static Iterable view(Iterable it1, Iterable... it2){
        Iterator[] iter = new Iterator[it2.length + 1];

        iter[0] = it1.iterator();
        for (int i = 0; i < it2.length; i++)
            iter[i + 1] = it2[i].iterator();
        return new View(iter);
    }

    static class View implements Iterable{
        Iterator[] iter;

        public View(Iterator[] iter) {
            this.iter = iter;
        }

        @Override
        public Iterator iterator() {
            return new Iterator() {
                int idx = 0;
                Object o;
                @Override
                public boolean hasNext() {
                    if (idx == iter.length) return false;
                    if (iter[idx].hasNext()){
                        o = iter[idx].next();
                        return true;
                    }
                    else {
                        idx++;
                        return hasNext();
                    }
                }

                @Override
                public Object next() {
                    return o;
                }
            };
        }
    }

    public static Iterable transformView(Transformer2 trans, Iterable... iterables){

        Iterator[] iter = new Iterator[iterables.length];

        for (int i = 0; i < iterables.length; i++)
            iter[i] = iterables[i].iterator();

        return new ViewIterableTranform(trans, iter);

    }

    static class ViewIterableTranform implements Iterable {

        Transformer2 trans;
        Iterator[] iter;

        public ViewIterableTranform(Transformer2 trans, Iterator... iter) {
            this.trans = trans;
            this.iter = iter;
        }

        @Override
        public Iterator iterator() {
            return new Iterator() {
                int idx = 0;
                Object o;
                @Override
                public boolean hasNext() {
                    if (idx == iter.length)
                        return  false;
                    if (iter[idx].hasNext()) {
                        o = trans.apply(iter[idx].next());
                        return true;
                    }
                    else{
                        idx++;
                        return hasNext();
                    }
                }

                @Override
                public Object next() {
                    return o;
                }
            };
        }
    }

    public static Iterable filterView(Predicate pred, Iterable... iterables){

        // Создадим массив итераторов, для перехода по ним через hasnext и next для получения результата.
        // через foreach пройти не получится, так как скорее всего он сбрасывает счетчик при вызове это процедуры.
        Iterator[] iter = new Iterator[iterables.length];

        for (int i = 0; i < iterables.length; i++)
            iter[i] = iterables[i].iterator();


        // Создадим новый объект, имплементирующий Iterable. В котором будем хранить массив итераторов для обхода,
        // А также предикат, для выполнения действий над объектом.
        return new ViewIterable(pred, iter);
    }

    static class ViewIterable implements Iterable{
        Iterator[] iterators;
        Predicate pred;

        public ViewIterable(Predicate pred, Iterator... iterators) {
            this.iterators = iterators;
            this.pred = pred;
        }

        @Override
        public Iterator iterator() {
            return new Iterator() {
                Object o;
                int idx = 0;
                @Override
                public boolean hasNext() {
                    if (idx == iterators.length)
                        return  false;
                    while (iterators[idx].hasNext()){
                        o = iterators[idx].next();
                        if (pred.apply(o)) return true;
                    }
                    idx++;
                    return hasNext();
                }

                @Override
                public Object next() {
                    return o;
                }
            };
        }
    }
}

