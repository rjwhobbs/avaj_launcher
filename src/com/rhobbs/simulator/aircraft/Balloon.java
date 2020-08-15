package com.rhobbs.simulator.aircraft;

public class Balloon extends Aircraft implements Flyable {

  Balloon(String name) {
    super(name);
    System.out.println("Balloon name con called: " + this.name + "\nid: " + this.id);
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
