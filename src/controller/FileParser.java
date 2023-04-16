package controller;

import album.Canvas;
import command.Command;
import command.PutShape;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import shape.ICoordinate;
import shape.IShape;
import shape.ShapeFactory;
import shape.twoDCoordinate;

public class FileParser {

  Scanner fileIn; // connection to input file
  String filenameIn; // name of input file
  List<Command> commands; // Commands to be executed
  Canvas canvas; // Command need canvas

  public FileParser(Scanner fileIn, String filenameIn, Canvas canvas) {
    this.fileIn = fileIn;
    this.filenameIn = filenameIn;
    this.commands = new ArrayList<>();
    this.canvas = canvas;
  }

  public List<Command> getCommands() {
    File inputfile = new File(this.filenameIn); // Get the input file
    // Read each command at each iteration and store it in an array
    String line = null;
    String[] words;
    // Read the command
    Command commandClass;
    ShapeFactory factory = new ShapeFactory();
    Color color;
    ICoordinate coordinate;
    IShape shape;
    String type;
    String name;
    int width;
    int height;

    // Read from the file
    try {
      this.fileIn = new Scanner(inputfile);
      while (this.fileIn.hasNext()) {
        line = this.fileIn.nextLine().trim();
        if (line.isEmpty()) {
          continue;
        }
        if (line.startsWith("#")) {
          continue;
        }
        words = line.split("\\s+");
        String command = words[0];
        switch (command.toUpperCase()) {
          case "SHAPE":
            coordinate = new twoDCoordinate(Double.parseDouble(words[3]),
                Double.parseDouble(words[4]));
            color = new Color(Integer.parseInt(words[7]), Integer.parseInt(words[8]),
                Integer.parseInt(words[9]));
            name = words[1];
            type = words[2];
            width = Integer.parseInt(words[5]);
            height = Integer.parseInt(words[6]);
            shape = factory.getShape(type, name, coordinate, color, width, height);
            commandClass = new PutShape(this.canvas, type, name, shape);
            this.commands.add(commandClass);
            break;
          case "MOVE":
          case "COLOR":
          case "RESIZE":
          case "REMOVE":
          case "SNAPSHOT":
        }
        System.out.println(words[0]);
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

    return this.commands;
  }

  public static void main(String[] args) {
    File inputfile = new File("demo_input.txt");
    String[] words;
    String line = null;
    try {
      Scanner fileIn = new Scanner(inputfile);
      while (fileIn.hasNext()) {
        line = fileIn.nextLine().trim();
        if (line.isEmpty()) {
          continue;
        }
        if (line.startsWith("#")) {
          continue;
        }
        words = line.split("\\s+");
        System.out.println(words[0]);
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
