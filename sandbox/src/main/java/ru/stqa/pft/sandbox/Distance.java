package ru.stqa.pft.sandbox;

public class Distance {
  public static void main(String[] args) {

    Point p1 = new Point(0, 0);
    Point p2 = new Point(8, 8);
    System.out.println("Length between  point" + "(" + p1.x + " ," + p1.y + ")" + " and point" + "(" + p2.x + " ," + p2.y + ")" + " = " + p1.distance(p2));

  }
}
