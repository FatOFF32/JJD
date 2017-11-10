package Shop;

import java.util.HashMap;
import java.util.Map;

public class Shop {

    private Map<String, Integer> warehaus = new HashMap<>();
    private Map<String, Product> products = new HashMap<>();

    public static void main(String[] args) {

        Shop shop = new Shop();

        // Заполним продукцию на склад
        shop.addProduct(new Product("id111", "Сметана", "Веселая молочница", 15.5), 25);
        shop.addProduct(new Product("id222", "Майонез", "Сделает вкусным то, что приготовила твоя жена", 20), 40);
        shop.addProduct(new Product("id333", "Туалетная бумага", "Попкина радость", 10), 100);
        shop.addProduct(new Product("id444", "Пельмени", "Независимый мужчина", 250), 15);

        // проверки
        System.out.println(shop.getWarehaus());
    }

    public void addProduct(Product product, Integer count){
        products.put(product.getId(),product);
        warehaus.merge(product.getId(), count, (integer, integer2) -> integer + integer2);
    }

    public Map<String, Integer> getWarehaus() {
        return warehaus;
    }
}
