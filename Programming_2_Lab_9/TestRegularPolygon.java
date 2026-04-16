public class TestRegularPolygon {
        public static void main(String[] args) {

                // Object 1: No-arg constructor (n=3, side=1.0, x=0.0, y=0.0)
                RegularPolygon p1 = new RegularPolygon();

                // Object 2: RegularPolygon(6, 4) → n=6, side=4.0, centered at origin
                RegularPolygon p2 = new RegularPolygon(6, 4);

                // Object 3: RegularPolygon(10, 4, 5.6, 7.8)
                RegularPolygon p3 = new RegularPolygon(10, 4, 5.6, 7.8);

                System.out.println("======================================");
                System.out.println("       Regular Polygon Results        ");
                System.out.println("======================================");

                System.out.printf("Polygon 1 (n=%d, side=%.1f, x=%.1f, y=%.1f)%n",
                                p1.getN(), p1.getSide(), p1.getX(), p1.getY());
                System.out.printf("  Perimeter : %.2f%n", p1.getPerimeter());
                System.out.printf("  Area      : %.2f%n%n", p1.getArea());

                System.out.printf("Polygon 2 (n=%d, side=%.1f, x=%.1f, y=%.1f)%n",
                                p2.getN(), p2.getSide(), p2.getX(), p2.getY());
                System.out.printf("  Perimeter : %.2f%n", p2.getPerimeter());
                System.out.printf("  Area      : %.2f%n%n", p2.getArea());

                System.out.printf("Polygon 3 (n=%d, side=%.1f, x=%.1f, y=%.1f)%n",
                                p3.getN(), p3.getSide(), p3.getX(), p3.getY());
                System.out.printf("  Perimeter : %.2f%n", p3.getPerimeter());
                System.out.printf("  Area      : %.2f%n", p3.getArea());

                System.out.println("======================================");
        }
}