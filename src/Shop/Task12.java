package Shop;

public class Task12 {
    public static void main(String[] args) {

        Shop shop = new Shop();

        // Заполним продукцию на склад
        shop.addProduct(new Product("id111", "Сметана", "Веселая молочница", 15.5), 25);
        shop.addProduct(new Product("id222", "Майонез", "Сделает вкусным то, что приготовила твоя жена", 20), 40);
        shop.addProduct(new Product("id333", "Туалетная бумага", "Попкина радость", 10), 100);
        shop.addProduct(new Product("id444", "Пельмени", "Независимый мужчина", 250), 15);

        // Зарегистрируем пользователей
        shop.registerUser(new User("Коля", "123", 3200));
        shop.registerUser(new User("Вася", "222", 200));
        shop.registerUser(new User("Петя", "333", 200));
        shop.registerUser(new User("Вася", "222", 200)); // проверка на повторную регистрацию с таким же именем

        // Осуществим вход
        shop.login("Коля", "222"); // проверка на некорректный пароль
        shop.login("Коля", "123");
        shop.login("Вася", "222");
        shop.login("Петя", "333");
        shop.exitUser("Петя"); // проверим выход

        // проверки до покупки
        System.out.println("Остатки на складе до операции: " + shop.getWarehaus());
        System.out.println("Зарегистрированные пользователи: " + shop.getUserReg());
        System.out.println("Пользователи он-лайн: " + shop.getUserOnlain());

        // Покупка
        shop.buy("Коля", "id333", 33);
        shop.buy("Коля", "id444", 7);
        shop.buy("Вася", "id444", 9);
        shop.buy("Вася", "id111", 8);
        shop.buy("Петя", "id111", 8);

        // проверки после покупки
        System.out.println("Остатки на складе после операции: " + shop.getWarehaus());

    }
}
