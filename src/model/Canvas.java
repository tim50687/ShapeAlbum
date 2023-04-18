package model;

import java.awt.Color;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;
import model.shape.ICoordinate;
import model.shape.IShape;

/**
 * This is a Java class named Canvas that represents a canvas for putting shapes on it.
 */
public class Canvas {

  // Shapes that stored in the Canvas
  private LinkedHashMap<String, IShape> shapes;
  // Snapshots that stored in the Canvas
  private LinkedHashMap<String, String> snapshots;

  private final int length;
  private final int width;


  /**
   * Instantiates a new Canvas.
   *
   * @param length the length
   * @param width  the width
   */
  public Canvas(int length, int width) {
    if (length < 0 || width < 0) {
      throw new IllegalArgumentException("Size cannot be negative value");
    }
    this.shapes = new LinkedHashMap<>();
    this.snapshots = new LinkedHashMap<>();
    this.length = length;
    this.width = width;
  }

  /**
   * Move.
   *
   * @param name       the name
   * @param coordinate the coordinate
   * @throws IllegalArgumentException the illegal argument exception
   */
  public void move(String name, ICoordinate coordinate)
      throws IllegalArgumentException {
    // Check the null string and empty string
    if (name == null || name.equals("")) {
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
    // Move the shape
    this.shapes.get(name).move(coordinate);


  }


  /**
   * Put shape.
   *
   * @param name  the name
   * @param shape the shape
   * @throws IllegalArgumentException the illegal argument exception
   */
  public void putShape(String name, IShape shape) throws IllegalArgumentException {
    // Check the null string and empty string
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Name cannot be empty string or null");
    }
    // Check if shape is not null
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null");
    }
    // Check same name
    if (this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("Can not have the same name");
    }
    // Put the shape on the canvas
    this.shapes.put(name, shape);

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
   * @param name   the name
   * @param width  the width
   * @param height the height
   * @throws IllegalArgumentException the illegal argument exception
   */
  public void changeShapeSize(String name, int width, int height)
      throws IllegalArgumentException {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Name cannot be empty string or null");
    }
    // Make sure the shape is in the canvas
    if (!this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("Can't find your shape");
    }
    this.shapes.get(name).setWidth(width);
    this.shapes.get(name).setHeight(height);
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
    if (description == null) {
      throw new IllegalArgumentException("Description cannot be null");
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
    return String.format("Canvas:\nLength: %d, Height: %d", this.length, this.width);
  }


}
