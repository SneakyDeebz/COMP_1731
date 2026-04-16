abstract class GeometricObject {
    private String color = "white";
    private boolean filled;
    private java.util.Date dateCreated;

    protected GeometricObject() {
        dateCreated = new java.util.Date();
    }

    protected GeometricObject(String color, boolean filled) {
        dateCreated = new java.util.Date();
        this.color = color;
        this.filled = filled;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public java.util.Date getDateCreated() {
        return dateCreated;
    }

    public abstract double getArea();

    public abstract double getPerimeter();
}

class Circle extends GeometricObject {
    private double radius;

    public Circle() {
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
}

class Rectangle extends GeometricObject {
    private double width, height;

    public Rectangle() {
    }

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }
}

interface Colorable {
    void howToColor();
}

class Square extends GeometricObject implements Colorable {
    private double side;

    // No-arg constructor
    public Square() {
        this.side = 0;
    }

    // Constructor with specified side
    public Square(double side) {
        this.side = side;
    }

    // Getter
    public double getSide() {
        return side;
    }

    // Setter
    public void setSide(double side) {
        this.side = side;
    }

    @Override
    public double getArea() {
        return side * side;
    }

    @Override
    public double getPerimeter() {
        return 4 * side;
    }

    @Override
    public void howToColor() {
        System.out.println("Color all four sides");
    }
}

public class Exercise13_07 {
    public static void main(String[] args) {
        GeometricObject[] objects = {
                new Square(2),
                new Circle(5),
                new Square(5),
                new Rectangle(3, 4),
                new Square(4.5)
        };

        for (int i = 0; i < objects.length; i++) {
            System.out.printf("Object %d: %s%n", i + 1,
                    objects[i].getClass().getSimpleName());
            System.out.printf("  Area is %.2f%n", objects[i].getArea());
            if (objects[i] instanceof Colorable) {
                System.out.print("  howToColor: ");
                ((Colorable) objects[i]).howToColor();
            } else {
                System.out.println("  (Not colorable)");
            }
            System.out.println();
        }
    }
}