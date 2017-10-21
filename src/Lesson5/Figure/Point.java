package Lesson5.Figure;

public class Point {
    int x;
    int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    public static double getLenght(Point a, Point b){
        return Math.pow((a.x-b.x),2) + Math.pow((a.y-b.y),2);
    }
}
