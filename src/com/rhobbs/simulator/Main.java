package com.rhobbs.simulator;

import com.rhobbs.simulator.aircraft.AircraftFactory;
import com.rhobbs.simulator.aircraft.Flyable;
import com.rhobbs.simulator.weather.WeatherProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static List<Flyable> flyables = new ArrayList<>();

    public static void main(String[] args) /* throws InterruptedException */ {
      try {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        String line = reader.readLine();

        if (line != null) {
          String[] strArr = line.split(" ");
          for (int i = 0; i < strArr.length; i++) {
            System.out.println(strArr[i]);
          }
        }

        String[] test = {"Helicopter H1", "Balloon B1", "JetPlane J1", "Helicopter H2"};
        for (int i = 0; i < test.length; i++) {
          Flyable flyable = AircraftFactory.newAircraft(
                  test[i].split(" ")[0],
                  test[i].split(" ")[1],
                  33,
                  44,
                  55
          );
          flyables.add(flyable);
        }
//        Weather w = new Weather();

        for (Flyable flyable: flyables) {
          flyable.updateConditions();
          flyable.registerTower();
//          flyable
        }
        WeatherProvider weatherProvider = WeatherProvider.getWeatherProvider();
        System.out.println(weatherProvider.getCurrentWeather(2, 3, 20));


      } catch (FileNotFoundException e) {
        System.out.println(args[0] + " : File not found");
      } catch (IOException e) {
        System.out.println("IO error on file");
      }
    }
}
