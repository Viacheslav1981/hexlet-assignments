package exercise;

// BEGIN
public class Circle {
    private int radius;
    private final double PI = 3.1415;
    private Point point;

    public Circle(Point point, int radius) {
        this.point = point;
        this.radius = radius;

    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (getRadius() < 0)
            throw new NegativeRadiusException("Не удалось посчитать площадь");
        return PI * getRadius() * getRadius();
    }
}

// END
