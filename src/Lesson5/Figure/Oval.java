package Lesson5.Figure;

public class Oval extends Circle {
    Point c;

    public Oval(Point a, Point b, Point c) {
        super(a, b);
        this.c = c;
    }

    double getScuare() {
        return super.getScuare() * Point.getLenght(a, c);
    }

    double getPerimeter() {

        double r1 = Point.getLenght(a, b);
        double r2 = Point.getLenght(a, c);
        return 4 * ((super.getPerimeter() * r2 + Math.pow((r1 + r2), 2)) / (r1 + r2));
    }
}
