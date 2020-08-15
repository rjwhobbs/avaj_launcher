package com.rhobbs.simulator.aircraft;

public abstract class AircraftFactory implements Flyable {
  public static Flyable newAircraft(String type, String name) {
    switch (type) {
      case "Helicopter":
        return new Helicopter(name);
      case "Balloon":
        return new Balloon(name);
      case "JetPlane":
        return new JetPlane(name);
      default: return null;
    }
  }
}
