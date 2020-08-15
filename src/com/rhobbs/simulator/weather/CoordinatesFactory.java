package com.rhobbs.simulator.weather;

public abstract class CoordinatesFactory {
  public static Coordinates newCoordinates(int longitude, int latitude, int height) {
    return new Coordinates(longitude, latitude, height);
  }
}
