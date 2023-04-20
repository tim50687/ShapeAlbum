package model;

import java.awt.Color;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;
import model.shape.ICoordinate;
import model.shape.IShape;

public interface ICanvasModel {

  /**
   * Move shape on the canvas.
   *
   * @param name       the name
   * @param coordinate the coordinate
   * @throws IllegalArgumentException the illegal argument exception
   */
  public void move(String name, ICoordinate coordinate)
      throws IllegalArgumentException;

  /**
   * Put shape on the canvas.
   *
   * @param name  the name
   * @param shape the shape
   * @throws IllegalArgumentException the illegal argument exception
   */
  public void putShape(String name, IShape shape) throws IllegalArgumentException;

  /**
   * Remove shape on the canvas based on given name of the shape.
   *
   * @param name the name of the shape
   * @return the shape
   * @throws IllegalArgumentException the illegal argument exception
   */
  public IShape removeShape(String name) throws IllegalArgumentException;


  /**
   * Change shape size.
   *
   * @param name   the name
   * @param width  the width
   * @param height the height
   * @throws IllegalArgumentException the illegal argument exception
   */
  public void changeShapeSize(String name, int width, int height)
      throws IllegalArgumentException;

  /**
   * Change color of the given shape.
   *
   * @param name  the name of the shape
   * @param color new color
   */
  public void changeColor(String name, Color color);


  /**
   * Take snapshot.
   *
   * @param description the description of the snapshot
   * @param shapes      the shapes that be put on the canvas
   * @return the snapshot
   * @throws NoSuchAlgorithmException the no such algorithm exception
   */
  public Snapshot takeSnapshot(String description, Map<String, IShape> shapes)
      throws NoSuchAlgorithmException;

  /**
   * Untake snapshot.
   */
  public void unTakeSnapshot();

  /**
   * Gets snapshot by the ID.
   *
   * @param ID the id
   * @return the snapshot
   */
  public String getSnapshot(String ID);

  /**
   * Gets all snapshots that be taken so far.
   *
   * @return the all snapshot
   */
  public LinkedHashMap<String, String> getAllSnapshot();

  /**
   * Print all snapshots.
   */
  public void printSnapshots();

  /**
   * Gets shape by given name.
   *
   * @param name the name
   * @return the shape
   */
  public IShape getShape(String name);

  /**
   * Gets all shape that be put on the canvas.
   *
   * @return the all shape
   */
  public Map<String, IShape> getAllShape();

  /**
   * Print all shapes on the canvas.
   */
  public void printShapes();
}
