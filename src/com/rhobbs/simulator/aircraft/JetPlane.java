package com.rhobbs.simulator.aircraft;

import com.rhobbs.simulator.weather.Coordinates;

public class JetPlane extends Aircraft implements Flyable {

  JetPlane(String name, Coordinates coordinates) {
    super(name, coordinates);
    System.out.println("JetPlane name con called: "  + this.name + "\nid: " + this.id);
    super.showCoords();
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
