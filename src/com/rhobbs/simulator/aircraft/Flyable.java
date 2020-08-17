package com.rhobbs.simulator.aircraft;

import com.rhobbs.simulator.WeatherTower;
import com.rhobbs.simulator.weather.Coordinates;

public interface Flyable {
  public void updateConditions();
  public void registerTower(WeatherTower weatherTower);
  public Coordinates getCoordinates();
  public long getId();
  public String getName();
}
