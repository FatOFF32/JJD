package Shop;

public class User {
    private String name;
    private String password;
    private int account;

    public User(String name, String password, int account) {
        this.name = name;
        this.password = password;
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public boolean checkPass(String pass){
        return password.equals(pass);
    }

    public int getAccount() {
        return account;
    }

    public void writeOffMoney(int count){
        account -= count;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", account=" + account +
                '}';
    }
}
