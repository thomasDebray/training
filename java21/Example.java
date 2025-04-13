import java.util.List;

public class Example {

    public record Point(int x,int y) {}

    public record Rectangle(Point topleft, Point bottomRight) {}

    public record Circle(Point center, int radius) {}


    static void describe(Object obj) {
        switch (obj) {
            case Point(int x, int y) ->
                System.out.println("Point en (" + x + ", " + y + ")");
            case Rectangle(Point (int x, int y), Point bottomRight) ->
                System.out.println("Rectangle de Point en (" + x + ", " + y + ")" + " à " + bottomRight);
            case Circle(Point center, int radius) ->
                System.out.println("Cercle de centre " + center + " et de rayon " + radius);
            case null ->
                System.out.println("C'est null !");
            default ->
                System.out.println("Type inconnu : " + obj);
        }
    }
    public static void main(String[] args) {
        List<Object> shapes = List.of(
            new Point(1, 2),
            new Rectangle(new Point(0, 0), new Point(3, 4)),
            new Circle(new Point(5, 5), 10),
            "Pas une forme"
        );

        for (Object shape : shapes) {
            describe(shape);
        }

    }
}