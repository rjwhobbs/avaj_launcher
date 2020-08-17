package com.rhobbs.simulator;

import com.rhobbs.simulator.aircraft.Flyable;
import com.rhobbs.simulator.weather.WeatherProvider;

public class WeatherTower extends Tower {
  private static final WeatherProvider weatherProvider = WeatherProvider.getWeatherProvider();

  public String getWeather(Flyable flyable) {
    return weatherProvider.getCurrentWeather(flyable.getCoordinates());
  }

  void changeWeather(){
    super.conditionsChanged();
  }
}
