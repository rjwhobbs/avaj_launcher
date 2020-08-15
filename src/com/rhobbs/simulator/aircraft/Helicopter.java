package com.rhobbs.simulator.aircraft;

import com.rhobbs.simulator.aircraft.Flyable;

public class Helicopter extends Aircraft implements Flyable {

  Helicopter(String name) {
    // Java can only inherit from one class, no diamonds here
    super(name);
    System.out.println("Heli name con called: " + this.name + "\nid: " + this.id);
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
