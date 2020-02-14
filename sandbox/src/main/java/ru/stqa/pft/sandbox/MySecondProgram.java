package ru.stqa.pft.sandbox;

public class MySecondProgram {

  public static void main(String[] args) {

    Point p1 = new Point().p1(6,5);
    Point p2 = new Point().p2(4,7);


    System.out.println("Расстояние между точками = " + p1.distance());
  }
}
