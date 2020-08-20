package com.rhobbs.simulator.aircraft;

import com.rhobbs.simulator.weather.CoordinatesFactory;

public abstract class AircraftFactory implements Flyable {
  public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
    switch (type) {
      case "Helicopter":
        return new Helicopter(name, CoordinatesFactory.newCoordinates(longitude, latitude, height));
      case "Baloon":
        return new Baloon(name, CoordinatesFactory.newCoordinates(longitude, latitude, height));
      case "JetPlane":
        return new JetPlane(name, CoordinatesFactory.newCoordinates(longitude, latitude, height));
      default: return null;
    }
  }
}
