package com.rhobbs.simulator.aircraft;

import com.rhobbs.simulator.weather.Coordinates;

public class Balloon extends Aircraft implements Flyable {

  Balloon(String name, Coordinates coordinates) {
    super(name, coordinates);
    System.out.println("Balloon name con called: " + this.name + "\nid: " + this.id);
    super.showCoords();
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
