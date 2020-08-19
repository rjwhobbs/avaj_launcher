package com.rhobbs.simulator.aircraft;

import com.rhobbs.simulator.WeatherTower;
import com.rhobbs.simulator.weather.Coordinates;
import com.rhobbs.simulator.weather.CoordinatesFactory;

import java.util.logging.Logger;

public class Helicopter extends Aircraft implements Flyable {

  private WeatherTower weatherTower;

  Helicopter(String name, Coordinates coordinates) {
    super(name, coordinates);
    this.type = "Helicopter";
//    System.out.println("Heli name con called: " + this.name + "\nid: " + this.id);
//    super.showCoords();
  }

  @Override
  public void updateConditions() {

    int lon = this.coordinates.getLongitude();
    int lat = this.coordinates.getLatitude();
    int height = this.coordinates.getHeight();
    String message = "";

    switch (weatherTower.getWeather(this.coordinates)) {
      case "sun":
        lon += 10;
        height += 2;
        message = "This sun reminds me of Top Gun.";
        break;
      case "rain":
        lon += 5;
        message = "Good thing I took the helicopter, look at that wet fool in the balloon.";
        break;
      case "fog":
        lon += 1;
        message = "Can't see, at least I can fly in one place.";
        break;
      case "snow":
        height -= 12;
        message = "Dang it's cold, should've taken the jet today.";
        break;
      default: break;
    }
    Logger.getLogger("simulation").info(
            this.type  + "#" + this.name +
                    "(" + this.id + "): " + message);
    if (height > 100) {
      height = 100;
    } else if (height  <= 0) {
      height = 0;
      this.coordinates = CoordinatesFactory.newCoordinates(lon, lat, height);
      this.weatherTower.unregister(this);
      Logger.getLogger("simulation").info(
            "Tower says: " + this.type + "#" + this.name +
                    "(" + this.id + ") unregistered from weather tower."
      );
      this.coordinates = CoordinatesFactory.newCoordinates(lon, lat, height);
      Logger.getLogger("simulation").info(
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
    Logger.getLogger("simulation").info(
            "Tower says: " + this.type + "#" + this.name +
                    "(" + this.id + ") registered to weather tower."
    );
  }
}
