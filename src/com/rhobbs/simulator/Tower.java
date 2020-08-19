package com.rhobbs.simulator;

import com.rhobbs.simulator.aircraft.Flyable;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public abstract class Tower {

  private List<Flyable> observers = new ArrayList<>();

  public void register(Flyable flyable) {
//    System.out.println(flyable.getName()+" registered with tower");
    observers.add(flyable);
    // Logger.getLogger(Main.logFile).info(
    //         "Tower says: " + flyable.getType() + "#" + flyable.getName() +
    //                 "(" + flyable.getId() + ") registered to weather tower."
    // );
  }

  public void unregister(Flyable flyable) {
    observers.remove(flyable);
    // Logger.getLogger(Main.logFile).info(
    //         "Tower says: " + flyable.getType() + "#" + flyable.getName() +
    //                 "(" + flyable.getId() + ") unregistered from weather tower."
    // );
  }

  protected void conditionsChanged() {
    int i = 0;
    while (i < observers.size()) { // So it appears this size() is dynamic
      observers.get(i).updateConditions();
      i++;
    }

  }



}
