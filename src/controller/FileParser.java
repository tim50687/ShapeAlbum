package controller;

import album.Canvas;
import command.ChangeColorShape;
import command.ChangeSizeShape;
import command.Command;
import command.MoveShape;
import command.PutShape;
import command.RemoveShape;
import command.SnapShotCanvas;
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


/**
 * The FileParser class reads a file containing commands for modifying a Canvas object and generates
 * a list of Command objects that can be executed to apply the modifications to the Canvas.
 */
public class FileParser {

  Scanner fileIn; // connection to input file
  String filenameIn; // name of input file
  List<Command> commands; // Commands to be executed
  Canvas canvas; // Command need canvas

  /**
   * Instantiates a new File parser.
   *
   * @param filenameIn the filename in
   * @param canvas     the canvas
   */
  public FileParser(String filenameIn, Canvas canvas) {
    this.filenameIn = filenameIn;
    this.fileIn = new Scanner(this.filenameIn);
    this.commands = new ArrayList<>();
    this.canvas = canvas;
  }

  /**
   * Parse commands.
   */
  public void parseCommands() {
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
            coordinate = new twoDCoordinate(Integer.parseInt(words[3]),
                Integer.parseInt(words[4]));
            color = new Color(Integer.parseInt(words[7]), Integer.parseInt(words[8]),
                Integer.parseInt(words[9]));
            name = words[1];
            type = words[2];
            width = Integer.parseInt(words[5]);
            height = Integer.parseInt(words[6]);
            shape = factory.getShape(type, name, coordinate, color, width, height);
            commandClass = new PutShape(this.canvas, name, shape);
            this.commands.add(commandClass);
            break;
          case "MOVE":
            coordinate = new twoDCoordinate(Integer.parseInt(words[2]),
                Integer.parseInt(words[3]));
            name = words[1];
            commandClass = new MoveShape(this.canvas, name, coordinate);
            this.commands.add(commandClass);
            break;
          case "COLOR":
            color = new Color(Integer.parseInt(words[2]), Integer.parseInt(words[3]),
                Integer.parseInt(words[4]));
            name = words[1];
            commandClass = new ChangeColorShape(this.canvas, name, color);
            this.commands.add(commandClass);
            break;
          case "RESIZE":
            name = words[1];
            width = Integer.parseInt(words[2]);
            height = Integer.parseInt(words[3]);
            commandClass = new ChangeSizeShape(this.canvas, name, width, height);
            this.commands.add(commandClass);
            break;
          case "REMOVE":
            name = words[1];
            commandClass = new RemoveShape(this.canvas, name);
            this.commands.add(commandClass);
            break;
          case "SNAPSHOT":
            String[] temp = line.split("\\s+", 2);
            String description = temp.length > 1 ? temp[1] : "";
            commandClass = new SnapShotCanvas(this.canvas, description);
            this.commands.add(commandClass);
            break;
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("File not found: " + this.filenameIn);
    }

  }

  /**
   * Gets commands.
   *
   * @return the commands
   */
  public List<Command> getCommands() {
    return this.commands;
  }

}

