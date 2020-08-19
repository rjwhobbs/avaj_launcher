package com.rhobbs.simulator.aircraft;

import com.rhobbs.simulator.WeatherTower;

public interface Flyable {
  public void updateConditions();
  public void registerTower(WeatherTower weatherTower);
}
