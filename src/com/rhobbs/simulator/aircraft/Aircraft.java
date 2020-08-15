package com.rhobbs.simulator.aircraft;

public abstract class Aircraft {
  protected long id;
  protected String name;

  private static long idCounter = 0;

  protected Aircraft(String name) {
    System.out.println("Aircraft con called");
    this.name = name;
    this.id = nextId();
  }

  private long nextId() {
    return Aircraft.idCounter += 1;
  }
}
