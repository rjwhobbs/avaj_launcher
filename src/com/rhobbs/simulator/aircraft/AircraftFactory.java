package com.rhobbs.simulator.aircraft;

public abstract class AircraftFactory implements Flyable {
  public static Flyable newAircraft(String name) {
    return new Helicopter(name);
  }
}
