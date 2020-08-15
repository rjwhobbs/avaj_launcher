package com.rhobbs.simulator.aircraft;

import com.rhobbs.simulator.aircraft.Flyable;

public class Helicopter extends Aircraft implements Flyable {

  Helicopter(String name) {
//    Aircraft("Test", 456);
    super(99);
    System.out.println("Heli name con called: " + name + " " + this.id);
  }
  @Override
  public void updateConditions() {
    System.out.println("Heli update called");
  }

  @Override
  public void registerTower() {
    System.out.println("Heli reg Called");
  }
}
