package Control;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class Bank {

    BlockingQueue<Mail> mails = new ArrayBlockingQueue<Mail>(100);
    Map<Integer, User> accUsers = new HashMap<>();
    Map<Integer, User> users = new HashMap<>();
    ExecutorService pool = Executors.newFixedThreadPool(4);
    int curUserId = 0;
    int curAccId = 0;

    public Bank() {
        pool.submit(new Mailer());
    }

    public static void main(String[] args) {

        Bank bank = new Bank();
        Account acc1 = bank.addAccount(bank.addUser("Дима"), 50);
        Account acc2 = bank.addAccount(bank.addUser("Коля"), 100);
        Account acc3 = bank.addAccount(bank.addUser("Вася"), 150);
        int idPetya = bank.addUser("Петя"); // Для проверки аккаунтов
        Account acc4 = bank.addAccount(idPetya, 200);
        Account acc5 = bank.addAccount(idPetya, 300);

        bank.addTransaction(acc1, acc2, 30);
        bank.addTransaction(acc2, acc1, 10);
        bank.addTransaction(acc5, acc3, 40);
        // Попытка задедлочить
        bank.addTransaction(acc4, acc2, 1000);
        bank.addTransaction(acc2, acc4, 11);
        bank.addTransaction(acc4, acc2, 22);
        bank.addTransaction(acc5, acc2, 60);
        bank.addTransaction(acc4, acc3, 40);
        bank.addTransaction(acc1, acc2, 100);

    }

    void addTransaction(Account src, Account dest, int amount){
        pool.submit(new Transaction(src, dest, amount));
    }

    int addUser(String name){
        curUserId++;
        users.put(curUserId, new User(curUserId, name));
        return curUserId;
    }

    Account addAccount(int userId, int balans){
        curAccId++;
        accUsers.put(curAccId, users.get(userId));
        return new Account(curAccId, balans, userId);
    }

    class Transaction extends Thread{
        Account src;
        Account dest;
        int amount;

        public Transaction(Account src, Account dest, int amount) {
            this.src = src;
            this.dest = dest;
            this.amount = amount;
        }

        @Override
        public void run() {
            try {
                if (transferMoney(src, dest, amount) == TxResult.NOT_ENOUGN)
                    System.out.println("Операция не успешна! У пользователя: " + users.get(src.userID) +
                            " в кошельке " + src.balans + ", а требуется для перевода: " + amount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public TxResult transferMoney(Account src, Account dest, int amount) throws InterruptedException {

        if (src.balans < amount)
            return TxResult.NOT_ENOUGN;

        Account acc1;
        Account acc2;

        if (src.id > dest.id){
            acc1 = dest;
            acc2 = src;
        }
        else {
            acc2 = dest;
            acc1 = src;
        }

        // Зафиксируем по порядку чтобы небыло дедлоков.
        synchronized (acc1){
            synchronized (acc2){
                src.balans -= amount;
                dest.balans +=amount;
                mails.put(new Mail(src.id, "Остаток средств: " + src.balans));
                mails.put(new Mail(dest.id, "Остаток средств: " + dest.balans));

            }
        }

        return TxResult.SACCESS;

    }

    class Mailer extends Thread{
        @Override
        public void run() {
            Mail mail;
            User user;
            while (!Thread.currentThread().isInterrupted()){
                try {
                    mail = mails.take();
                    user = accUsers.get(mail.idAccount);
                    if (user != null)
                        System.out.println("Пользователь: " + user.name + ", Сообщение: " + mail.mes);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }

        }
    }

    static class Mail{
        int idAccount;
        String mes;

        public Mail(int idAccount, String mes) {
            this.idAccount = idAccount;
            this.mes = mes;
        }
    }

    class Account{
        int id;
        int balans;
        int userID;

        public Account(int id, int balans, int userID) {
            this.id = id;
            this.balans = balans;
            this.userID = userID;
        }
    }

    class User{
        int id;
        String name;

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    enum TxResult{
        SACCESS,
        NOT_ENOUGN
    }
}
