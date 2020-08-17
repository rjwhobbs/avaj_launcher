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
  public static WeatherTower weatherTower = new WeatherTower();

    public static void main(String[] args) /* throws InterruptedException */ {
      try {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        String line = reader.readLine();

        if (line != null) {
          String[] strArr = line.split(" ");
        }

        while ((line = reader.readLine()) != null) {
          Flyable flyable = AircraftFactory.newAircraft(
                  line.split(" ")[0],
                  line.split(" ")[1],
                  Integer.parseInt(line.split(" ")[2]),
                  Integer.parseInt(line.split(" ")[3]),
                  Integer.parseInt(line.split(" ")[4])
          );
          flyables.add(flyable);
        }

        WeatherProvider weatherProvider = WeatherProvider.getWeatherProvider();

        for (Flyable flyable: flyables) {
          flyable.registerTower(weatherTower);
        }

        for (int i = 0; i < 5; i++) {
          weatherTower.changeWeather();
        }
        System.out.println("CXCXCXCXCXCXCXCXCXCXCXCXCXCCXCXCXCXCXCXCXCXCXCXCXCXCXCXCXCXCXCXC");
        for (int i = 0; i < 5; i++) {
          weatherTower.changeWeather();
        }


      } catch (FileNotFoundException e) {
        System.out.println(args[0] + " : File not found");
      } catch (IOException e) {
        System.out.println("IO error on file");
      }
    }
}
