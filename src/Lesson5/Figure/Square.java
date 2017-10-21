package Lesson5.Figure;

public class Square extends Figure{

    public Square(Point a, Point b){
        super(a, b);
    }

    double getScuare(){
        return Math.pow(Point.getLenght(a, b),2);
    }

    double getPerimeter(){
        return 4 * Point.getLenght(a, b);
    }
}
