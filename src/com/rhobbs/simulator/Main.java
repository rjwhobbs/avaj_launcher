package com.rhobbs.simulator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

abstract class Test {
  public static void method(String s) {
    System.out.println(s);
  }
}

public class Main {

    public static void main(String[] args) /* throws InterruptedException */ {
      try {
        Test.method("MA BRUS");
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        String line = reader.readLine();
        if (line != null) {
          String[] strArr = line.split(" ");
          for (int i = 0; i < strArr.length; i++) {
            System.out.println(strArr[i]);
          }
        }
      } catch (FileNotFoundException e) {
        System.out.println(args[0] + " : File not found");
      } catch (IOException e) {
        System.out.println("IO error on file");
      }
    }
}
