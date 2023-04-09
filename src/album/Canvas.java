package album;

import java.awt.Color;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import shape.Circle;
import shape.ICoordinate;
import shape.IShape;
import shape.Rectangle;
import shape.Square;

/**
 * This is a Java class named Canvas that represents a canvas for putting shapes on it.
 */
public class Canvas {

  // Shapes that stored in the Canvas
  private Map<String, IShape> shapes;
  // Snapshots that stored in the Canvas
  private LinkedHashMap<String, String> snapshots;

  private final double length;
  private final double width;

  /**
   * A helper method that check the validity of actions before they are performed.
   *
   * @param type       shape type
   * @param shape      type of IShape
   * @param coordinate coordinate of shape
   * @return true if the action is valid, false otherwise
   */
  private boolean checkValidAction(String type, IShape shape, ICoordinate coordinate) {
    switch (type.toUpperCase()) {
      case "CIRCLE":
        Circle circle = (Circle) shape;
        // Check the new coordinate is valid or not
        return !(coordinate.getX() + circle.getRadius() > this.length)
            && !(coordinate.getX() - circle.getRadius() < 0)
            && !(coordinate.getY() + circle.getRadius() > this.width)
            && !(coordinate.getY() - circle.getRadius() < 0);
      case "RECTANGLE":
        Rectangle rectangle = (Rectangle) shape;
        // Check the new coordinate is valid or not
        return !(coordinate.getX() + rectangle.getLength() / 2 > this.length)
            && !(coordinate.getX() - rectangle.getLength() / 2 < 0)
            && !(coordinate.getY() + rectangle.getWidth() / 2 > this.width)
            && !(coordinate.getY() - rectangle.getWidth() / 2 < 0);
      case "SQUARE":
        Square square = (Square) shape;
        // Check the new coordinate is valid or not
        return !(coordinate.getX() + square.getWidth() / 2 > this.length)
            && !(coordinate.getX() - square.getWidth() / 2 < 0)
            && !(coordinate.getY() + square.getWidth() / 2 > this.width)
            && !(coordinate.getY() - square.getWidth() / 2 < 0);
    }
    return false;
  }

  /**
   * Instantiates a new Canvas.
   *
   * @param length the length
   * @param width  the width
   */
  public Canvas(double length, double width) {
    if (length < 0 || width < 0) {
      throw new IllegalArgumentException("Size cannot be negative value");
    }
    this.shapes = new HashMap<>();
    this.snapshots = new LinkedHashMap<>();
    this.length = length;
    this.width = width;
  }

  /**
   * Move the shape on te canvas.
   *
   * @param type       the type
   * @param name       the name of the shape
   * @param coordinate the coordinate of the shape
   * @throws IllegalArgumentException the illegal argument exception
   */
  public void move(String type, String name, ICoordinate coordinate)
      throws IllegalArgumentException {
    // Check the null string and empty string
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Name cannot be empty string or null");
    }
    if (type == null || type.equals("")) {
      throw new IllegalArgumentException("Name cannot be empty string or null");
    }
    // Check the null coordinate
    if (coordinate == null) {
      throw new IllegalArgumentException("Coordinate cannot be null");
    }
    // Make sure the shape is in the canvas
    if (!this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("Can't find your shape");
    }
    // Make sure the coordinate is valid
    if (coordinate.getX() > length || coordinate.getX() < 0 || coordinate.getY() > width
        || coordinate.getY() < 0) {
      throw new IllegalArgumentException("Invalid Coordinate");
    }
    if (checkValidAction(type, this.shapes.get(name), coordinate)) {
      this.shapes.get(name).move(coordinate);
    } else {
      throw new IllegalArgumentException("Invalid move shape, shape out of bound");
    }

  }

  /**
   * Put shape on the canvas.
   *
   * @param type  the type of the shape
   * @param name  the name of the shape
   * @param shape the shape
   * @throws IllegalArgumentException the illegal argument exception
   */
  public void putShape(String type, String name, IShape shape) throws IllegalArgumentException {
    // Check the null string and empty string
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Name cannot be empty string or null");
    }
    // Check if shape is not null
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null");
    }
    // Check the null string and empty string
    if (type == null || type.equals("")) {
      throw new IllegalArgumentException("Name cannot be empty string or null");
    }
    // Check same name
    if (this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("Can not have the same name");
    }
    if (checkValidAction(type, shape, shape.getCoordinate())) {
      this.shapes.put(name, shape);
    } else {
      throw new IllegalArgumentException("Invalid put shape, shape out of bound");
    }
  }

  /**
   * Remove shape on the canvas based on given name of the shape.
   *
   * @param name the name of the shape
   * @return the shape
   * @throws IllegalArgumentException the illegal argument exception
   */
  public IShape removeShape(String name) throws IllegalArgumentException {
    // Check the null string and empty string
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Name cannot be empty string or null");
    }
    // Check same name
    if (!this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("Can not find your shape");
    }
    IShape removeShape = this.shapes.remove(name);
    return removeShape;

  }

  /**
   * Change shape size.
   *
   * @param type   the type of the shape
   * @param name   the name of the shape
   * @param params the params that represents the dimension of the shape
   * @throws IllegalArgumentException the illegal argument exception
   */
  public void changeShapeSize(String type, String name, double... params)
      throws IllegalArgumentException {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Name cannot be empty string or null");
    }
    if (type == null || type.equals("")) {
      throw new IllegalArgumentException("Name cannot be empty string or null");
    }
    // Make sure the shape is in the canvas
    if (!this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("Can't find your shape");
    }
    // New size of the shape 
    double radius;
    double width;
    double length;
    // If new size is invalid, change it back
    double oldRadius;
    double oldWidth;
    double oldLength;
    switch (type.toUpperCase()) {
      case "CIRCLE":
        if (params.length != 1) {
          throw new IllegalArgumentException("Circle requires 1 parameter: radius");
        }
        radius = params[0];
        Circle circle = (Circle) this.shapes.get(name);
        oldRadius = circle.getRadius();
        circle.setSize(radius);
        // If shape is out of canvas after changing the size, change it back
        if (!checkValidAction(type, this.shapes.get(name), this.shapes.get(name).getCoordinate())) {
          circle.setSize(oldRadius);
          throw new IllegalArgumentException("Invalid change size, shape out of bound");
        }
        break;
      case "SQUARE":
        if (params.length != 1) {
          throw new IllegalArgumentException("Square requires 1 parameter: radius");
        }
        width = params[0];
        Square square = (Square) this.shapes.get(name);
        oldWidth = square.getWidth();
        square.setWidth(width);
        // If shape is out of canvas after changing the size, change it back
        if (!checkValidAction(type, this.shapes.get(name), this.shapes.get(name).getCoordinate())) {
          square.setWidth(oldWidth);
          throw new IllegalArgumentException("Invalid change size, shape out of bound");
        }
        break;
      case "RECTANGLE":
        if (params.length != 2) {
          throw new IllegalArgumentException("Circle requires 1 parameter: radius");
        }
        if (params[0] > params[1]) {
          width = params[1];
          length = params[0];
        } else {
          width = params[0];
          length = params[1];
        }
        Rectangle rectangle = (Rectangle) this.shapes.get(name);
        oldLength = rectangle.getLength();
        oldWidth = rectangle.getWidth();
        rectangle.setSize(length, width);
        // If shape is out of canvas after changing the size, change it back
        if (!checkValidAction(type, this.shapes.get(name), this.shapes.get(name).getCoordinate())) {
          rectangle.setSize(oldLength, oldWidth);
          throw new IllegalArgumentException("Invalid change size, shape out of bound");
        }
        break;
    }

  }

  /**
   * Change color of the given shape.
   *
   * @param name  the name of the shape
   * @param color new color
   */
  public void changeColor(String name, Color color) {
    // Check the null string and empty string
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Name cannot be empty string or null");
    }
    // Make sure the shape is in the canvas
    if (!this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("Can't find your shape");
    }
    this.shapes.get(name).setColor(color);
  }


  /**
   * Take snapshot.
   *
   * @param description the description of the snapshot
   * @param shapes      the shapes that be put on the canvas
   * @return the snapshot
   * @throws NoSuchAlgorithmException the no such algorithm exception
   */
  public Snapshot takeSnapshot(String description, Map<String, IShape> shapes)
      throws NoSuchAlgorithmException {
    // Check if shape is not null
    if (shapes == null) {
      throw new IllegalArgumentException("Shapes cannot be null");
    }
    // Check the null string and empty string
    if (description == null || description.equals("")) {
      throw new IllegalArgumentException("Description cannot be empty string or null");
    }
    Snapshot snapshot = new Snapshot(description, shapes);
    // Use toString() to freeze the snapshot and store it
    this.snapshots.put(snapshot.getID(), snapshot.toString());
    return snapshot;
  }

  /**
   * Untake snapshot.
   */
  public void unTakeSnapshot() {
    int lastIndex = this.snapshots.size() - 1;
    this.snapshots.remove(this.snapshots.keySet().toArray()[lastIndex]);
  }

  /**
   * Gets snapshot by the ID.
   *
   * @param ID the id
   * @return the snapshot
   */
  public String getSnapshot(String ID) {
    return this.snapshots.get(ID);
  }

  /**
   * Gets all snapshots that be taken so far.
   *
   * @return the all snapshot
   */
  public LinkedHashMap<String, String> getAllSnapshot() {
    return this.snapshots;
  }

  /**
   * Print all snapshots.
   */
  public void printSnapshots() {
    StringBuilder ans = new StringBuilder();
    for (String log : this.snapshots.values()) {
      ans.append(log).append("\n");
    }
    System.out.println(ans);
  }

  /**
   * Gets shape by given name.
   *
   * @param name the name
   * @return the shape
   */
  public IShape getShape(String name) {
    return this.shapes.get(name);
  }

  /**
   * Gets all shape that be put on the canvas.
   *
   * @return the all shape
   */
  public Map<String, IShape> getAllShape() {
    return this.shapes;
  }

  /**
   * Print all shapes on the canvas.
   */
  public void printShapes() {
    StringBuilder ans = new StringBuilder();
    for (IShape shape : this.shapes.values()) {
      ans.append(shape.toString()).append("\n");
    }
    System.out.println(ans);
  }
  
  @Override
  public String toString() {
    return String.format("Canvas:\nLength: %.1f, Height: %.1f", this.length, this.width);
  }


}
