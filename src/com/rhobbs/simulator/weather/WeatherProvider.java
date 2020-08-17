package com.rhobbs.simulator.weather;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeatherProvider {

  private static WeatherProvider weatherProvider = null;
  private static String weather;
  private static Random random;
  private static int longMaxRange = 1000;
  private static int heightMaxRange = 1000;
  private static int latitudeMaxRange = 100;

  private WeatherProvider() {
    random = new Random();
  }

  public static WeatherProvider getWeatherProvider() {
    if (weatherProvider == null) {
      return new WeatherProvider();
    }
    return weatherProvider;
  }

  public String getCurrentWeather(Coordinates coordinates) {
    int sun, rain, fog, snow;
    List<String> randArr = new ArrayList<>();

    sun = getRandval(longMaxRange, longMaxRange - coordinates.getLongitude()); // long
    snow = getRandval(longMaxRange, coordinates.getLongitude());
    rain = getRandval(longMaxRange, coordinates.getLongitude());
    fog = getRandval(longMaxRange, coordinates.getLongitude());

    sun += getRandval(latitudeMaxRange, latitudeMaxRange - coordinates.getLatitude());
    rain += getRandval(latitudeMaxRange, latitudeMaxRange - coordinates.getLatitude());
    snow += getRandval(latitudeMaxRange, coordinates.getLatitude());
    fog += getRandval(latitudeMaxRange, coordinates.getLatitude());

    sun += getRandval(heightMaxRange, coordinates.getHeight()); // height
    rain += getRandval(heightMaxRange, coordinates.getHeight());
    snow += getRandval(heightMaxRange, heightMaxRange - coordinates.getHeight());
    fog += getRandval(heightMaxRange, heightMaxRange - coordinates.getHeight());

    System.out.println("Sn " +sun+" Sw "+snow+" R "+rain+" F "+fog);

    for (int i = 0; i <= sun; i++) {
      randArr.add("sun");
    }
    for (int i = 0; i <= snow; i++) {
      randArr.add("snow");
    }
    for (int i = 0; i <= rain; i++) {
      randArr.add("rain");
    }
    for (int i = 0; i <= fog; i++) {
      randArr.add("fog");
    }
    weather = randArr.get(random.nextInt(randArr.size()));
    return weather;
  }

  private int getRandval(int max, int val) {
    int split = max / 10;
    int rand;
    if (val < split) {
      return random.nextInt(2);
    } else if (val < split * 2) {
      return random.nextInt(3);
    } else if (val < split * 3) {
      return random.nextInt(4);
    } else if (val < split * 4) {
      return random.nextInt(5);
    } else if (val < split * 5) {
      return random.nextInt(6);
    } else if (val < split * 6) {
      return random.nextInt(7);
    } else if (val < split * 7) {
      return random.nextInt(8);
    } else if (val < split * 8) {
      return random.nextInt(9);
    } else if (val < split * 9) {
      return random.nextInt(10);
    } else {
      return random.nextInt(10);
    }
  }

}
