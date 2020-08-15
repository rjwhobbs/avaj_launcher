package com.rhobbs.simulator.aircraft;

import com.rhobbs.simulator.aircraft.Flyable;

public class Helicopter implements Flyable {

  Helicopter(String name) {
    System.out.println("Heli name con called: " + name);
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
