package com.rhobbs.simulator.aircraft;

import com.rhobbs.simulator.WeatherTower;
import com.rhobbs.simulator.weather.Coordinates;
import com.rhobbs.simulator.weather.CoordinatesFactory;

public class Balloon extends Aircraft implements Flyable {

  private WeatherTower weatherTower;

  Balloon(String name, Coordinates coordinates) {
    super(name, coordinates);
    System.out.println("Balloon name con called: " + this.name + "\nid: " + this.id);
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
        lon += 2;
        height += 4;
        break;
      case "rain":
        height -= 5;
        break;
      case "fog":
        height -= 3;
        break;
      case "snow":
        height -= 15;
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
