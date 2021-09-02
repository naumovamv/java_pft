package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTests {
  @Test
  public void testDistance1() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(9, 9);
    Assert.assertEquals(p1.distance(p2), 12.727922061357855);
  }

  @Test
  public void testDistance2() {
    Point p1 = new Point(8, 7);
    Point p2 = new Point(5, 4);
    Assert.assertEquals(p1.distance(p2), 4.242640687119285);
  }

}
