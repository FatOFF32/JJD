package Lesson5.Operation;

import java.util.Scanner;

import static java.lang.System.out;

public class Accamulator {

    private int value;
    private Operation operation;

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        out.println("Введите число а:" );
        int a = scanner.nextInt();

        out.println("Введите число b:" );
        int b = scanner.nextInt();

        Accamulator accamulator = new Accamulator(a, new Plus());
        accamulator.Accamulate(b);
        out.println("Результат после операции сложения " + accamulator.getValue());

        accamulator = new Accamulator(a, new Minus());
        accamulator.Accamulate(b);
        out.println("Результат после операции вычитания " + accamulator.getValue());

        accamulator = new Accamulator(a, new Multiply());
        accamulator.Accamulate(b);
        out.println("Результат после операции умножения " + accamulator.getValue());

        accamulator = new Accamulator(a, new Devide());
        accamulator.Accamulate(b);
        out.println("Результат после операции деления " + accamulator.getValue());

        accamulator = new Accamulator(a, new Power());
        accamulator.Accamulate(b);
        out.println("Результат после операции возведения в степень " + accamulator.getValue());
    }

    public Accamulator(int a, Operation operation){
        value = a;
        this.operation = operation;
    }
    void Accamulate(int b){

        operation.a = value;
        operation.b = b;
        value = operation.getResult();
    }
    int getValue(){
        return value;
    }
}
