package multithreading;

//import Lessons7andAbove.ArrayList;
import Patterns.Pizza;

import java.util.*;

public class Pizzeria {

    Order order;

    public static void main(String[] args) {





    }
    private static class Order{
        private final List<Pizza> order;
        public StatusOrder position;

        public Order(Pizza[] pizza) {
            this.order = new ArrayList<>(Arrays.asList(pizza));
            position = StatusOrder.ORDERED;
        }
    }

    public void addOrder(Pizza... pizza){

        order = new Order(pizza);
    }

    enum StatusOrder{
        ORDERED,
        PREPARING,
        READY;
    }
}
