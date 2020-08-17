package com.rhobbs.simulator.aircraft;

import com.rhobbs.simulator.WeatherTower;
import com.rhobbs.simulator.weather.Coordinates;

public class JetPlane extends Aircraft implements Flyable {

  JetPlane(String name, Coordinates coordinates) {
    super(name, coordinates);
    System.out.println("JetPlane name con called: "  + this.name + "\nid: " + this.id);
  }

  public Coordinates getCoordinates() {
    return this.coordinates;
  }

  @Override
  public void updateConditions() {
    System.out.println("JetPlane update called");
  }

  @Override
  public void registerTower(WeatherTower weatherTower) {
    weatherTower.register(this);
  }
}
