package com.rhobbs.simulator;

import com.rhobbs.simulator.aircraft.AircraftFactory;
import com.rhobbs.simulator.aircraft.Flyable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
  private static List<Flyable> flyables = new ArrayList<>();
  private static WeatherTower weatherTower = new WeatherTower();
  private static String logFile = "simulation";
  private static FileHandler fh;
  private static BufferedReader reader;

  public static boolean isParsable(String input) {
    try {
      Integer.parseInt(input);
      return true;
    } catch (final NumberFormatException e) {
      return false;
    }
  }

  private static String[] validateInput(String inputLine) throws Exception {
    String[] inputArray = inputLine.split("\\s+");
    if (inputArray.length != 5 ) {
      throw new Exception();
    }
    if (!inputArray[0].equals("Baloon") && !inputArray[0].equals("Helicopter") && !inputArray[0].equals("JetPlane")) {
      throw new Exception();
    }
    if (!isParsable(inputArray[2]) ||
            !isParsable(inputArray[3]) ||
            !isParsable(inputArray[4])) {
      throw new Exception();
    }
    return inputArray;
  }

  public static void main(String[] args) {
      try {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s%n");
        Logger logger = Logger.getLogger(logFile);
        logger.setUseParentHandlers(false);

        if (args.length != 1) {
          throw new Exception("Incorrect amount of arguments");
        }

        reader = new BufferedReader(new FileReader(args[0]));
        String line = reader.readLine();

        String simulationInput;
        int simulationAmount = 0;
        if (line != null) {
          if (line.isEmpty()) {
            throw new Exception("No empty lines.");
          }
          if (line.split("\\s+").length != 1) {
            throw new Exception();
          }
          simulationInput = line.split("\\s+")[0];
          if (!isParsable(simulationInput)) {
            throw new Exception();
          }
          simulationAmount = Integer.parseInt(simulationInput);
          if (simulationAmount <= 0) {
            throw new Exception();
          }
        } else {
          throw new Exception();
        }

        String[] inputArr;
        while ((line = reader.readLine()) != null) {
          if (line.isEmpty()) {
            throw new Exception("No empty lines.");
          }
          inputArr = validateInput(line);
          if (Integer.parseInt(inputArr[4]) <= 0 ||
              Integer.parseInt(inputArr[3]) <= 0 ||
              Integer.parseInt(inputArr[2]) <= 0) {
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

        fh = new FileHandler("./"+ logFile +".txt");
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);

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
        if (e.getMessage() != null) {
          System.out.println("Error: " + e.getMessage());
        } else {
          System.out.println("File empty or contains an input error: format:\n"+
                  "(No leading or trailing spaces)\n"+
                  "<amount of simulations> (0 > int) \n"+
                  "<aircraft type> (Baloon / JetPlane / Helicopter) "+
                  "<name> (string) <long> (0 > int) <lat> (0 > int) <height> (0 > int)");
        }
      } finally {
        if (fh != null) {
          fh.close();
        }
        if (reader != null) {
          try {
            reader.close();
          } catch (IOException e) {
            System.out.println("Error closing input read buffer.");
          }
        }
      }
    }
}
