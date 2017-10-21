package Lesson5.Figure;

public class Rectanle extends Square{

    Point c;

    public Rectanle(Point a, Point b, Point c){
        super(a, b);
        this.c = c;
    }

    double getScuare(){
        return Point.getLenght(a, b) * Point.getLenght(a, c);
    }

    double getPerimeter(){
        return 2 * (Point.getLenght(a, b) + Point.getLenght(a, c));
    }
}
