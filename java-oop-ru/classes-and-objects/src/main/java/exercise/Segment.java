package exercise;

// BEGIN
public class Segment {
    private Point point1;
    private Point point2;

    public static void main(String[] args) {
        System.out.println("test");
        Point point1 = new Point(4, 3);
        Point point2 = new Point(6, 1);
        Segment segment = new Segment(point1, point2);

        Point midPoint = segment.getMidPoint();
        System.out.println(midPoint.getX());
        System.out.println(midPoint.getY());
    }

    public Segment(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public Point getBeginPoint() {
        return point1;
    }

    public Point getEndPoint() {
        return point2;

    }

    public Point getMidPoint() {
        int x1 = (point1.getX() + point2.getX()) / 2;
        int y1 = (point1.getX() + point2.getY()) / 2;
        return new Point(x1, y1);
    }
}

// END
