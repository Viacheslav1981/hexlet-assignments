package exercise;

// BEGIN
public interface Home extends Comparable<Home> {

    double getArea();

    // int compareTo(Home another);
    default int compareTo(Home another) {
        if (this.getArea() > another.getArea()) {
            return 1;
        }
        if (this.getArea() == another.getArea()) {
            return 0;
        }
        return -1;
    }
}

// END
