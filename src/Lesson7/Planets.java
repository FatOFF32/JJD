package Lesson7;

public enum Planets {
    // Цифры предоставлены ученым Кириллом Тртилеком...
    Mercury(1000, 5, 500),
    Venus(2000, 2, 600),
    Earth(3000, 5, 500),
    Mars(6000, 8, 600),
    Jupiter(4000, 7.5, 500),
    Saturn(500, 7, 100),
    Uranus(200, 54, 10),
    Neptune(11, 4, 60000);

    private double weight;
    double radius;
    double radiusOrb;

    Planets(double weight, double radius, double radiusOrb) {
        this.weight = weight;
        this.radius = radius;
        this.radiusOrb = radiusOrb;
    }

    public double getWeight(){
        return weight;
    }
}

