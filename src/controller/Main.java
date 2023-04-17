package controller;

import album.Canvas;
import command.Command;
import java.awt.Color;
import java.io.File;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    File inputfile = new File("demo_input.txt");
    List<Command> c;
    Canvas canvas = new Canvas(100, 100);
    FileParser p = new FileParser("demo_input.txt", canvas);
    p.parseCommands();
    c = p.getCommands();
    System.out.println(c.size());
    Color red = Color.RED;
    System.out.println(
        red.getRed() + "," + red.getGreen() + "," + red.getBlue());
    System.out.println(c.toString());
  }


}
