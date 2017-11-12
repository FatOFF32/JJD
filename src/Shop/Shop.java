package Shop;


import java.util.*;

public class Shop {

    private Map<Product, Integer> warehaus = new HashMap<>();
    private Map<String, Product> products = new HashMap<>();
    private Map<User, Boolean> userOnlain = new HashMap<>();
    private Map<String, User> userReg = new HashMap<>();
    private Collection<Transaction> transactions = new ArrayList<Transaction>();

    public static void main(String[] args) {

    }

    public void addProduct(Product product, Integer count){
        products.put(product.getId(),product);
        warehaus.merge(product, count, (integer, integer2) -> integer + integer2);
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
        if (user == null || !user.checkPass(pass)) {
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

    public void buy(String userName, String idProduct, int count){

        // Все необходимые проверки
        User user = userReg.get(userName);
        if (user == null){
            System.out.println("Пользователь с именем " + userName + " не зарегистрирован");
            return;
        }

        if (!userOnlain.get(user).booleanValue()){
            System.out.println("Сессия пользователя " + userName + " устарела, войдите снова!");
            return;
        }

        Product product = products.get(idProduct);
        if (product == null){
            System.out.println("Продукт с ID " + idProduct + " не зарегистрирован!");
            return;
        }

        Integer countWareHause = warehaus.get(product);
        if (countWareHause == null || countWareHause.intValue() < count){
            System.out.println("Запрашиваемого количества нет на складе. Запрашиваемое количество: " + count +
                        ", доступно: " + (countWareHause == null ? 0 : countWareHause));
            return;
        }

        int sum = (int) product.getPrice() * count;
        if (user.getAccount() < sum){
            System.out.println("У пользователя " + userName + " недостаточно средств для покупки! Стоимость покупки: " +
                    sum + " Остаток в кошельке: " + user.getAccount());
            return;
        }

        // Все проверки прошли, можно совершать покупку
        user.writeOffMoney(sum);
        warehaus.merge(product, count, (integer, integer2) -> integer - integer2);
        transactions.add(new Transaction(user, product, count));

        System.out.println("Пользователь: " + userName + " купил " + product +
                " в количестве " + count + " сумма " + sum + " остаток в кошельке " + user.getAccount());


    }

    public Map<Product, Integer> getWarehaus() {
        return warehaus;
    }

    public Map<String, User> getUserReg() {
        return userReg;
    }

    public Map<User, Boolean> getUserOnlain() {
        return userOnlain;
    }
}
