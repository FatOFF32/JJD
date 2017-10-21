package Lesson5.Figure;

public class Circle extends Figure{

    public Circle(Point a, Point b){
        super(a, b);
    }

    double getScuare(){
        return Math.PI * Point.getLenght(a, b);
    }

    double getPerimeter(){
        return 2 * Math.PI * Point.getLenght(a, b);
    }
}
