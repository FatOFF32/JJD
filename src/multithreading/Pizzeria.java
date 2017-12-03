package multithreading;

//import Lessons7andAbove.ArrayList;
import Patterns.Pizza;

/*
* !!!!ВАЖНО!!!!
* то как я реализовал пицерию, блокируя на одном объекте "order" и клиента и повора и афицианта, в корне не правильно!!! Потому что:
* 1. Это мешает нам сделать несколько заказо (эта причина не главная, все можно реализовать)
* 2. ГЛАВНАЯ ПРИЧИНА! Когда мы усыпляем несколько потоков на одном объекте, а затем будем через notifyAll,
*       будятся все потоки, и начинается гонка (конкуренция за захват объекта) среди конкурирующих потоков.
*       Каждый поток пытается захватить ресурс, если у него не получается (ресурс захвачен другим потоком),
*       то данный поток зависает в ожидании на завате этого объекта. И так делают все потоки,
*       и только после того как обьект освобождается, он захватывает его, проверяет свой флаг, и засыпает...
*       Данная конкуренция очень дорогая операция для производительности системы, поэтому такой ситуации лучше не допускать (особенно когда много потоков)
*
* Как надо было этого избежать (примерно)! Клиент должен заснуть на поворе, повор на пицце, официант на клиенте.
*/

public class Pizzeria {

    // Все мониторы (то на чем блокируются потоки) должны быть final. Во избежании ошибки,
    // когда мы усыпили поток на одном объекте, а затем объект поменяли, и потом будет спать вечно... (так было у меня)
    private final Order order = Order.getEmpty();


    private Pizzeria() {

        /*
        Закроем поток после окончания работы основного потока, закроем все созданные нами!
        ибо без него, все остальные потоки бессмысленны...waiter.setDaemon(true)
        */
        Waiter waiter = new Waiter();
        waiter.setDaemon(true);
        waiter.start();
        while (waiter.getState() != Thread.State.WAITING){
            // Подождем пока официант выйдет на работу и уснет...
        }

        Cooker cooker = new Cooker();
        cooker.setDaemon(true);
        cooker.start();
        while (cooker.getState() != Thread.State.WAITING){
            // Подождем пока повор выйдет на работу и уснет...
        }

    }

    public static void main(String[] args) {

        Pizzeria pizzeria = new Pizzeria();
        Pizza pizza = pizzeria.buyPizza(true, NamePizza.MEAT);
        System.out.println(">>>> Вот такая пицца получилась: " + pizza);

    }
    private class Waiter extends Thread{

        @Override
        public void run() {
            synchronized (order) {
                while (true) {
                    try {
                        System.out.println("Официант сейчас уснет");
                        order.wait();
                        System.out.println("Официант проснулся");
                        if (order.status != StatusOrder.ORDERED)
                            continue;

                        order.status = StatusOrder.PREPARING;
                        System.out.println(">>>> Ваш заказ принят и будет скоро готов!");
                        order.notifyAll();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    private class Cooker extends Thread{

        @Override
        public void run() {
            synchronized (order){
                while (true){
                    try {
                        System.out.println("Повар сейчас уснет");
                        order.wait();
                        System.out.println("Повар проснулся");
                        if (order.status != StatusOrder.PREPARING)
                            continue;

                        if (order.namePizza == NamePizza.MEAT)
                            order.builder.addBecon(10).addSauseg(5);
                        else if (order.namePizza == NamePizza.ITALIANO)
                            order.builder.addSauseg(20).addCheese(5);
                        if (order.namePizza == NamePizza.CHEESE)
                            order.builder.addCheese(25);

                        order.pizza = order.builder.build();

                        // Ну не сразу же нести пиццу клиенту... Пусть аппетит нагуляет...
                        Thread.sleep(5000);

                        order.status = StatusOrder.READY;
                        System.out.println(">>>> Пицца готова, заберите заказ!");
                        order.notifyAll();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    private static class Order{
        Pizza.Builder builder;
        Pizza pizza;
        NamePizza namePizza;
        StatusOrder status = StatusOrder.READY;

        private Order() {
        }

        Order(Pizza.Builder builder, NamePizza namePizza) {
            this.namePizza = namePizza;
            status = StatusOrder.ORDERED;
            this.builder = builder;
        }

        public static Order getEmpty(){
            return new Order();
        }
    }

    private Pizza buyPizza(boolean thic, NamePizza namePizza){

        order.namePizza = namePizza;
        order.status = StatusOrder.ORDERED;
        order.builder = new Pizza.Builder(thic);

        synchronized (order){
            order.notifyAll();
            while (true){
                try {
                    order.wait();
                    if (order.status == StatusOrder.READY){
                        System.out.println(">>>> Мммммм спасибо!!!");
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return order.pizza;

    }

    private enum StatusOrder{
        ORDERED,
        PREPARING,
        READY;
    }

    private enum NamePizza{
        MEAT,
        ITALIANO,
        CHEESE;
    }
}
