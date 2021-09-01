package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Rita");

    Square s = new Square(5);
    System.out.println("S square side " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("S rectangle sides " + r.a + " and " + r.b + " = " + r.area());

    Point p1 = new Point(2, 3);
    Point p2 = new Point(10, 11);
    System.out.println("Length between  point" + "(" + p1.x + " ," + p1.y + ")" + " and point" + "(" + p2.x + " ," + p2.y + ")" + " = " + distance(p1, p2));

  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt(((p2.x - p1.x) * (p2.x - p1.x)) + ((p2.y - p1.y) * (p2.y - p1.y)));
  }

}
