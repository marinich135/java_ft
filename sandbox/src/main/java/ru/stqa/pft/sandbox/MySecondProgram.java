package ru.stqa.pft.sandbox;

public class MySecondProgram {

  public static void main(String[] args) {

    Point p1 = new Point(4,6);
    Point p2 = new Point(8,2);

    System.out.println("Расстояние между точками = " + p1.distance(p2));
  }
}
