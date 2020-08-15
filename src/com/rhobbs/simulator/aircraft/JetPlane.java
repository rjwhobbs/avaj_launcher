package com.rhobbs.simulator.aircraft;

public class JetPlane implements Flyable {

  JetPlane(String name) {
    System.out.println("JetPlane name con called: " + name);
  }

  @Override
  public void updateConditions() {
    System.out.println("JetPlane update called");
  }

  @Override
  public void registerTower() {
    System.out.println("JetPlane reg Called");
  }
}
