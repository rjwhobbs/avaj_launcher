package com.rhobbs.simulator;

import com.rhobbs.simulator.aircraft.Flyable;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public abstract class Tower {

  private List<Flyable> observers = new ArrayList<>();

  public void register(Flyable flyable) {
    System.out.println(flyable.getName()+" registered with tower");
    observers.add(flyable);
  }

  public void unregister(Flyable flyable) {
    this.observers.remove(flyable);
    System.out.println(flyable.getName()+" unregistered "+observers.size());
  }

  protected void conditionsChanged() {
    int i = 0;
    int len = observers.size();
    while (i < len) {
      observers.get(i).updateConditions();
      System.out.println("Name: "+ observers.get(i).getName()
              +" Update called: Lo: "
              + observers.get(i).getCoordinates().getLongitude()
              +" La: "+ observers.get(i).getCoordinates().getLatitude()
              +" H: "+ observers.get(i).getCoordinates().getHeight()+
              "\n-----------------------------------------"
      );
      if (observers.get(i).getCoordinates().getHeight() <= 0) {
        unregister(observers.get(i));
        i--;
        len--;
      }
//      Logger logger = Logger.getLogger("log");
//      logger.info("This happened TTTTTTOOTOTOTOTOTOTOTO");
      i++;
    }

  }



}
