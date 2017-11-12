package Shop;

public class Transaction {
    User user;
    Product product;
    int account;

    public Transaction(User user, Product product, int account) {
        this.user = user;
        this.product = product;
        this.account = account;
    }
}
