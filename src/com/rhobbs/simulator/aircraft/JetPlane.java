package com.rhobbs.simulator.aircraft;

public class JetPlane extends Aircraft implements Flyable {

  JetPlane(String name) {
    super(name);
    System.out.println("JetPlane name con called: "  + this.name + "\nid: " + this.id);
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
