package Lesson5.Figure;

public class Triangle extends Figure{
    Point c;

    public Triangle(Point a, Point b, Point c){
        super(a, b);
        this.c = c;
    }

    double getScuare(){
        double p = getPerimeter();
        return Math.sqrt(p * (p - Point.getLenght(a, b)) * (p - Point.getLenght(a, c)) *p * (p - Point.getLenght(c, b)));
    }

    double getPerimeter(){
        return Point.getLenght(a, b) + Point.getLenght(a, c) + Point.getLenght(c, b);
    }
}
