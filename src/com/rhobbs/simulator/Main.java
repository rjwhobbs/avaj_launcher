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
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Pattern;

public class Main {
  public static List<Flyable> flyables = new ArrayList<>();
  public static WeatherTower weatherTower = new WeatherTower();
  private List<String[]> inputLine = new ArrayList<>();
//  private static String[] inputArray;
  public static String logFile = "simulation";
  public static FileHandler fh;

  public static boolean isParsable(String input) {
    try {
      Integer.parseInt(input);
      return true;
    } catch (final NumberFormatException e) {
      return false;
    }
  }

  private static String[] validateInput(String inputLine) throws Exception {
    String[] inputArray = inputLine.split(" ");
    if (inputArray.length != 5) {
      throw new Exception();
    }
    if (!inputArray[0].equals("Balloon") && !inputArray[0].equals("Helicopter") && !inputArray[0].equals("JetPlane")) {
      throw new Exception();
    }
    if (!Pattern.matches("^[-\\+]?\\d+$", inputArray[2]) ||
            !Pattern.matches("^[-\\+]?\\d+$", inputArray[3]) ||
            !Pattern.matches("^[-\\+]?\\d+$", inputArray[4])) {
      throw new Exception();
    }
    return  inputArray;
  }

  public static void main(String[] args) /* throws InterruptedException */ {
      try {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s%n");
        Logger logger = Logger.getLogger(logFile);
        fh = new FileHandler("./"+ logFile +".txt");
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);

        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        String line = reader.readLine();

        String simulationInput;
        int simulationAmount;
        if (line != null) {
          if (line.split(" ").length > 1) {
            throw new Exception();
          }
          simulationInput = line.split(" ")[0];
          if (!Pattern.matches("^\\+?\\d+$", simulationInput)) {
            throw new Exception();
          }
        }

        String[] arr;
        while ((line = reader.readLine()) != null) {
          arr = validateInput(line);
          Flyable flyable = AircraftFactory.newAircraft(
                  arr[0],
                  arr[1],
                  Integer.parseInt(arr[2]),
                  Integer.parseInt(arr[3]),
                  Integer.parseInt(arr[4])
          );
          flyables.add(flyable);
        }

        WeatherProvider weatherProvider = WeatherProvider.getWeatherProvider();

        for (Flyable flyable: flyables) {
          flyable.registerTower(weatherTower);
        }

        for (int i = 0; i < 15; i++) {
          weatherTower.changeWeather();
        }
//        for (int i = 0; i < 5; i++) {
//          weatherTower.changeWeather();
//        }
      } catch (FileNotFoundException e) {
        System.out.println(args[0] + " : File not found");
      } catch (IOException e) {
        System.out.println("IO error on file");
      } catch (Exception e) {
        System.out.println("File contains an input error");
      } finally {
        fh.close();
      }
    }
}
