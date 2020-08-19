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
    if (!isParsable(inputArray[2]) ||
            !isParsable(inputArray[3]) ||
            !isParsable(inputArray[4])) {
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
        int simulationAmount = 0;
        if (line != null) {
          if (line.split(" ").length > 1) {
            throw new Exception();
          }
          simulationInput = line.split(" ")[0];
          if (!isParsable(simulationInput)) {
            throw new Exception();
          }
          simulationAmount = Integer.parseInt(simulationInput);
          if (simulationAmount <= 0) {
            throw new Exception();
          }
        }

        String[] inputArr;
        while ((line = reader.readLine()) != null) {
          inputArr = validateInput(line);
          if (Integer.parseInt(inputArr[4]) <= 0) {
            throw new Exception();
          }
          Flyable flyable = AircraftFactory.newAircraft(
                  inputArr[0],
                  inputArr[1],
                  Integer.parseInt(inputArr[2]),
                  Integer.parseInt(inputArr[3]),
                  Integer.parseInt(inputArr[4])
          );
          flyables.add(flyable);
        }

        WeatherProvider weatherProvider = WeatherProvider.getWeatherProvider();

        for (Flyable flyable: flyables) {
          flyable.registerTower(weatherTower);
        }

        for (int i = 0; i < simulationAmount; i++) {
          weatherTower.changeWeather();
        }

      } catch (FileNotFoundException e) {
        System.out.println(args[0] + " : File not found");
      } catch (IOException e) {
        System.out.println("IO error on file");
      } catch (Exception e) {
        System.out.println("File contains an input error\n"+
                "format:\n"+
                "<simulation amount> (0 > n int) \n"+
                "<aircraft type> (Balloon / JetPlane / Helicopter) "+
                "<name> <long> (int) <lat> (int) <height> (positive int)");
      } finally {
        fh.close();
      }
    }
}
