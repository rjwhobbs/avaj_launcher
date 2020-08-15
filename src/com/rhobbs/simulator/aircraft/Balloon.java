package com.rhobbs.simulator.aircraft;

public class Balloon implements Flyable {

  Balloon(String name) {
    System.out.println("Balloon name con called: " + name);
  }
  @Override
  public void updateConditions() {
    System.out.println("Balloon update called");
  }

  @Override
  public void registerTower() {
    System.out.println("Balloon reg Called");
  }
}
