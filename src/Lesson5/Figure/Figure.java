package Lesson5.Figure;

import static java.lang.System.out;

public class Figure {

    Point a;
    Point b;

    public static void main(String[] args){

        Point a = new Point(5,8);
        Point b = new Point(5,10);
        Point c = new Point(10,8);

        Circle circle = new Circle(a, b);
        out.println("Площать круга: " + circle.getScuare());
        out.println("Периметр круга: " + circle.getPerimeter());

        Oval oval = new Oval(a, b, c);
        out.println("Площать овала: " + oval.getScuare());
        out.println("Периметр овала: " + oval.getPerimeter());

        Square square = new Square(a, b);
        out.println("Площать квадрата: " + square.getScuare());
        out.println("Периметр квадрата: " + square.getPerimeter());

        Rectanle rectanle = new Rectanle(a, b, c);
        out.println("Площать прямоугольника: " + rectanle.getScuare());
        out.println("Периметр прямоугольника: " + rectanle.getPerimeter());

        Triangle triangle = new Triangle(a, b, c);
        out.println("Площать треугольника: " + triangle.getScuare());
        out.println("Периметр треугольника: " + triangle.getPerimeter());

    }

    public Figure(Point a, Point b){
        this.a = a;
        this.b = b;
    }
}
