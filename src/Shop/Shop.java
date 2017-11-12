package Shop;

import java.util.*;

public class Shop {

    private Map<String, Integer> warehaus = new HashMap<>();
    private Map<String, Product> products = new HashMap<>();
    private Map<User, Boolean> userOnlain = new HashMap<>();
    private Map<String, User> userReg = new HashMap<>();

    public static void main(String[] args) {

        Shop shop = new Shop();

        // Заполним продукцию на склад
        shop.addProduct(new Product("id111", "Сметана", "Веселая молочница", 15.5), 25);
        shop.addProduct(new Product("id222", "Майонез", "Сделает вкусным то, что приготовила твоя жена", 20), 40);
        shop.addProduct(new Product("id333", "Туалетная бумага", "Попкина радость", 10), 100);
        shop.addProduct(new Product("id444", "Пельмени", "Независимый мужчина", 250), 15);

        // Зарегистрируем пользователей
        shop.registerUser(new User("Коля", "123", 20));
        shop.registerUser(new User("Вася", "222", 20));
        shop.registerUser(new User("Петя", "333", 20));
        shop.registerUser(new User("Вася", "222", 20)); // проверка на повторную регистрацию с таким же именем

        // Осуществим вход
        shop.login("Коля", "222"); // проверка на некорректный пароль
        shop.login("Коля", "123");
        shop.login("Вася", "222");
        shop.login("Петя", "333");
        shop.exitUser("Петя"); // проверим выход

        // проверки
        System.out.println(shop.getWarehaus());
    }

    public void addProduct(Product product, Integer count){
        products.put(product.getId(),product);
        warehaus.merge(product.getId(), count, (integer, integer2) -> integer + integer2);
    }
    public void registerUser(User user){

        if (userReg.containsValue(user.getName())) {
            System.out.println("Пользователь с именем " + user.getName() + " уже существует в системе!");
            return;
        }

        userReg.put(user.getName(), user);


    }

    public void login(String name, String pass){

        User user = userReg.get(name);
        if (user == null && !user.checkPass(pass)) {
            System.out.println("Неверно указано имя пользователя или пароль!");
            return;
        }

        userOnlain.merge(user, true, (aBoolean, aBoolean2) -> true);

    }

    public void exitUser(String name){

        User user = userReg.get(name);
        if (user == null){
            System.out.println("Пользователь с таким именем не зарегистрирован!");
            return;
        }
        userOnlain.merge(user, false, (aBoolean, aBoolean2) -> aBoolean2);
    }

    public Map<String, Integer> getWarehaus() {
        return warehaus;
    }
}
