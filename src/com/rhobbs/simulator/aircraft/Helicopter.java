package com.rhobbs.simulator.aircraft;

import com.rhobbs.simulator.WeatherTower;
import com.rhobbs.simulator.weather.Coordinates;
import com.rhobbs.simulator.weather.CoordinatesFactory;
import com.rhobbs.simulator.weather.WeatherProvider;

public class Helicopter extends Aircraft implements Flyable {

  private WeatherTower weatherTower;

  Helicopter(String name, Coordinates coordinates) {
    // Java can only inherit from one class, no diamonds here
    super(name, coordinates);
    System.out.println("Heli name con called: " + this.name + "\nid: " + this.id);
//    super.showCoords();
  }

  public long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public Coordinates getCoordinates() {
    return this.coordinates;
  }

  @Override
  public void updateConditions() {

    int lon = this.coordinates.getLongitude();
    int lat = this.coordinates.getLatitude();
    int height = this.coordinates.getHeight();

    switch (weatherTower.getWeather(this.coordinates)) {
      case "sun":
        lon += 10;
        height += 2;
        break;
      case "rain":
        lon += 5;
        break;
      case "fog":
        lon += 1;
        break;
      case "snow":
        height -= 12;
        break;
      default: break;
    }
    if (height > 100) {
      height = 100;
    } else if (height  <= 0) {
      height = 0;
      this.coordinates = CoordinatesFactory.newCoordinates(lon, lat, height);
//      weatherTower.unregister(this);
      return ;
    }
    this.coordinates = CoordinatesFactory.newCoordinates(lon, lat, height);

  }

  @Override
  public void registerTower(WeatherTower weatherTower) {
    this.weatherTower = weatherTower;
    this.weatherTower.register(this);
  }
}
