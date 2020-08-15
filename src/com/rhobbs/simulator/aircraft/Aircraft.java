package com.rhobbs.simulator.aircraft;

public abstract class Aircraft {
  protected long id;
//  protected Aircraft() {
//    System.out.println("Def called ");
//  }
  protected Aircraft(long id) {
    System.out.println("Aircraft con called");
    this.id = id;
  }
}
