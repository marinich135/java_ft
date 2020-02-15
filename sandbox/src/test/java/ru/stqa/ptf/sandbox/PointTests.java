package ru.stqa.ptf.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.Point;

public class PointTests {

  @Test
  public void testArea(){

       Point p1 = new Point(4,6);
       Point p2 = new Point(8,2);
       Assert.assertEquals(p1.distance(p2), 5.656854249492381);


  }
}
