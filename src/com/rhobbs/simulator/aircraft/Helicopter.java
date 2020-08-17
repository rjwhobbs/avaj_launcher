package com.rhobbs.simulator.aircraft;

import com.rhobbs.simulator.weather.Coordinates;

public class Helicopter extends Aircraft implements Flyable {

  Helicopter(String name, Coordinates coordinates) {
    // Java can only inherit from one class, no diamonds here
    super(name, coordinates);
    System.out.println("Heli name con called: " + this.name + "\nid: " + this.id);
    super.showCoords();
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
