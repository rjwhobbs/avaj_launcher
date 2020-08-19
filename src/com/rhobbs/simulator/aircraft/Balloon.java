package com.rhobbs.simulator.aircraft;

import com.rhobbs.simulator.Main;
import com.rhobbs.simulator.WeatherTower;
import com.rhobbs.simulator.weather.Coordinates;
import com.rhobbs.simulator.weather.CoordinatesFactory;

import java.util.logging.Logger;

public class Balloon extends Aircraft implements Flyable {

  private WeatherTower weatherTower;

  Balloon(String name, Coordinates coordinates) {
    super(name, coordinates);
    this.type = "Balloon";
//    System.out.println("Balloon name con called: " + this.name + "\nid: " + this.id);
  }

  @Override
  public void updateConditions() {
    int lon = this.coordinates.getLongitude();
    int lat = this.coordinates.getLatitude();
    int height = this.coordinates.getHeight();
    String message = "";

    switch (weatherTower.getWeather(this.coordinates)) {
      case "sun":
        lon += 2;
        height += 4;
        message = "Ah, nice sunny weather.";
        break;
      case "rain":
        height -= 5;
        message = "Why did I take the balloon today, gonna get soaked.";
        break;
      case "fog":
        height -= 3;
        message = "\"A foggy day, in London town...\"";
        break;
      case "snow":
        height -= 15;
        message = "Balloons and snow don't mix!";
        break;
      default: break;
    }
    Logger.getLogger(Main.logFile).info(
            this.type  + "#" + this.name +
                    "(" + this.id + "): " + message);
    if (height > 100) {
      height = 100;
    } else if (height  <= 0) {
      height = 0;
      this.weatherTower.unregister(this);
      Logger.getLogger(Main.logFile).info(
            "Tower says: " + this.type + "#" + this.name +
                    "(" + this.id + ") unregistered from weather tower."
      );
      this.coordinates = CoordinatesFactory.newCoordinates(lon, lat, height);
      Logger.getLogger(Main.logFile).info(
              this.type  + "#" + this.name +
                      "(" + this.id + ") landing."
      );
      return ;
    }
    this.coordinates = CoordinatesFactory.newCoordinates(lon, lat, height);
  }

  @Override
  public void registerTower(WeatherTower weatherTower) {
    this.weatherTower = weatherTower;
    this.weatherTower.register(this);
    Logger.getLogger(Main.logFile).info(
            "Tower says: " + this.type + "#" + this.name +
                    "(" + this.id + ") registered to weather tower."
    );
  }
}
