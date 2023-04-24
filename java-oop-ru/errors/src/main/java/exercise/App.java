package exercise;

// BEGIN
public class App {
    public static void main(String[] args) throws NegativeRadiusException {

        Point point = new Point(3, 4);
        Circle circle = new Circle(point, 3);

        App.printSquare(circle);

    }

    public static void printSquare(Circle circle) {
        try {
            double square = Math.round(circle.getSquare());
            int circleSquare = (int) square;
            System.out.println(circleSquare);

        } catch (NegativeRadiusException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Вычисление окончено");
        }


    }
}

// END
